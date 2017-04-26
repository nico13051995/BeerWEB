package com.melnychuk.helpers;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.melnychuk.config.Config;
import com.melnychuk.dao.interfaces.BeerDao;
import com.melnychuk.dao.interfaces.SalePointDao;
import com.melnychuk.entities.Beer;
import com.melnychuk.entities.Join;
import com.melnychuk.entities.SalePoint;
import com.melnychuk.managers.SalePointManager;
import com.melnychuk.objects.ExcelParseResult;
import com.melnychuk.objects.UploadAnswer;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Component
public class ExcelHelper
{
    private final BeerDao beerDao;
    private final SalePointDao salePointDao;

    private final SalePointManager pointManager;

    @Autowired
    public ExcelHelper(BeerDao beerDao, SalePointDao salePointDao, SalePointManager pointManager)
    {
        this.beerDao = beerDao;
        this.salePointDao = salePointDao;
        this.pointManager = pointManager;
    }

    public UploadAnswer readFromExcel(File file) throws IOException, ApiException, InterruptedException
    {
        List<ExcelParseResult> results = parseFile(file);

        UploadAnswer answer = prepareToInsert(results);

        return answer;
    }

    private List<ExcelParseResult> parseFile(File file) throws IOException
    {
        List<ExcelParseResult> results = new ArrayList<ExcelParseResult>();

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(0);


        for (int i = 1; i < sheet.getLastRowNum() + 1; i++)
        {
            XSSFRow row = sheet.getRow(i);

            SalePoint sp = new SalePoint();
            sp.setName(row.getCell(Config.POINT_NAME_CELL).getStringCellValue());
            sp.setAddress(row.getCell(Config.POINT_ADDRESS_CELL).getStringCellValue());

            String beerName = row.getCell(Config.BEER_NAME_CELL).getStringCellValue();

            boolean[] joins = new boolean[Config.JOINS_COUNT];
            for (int j = 0; j < joins.length; j++)
            {
                String value = row.getCell(Config.BEER_NAME_CELL + (j + 1)).getStringCellValue();
                joins[j] = value.equals("+");
            }

            results.add(new ExcelParseResult(sp, beerName, joins));
        }

        return results;
    }

    private UploadAnswer prepareToInsert(List<ExcelParseResult> results) throws InterruptedException, ApiException, IOException
    {
        UploadAnswer uploadAnswer = new UploadAnswer();
        Set<SalePoint> points = new HashSet<SalePoint>(results.size() / 2);

        SalePoint point = null;
        for (ExcelParseResult result : results)
        {
            point = salePointDao.getPointByName(result.getPoint().getName());
            if (point == null)
            {
                //TODO new point!
                SalePoint newPoint = result.getPoint();
                LatLng latLng = pointManager.makeGeocodeDataFromInfo(newPoint.getAddress());
                newPoint.setLat(latLng.lat);
                newPoint.setLng(latLng.lng);

                salePointDao.save(newPoint);

                point = salePointDao.getPointByName(result.getPoint().getName());
                uploadAnswer.addNew(point);
            }
            else uploadAnswer.addUpdated(point);

            points.add(point);

            Beer beer = beerDao.getBeerByName(result.getBeerName());

            Join join = createJoin(point.getId(), beer.getId(), result.getJoins());
            join.setSalePoint(point);
            join.setBeer(beer);

            point.getJoins().add(join);

            salePointDao.save(point);
        }

        return uploadAnswer;
    }

    private Join createJoin(int pointID, int beerID, boolean[] joins)
    {
        Join join = new Join();
        join.setPointId(pointID);
        join.setBeerId(beerID);

        join.setG33(joins[0]);
        join.setG05(joins[1]);
        join.setP1(joins[2]);
        join.setP15(joins[3]);
        join.setP2(joins[4]);
        join.setK30(joins[5]);
        join.setK50(joins[6]);

        return join;
    }
}
