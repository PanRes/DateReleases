package gr.pr.datereleases.hibernatetools;

import com.sun.xml.internal.ws.handler.HandlerException;
import gr.pr.datereleases.models.UsersModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class UserTools {


    public static int isValidUser(String userName,String password){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<UsersModel> users = session.createCriteria(UsersModel.class).
                add(Restrictions.like("userName",userName)).
                add((Restrictions.like("password", password))).list();

        session.close();

        if(!users.isEmpty()){
            return users.get(0).getId();
        }

        return -1;
    }

    public static UsersModel getUserById(int userId){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        UsersModel user = session.get(UsersModel.class,userId);
        session.close();
        return user;
    }

    public static boolean userNameExists(String userName, int userId){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<UsersModel> users = session.createCriteria(UsersModel.class).
                add(Restrictions.eq("userName",userName)).
                add(Restrictions.ne("id",userId)).list();
        session.flush();
        session.close();
        return !users.isEmpty();
    }

    public static boolean emailExists(String email, int userId){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<UsersModel> users = session.createCriteria(UsersModel.class).
                add(Restrictions.eq("email",email)).
                add(Restrictions.ne("id",userId)).list();
        session.flush();
        session.close();
        return !users.isEmpty();
    }
}
