package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.RolesEntity;

import java.util.List;

public interface RolesDao {

	List<RolesEntity> getAllRoles();
	RolesEntity getRoleByName(String roleName);
}
