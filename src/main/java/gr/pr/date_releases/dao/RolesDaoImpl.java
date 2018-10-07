package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.RolesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RolesDaoImpl implements RolesDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<RolesEntity> getAllRoles() {
		Session session = sessionFactory.getCurrentSession();

		TypedQuery<RolesEntity> query = session.createNamedQuery("Role.findAll", RolesEntity.class);

		//TODO : add exception control
		return query.getResultList();
	}

	@Override
	public RolesEntity getRoleByName(String roleName) {
		Session session = sessionFactory.getCurrentSession();

		TypedQuery<RolesEntity> query = session.createNamedQuery("Role.findByName", RolesEntity.class)
				.setParameter("roleName", roleName);

		//TODO : add exception control
		return query.getSingleResult();
	}
}
