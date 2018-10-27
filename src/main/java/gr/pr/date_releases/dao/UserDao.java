
package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.UserEntity;

import java.util.List;

public interface UserDao {
	
	List<UserEntity> getAllUsers();
	UserEntity getUserByUserNameOtherThan(String userName, int userId);
	UserEntity getUserByEmailOtherThan(String userName, int userId);
	UserEntity getUserByUserNameOrEmail(String userName);
}
