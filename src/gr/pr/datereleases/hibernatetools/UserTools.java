package gr.pr.datereleases.hibernatetools;

import com.sun.xml.internal.ws.handler.HandlerException;
import gr.pr.datereleases.models.UsersModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserTools {


    public static UsersModel isValidUser(String userName,String password){
        int id = -1;
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<UsersModel> users = session.createCriteria(UsersModel.class).
                add(Restrictions.like("userName",userName)).
                add((Restrictions.like("password", password))).list();
        if(users.isEmpty()){
            users = session.createCriteria(UsersModel.class).
                    add(Restrictions.like("email",userName)).
                    add((Restrictions.like("password", password))).list();
            if(users.isEmpty()){
                users = null;
            }
        }

        session.flush();
        session.close();

        return users.get(0);
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

    public static boolean userNameExists(String userName){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<UsersModel> users = session.createCriteria(UsersModel.class).
                add(Restrictions.eq("userName",userName)).list();
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

    public static boolean emailExists(String email){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<UsersModel> users = session.createCriteria(UsersModel.class).
                add(Restrictions.eq("email",email)).list();
        session.flush();
        session.close();
        return !users.isEmpty();
    }
}
