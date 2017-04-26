package com.melnychuk.dao.impls;

import com.melnychuk.dao.interfaces.JoinDao;
import com.melnychuk.entities.Join;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinDaoImpl implements JoinDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public JoinDaoImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Join join)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(join);
    }
}
