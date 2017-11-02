package gr.pr.datereleases.hibernatetools;

import com.sun.xml.internal.ws.handler.HandlerException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTools {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        catch (HandlerException he){
            throw he;
        }
    }

    public static Session getSession() throws HandlerException {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            session = sessionFactory.openSession();
        }

        return sessionFactory.openSession();
    }

    public static void insertEntity(Object entity){
        Session session = HibernateTools.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public static void updateEntity(Object entity){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        session.flush();
        tx.commit();
        session.close();
    }

    public static void deleteTableRow(Object entity){
        Session session = HibernateTools.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        session.flush();
		tx.commit();
        session.close();
    }
}
