package gr.pr.date_releases.hibernatetools;

public class UserTools {

/*
	public static UsersModel isValidUser(String userName,String password){
		int id = -1;
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		if (users == null){
			return null;
		}

		return users.get(0);
	}

	public static UsersModel getUserById(int userId){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		UsersModel user = session.get(UsersModel.class,userId);
		session.close();
		return user;
	}

	public static boolean userNameExists(String userName, int userId){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		List<UsersModel> users = session.createCriteria(UsersModel.class).
				add(Restrictions.eq("userName",userName)).
				add(Restrictions.ne("id",userId)).list();
		session.flush();
		session.close();
		return !users.isEmpty();
	}

	public static boolean userNameExists(String userName){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		List<UsersModel> users = session.createCriteria(UsersModel.class).
				add(Restrictions.eq("userName",userName)).list();
		session.flush();
		session.close();
		return !users.isEmpty();
	}

	public static boolean emailExists(String email, int userId){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		List<UsersModel> users = session.createCriteria(UsersModel.class).
				add(Restrictions.eq("email",email)).
				add(Restrictions.ne("id",userId)).list();
		session.flush();
		session.close();
		return !users.isEmpty();
	}

	public static boolean emailExists(String email){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		List<UsersModel> users = session.createCriteria(UsersModel.class).
				add(Restrictions.eq("email",email)).list();
		session.flush();
		session.close();
		return !users.isEmpty();
	}*/
}
