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
import com.melnychuk.objects.ExcelParseResultForPoints;
import com.melnychuk.objects.UploadBeersAnswer;
import com.melnychuk.objects.UploadPointsAnswer;
import org.apache.poi.ss.usermodel.Cell;
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

    public UploadPointsAnswer readPointsFromExcel(File file) throws IOException, ApiException, InterruptedException
    {
        List<ExcelParseResultForPoints> results = parseFile(file);

        UploadPointsAnswer answer = prepareToInsert(results);

        return answer;
    }

    private List<ExcelParseResultForPoints> parseFile(File file) throws IOException
    {
        List<ExcelParseResultForPoints> results = new ArrayList<ExcelParseResultForPoints>();

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++)
        {

            try
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

                results.add(new ExcelParseResultForPoints(sp, beerName, joins));
            }
            catch (Exception e)
            {
                break;
            }

        }

        return results;
    }

    private UploadPointsAnswer prepareToInsert(List<ExcelParseResultForPoints> results) throws InterruptedException, ApiException, IOException
    {
        UploadPointsAnswer uploadPointsAnswer = new UploadPointsAnswer();
//        Set<SalePoint> points = new HashSet<SalePoint>(results.size() / 2);

        SalePoint point = null;
        for (ExcelParseResultForPoints result : results)
        {
            point = salePointDao.getPointByNameAndAddress(result.getPoint().getName(), result.getPoint().getAddress());
            if (point == null)
            {
                System.out.println(String.format("New: %s at [%s]", result.getPoint().getName(), result.getPoint().getAddress()));
                //TODO new point!
                SalePoint newPoint = result.getPoint();
                LatLng latLng = pointManager.makeGeocodeDataFromInfo(newPoint.getAddress());
                if(latLng != null)
                {
                    newPoint.setLat(latLng.lat);
                    newPoint.setLng(latLng.lng);
                } else {
                    uploadPointsAnswer.AddMsg(String.format("Некоректна адреса %s", newPoint.getAddress()));
                }

                salePointDao.save(newPoint);
                point = salePointDao.getPointByNameAndAddress(newPoint.getName(), newPoint.getAddress());

                uploadPointsAnswer.addNew(newPoint);
            } else
            {
                System.out.println(String.format("Up: %s", point.getName()));
                point.setAddress(result.getPoint().getAddress());

                uploadPointsAnswer.addUpdated(point);
            }

//            points.add(point);

            Beer beer = beerDao.getBeerByName(result.getBeerName());

            if(beer != null && point != null)
            {
                Join join = createJoin(point.getId(), beer.getId(), result.getJoins());
                join.setSalePoint(point);
                join.setBeer(beer);

                point.getJoins().add(join);
            }

            salePointDao.save(point);
        }

        return uploadPointsAnswer;
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


    public UploadBeersAnswer readBeersFromExcel(File file) throws IOException
    {
        UploadBeersAnswer beersAnswer = parseBeerExcel(file);

        return beersAnswer;
    }

    private UploadBeersAnswer parseBeerExcel(File file) throws IOException
    {
        UploadBeersAnswer beersAnswer = new UploadBeersAnswer();

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++)
        {
            XSSFRow row = sheet.getRow(i);

            if(getCellsSize(row) >= 3)
            {
                Beer beer = null;
                beer = beerDao.getBeerByName(row.getCell(0).getStringCellValue());
                if (beer != null)
                {
                    //add to updated
                    beersAnswer.addUpdated(beer);
                } else
                {
                    //add to new
                    beer = new Beer();
                    beer.setName(row.getCell(0).getStringCellValue());
                    beer.setDescription(row.getCell(1).getStringCellValue());
                    beer.setLogo(row.getCell(2).getStringCellValue());

                    beersAnswer.addNew(beer);
                }

                beerDao.save(beer);
            }
        }

        return beersAnswer;
    }


    private int getCellsSize(XSSFRow row)
    {
        int size = 0;

        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext())
        {
            cellIterator.next();
            size++;
        }
        return size;
    }

}
