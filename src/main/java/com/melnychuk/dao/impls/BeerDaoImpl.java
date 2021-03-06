package com.melnychuk.dao.impls;

import com.melnychuk.dao.interfaces.BeerDao;
import com.melnychuk.entities.Beer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BeerDaoImpl implements BeerDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public BeerDaoImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public List<Beer> getBeers()
    {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Beer.class);
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());

        return criteria.list();
    }

    @Transactional
    @Override
    public Beer getBeerByName(String name)
    {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Beer.class);
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.add(Restrictions.eq("name", name));

        return (Beer) criteria.uniqueResult();
    }

    @Transactional
    @Override
    public void save(Beer beer)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(beer);
    }
}
