package com.melnychuk.dao.impls;

import com.melnychuk.dao.interfaces.SalePointDao;
import com.melnychuk.entities.SalePoint;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SalePointDaoImpl implements SalePointDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public SalePointDaoImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public List<SalePoint> getSalePoints()
    {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SalePoint.class);
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
//        criteria.setResultTransformer(Transformers.aliasToBean(SalePoint.class));

        return criteria.list();
    }
}
