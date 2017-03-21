package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.entity.UserModel;

@Local
public interface UserServiceLocal {
	List<UserModel> findAllUsers();

	UserModel save(UserModel entity);

	UserModel update(UserModel entity);

	UserModel delete(UserModel entity);

	UserModel findById(Long id);
	UserModel findByUserName(String username);

	UserModel loginUser(String aUsername, String aPassword);

	UserModel checkUserExists(String username, Long id);

	List<UserModel> getAllUsersforDisplay();

}
