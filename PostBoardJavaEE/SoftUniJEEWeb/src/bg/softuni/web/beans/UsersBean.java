package bg.softuni.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.dto.UserDto;

@ManagedBean(name = "usersBean")
@SessionScoped
public class UsersBean {

	private List<UserDto> users;

	@PostConstruct
	public void init() {
		// initialize the users list
		users = new ArrayList<UserDto>();

		// create user
		UserDto initUser = new UserDto("admin", "123", "Test", "Tester", "test@gmail.com");

		// add user the created user to the list
		users.add(initUser);
		
		UserDto newser = new UserDto("regularUser", "123", "Ivan", "Ivanov", "ivan@gmail.com");
		users.add(newser);

	}

	public UserDto validateUser(String username, String password) {

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}

		for (UserDto currentUser : users) {
			if (username.equals(currentUser.getUsername()) && password.equals(currentUser.getPassword())) {
				return currentUser;
			}
		}

		return null;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

}

