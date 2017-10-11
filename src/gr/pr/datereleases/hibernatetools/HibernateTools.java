package gr.pr.datereleases.hibernatetools;

import com.sun.xml.internal.ws.handler.HandlerException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        return sessionFactory.openSession();
    }
}
