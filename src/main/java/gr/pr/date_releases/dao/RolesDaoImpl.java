package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.RolesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RolesDaoImpl implements RolesDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Set<RolesEntity> getAllRole() {
		Session session = sessionFactory.getCurrentSession();

		TypedQuery<RolesEntity> query = session.createNamedQuery("Role.findAll", RolesEntity.class);

		//TODO : add exception control
		return new HashSet<>(query.getResultList());
	}

	@Override
	public RolesEntity getRoleByName(String rolenname) {
		Session session = sessionFactory.getCurrentSession();

		TypedQuery<RolesEntity> query = session.createNamedQuery("Role.findByName", RolesEntity.class)
				.setParameter("roleName", rolenname);

		//TODO : add exception control
		return query.getSingleResult();
	}
}
