package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.RolesEntity;

import java.util.Set;

public interface RolesDao {

	Set<RolesEntity> getAllRole();
	RolesEntity getRoleByName(String roleName);
}
