package gr.pr.datereleases.hibernatetools;

import com.sun.xml.internal.ws.handler.HandlerException;
import gr.pr.datereleases.models.UsersModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class UserTools {


    public static boolean isValidUser(String userName,String password){
        Session session = HibernateTools.getSession();

        List<UsersModel> users = session.createCriteria(UsersModel.class).
                add(Restrictions.like("userName",userName)).
                add((Restrictions.like("password", password))).list();

        session.close();

        if(!users.isEmpty()){
            return true;
        }

        return false;
    }



}
