package com.wellsfargo.fsd.ims.service;

import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.fsd.ims.dao.UserDao;
import com.wellsfargo.fsd.ims.dao.UserDaoJdbcImpl;
import com.wellsfargo.fsd.ims.entity.User;
import com.wellsfargo.fsd.ims.exception.ImsException;

public class UserServiceImpl implements UserService {
	UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoJdbcImpl();
	}

	private boolean isfirstnameValid(String firstname) {
		return firstname != null;
	}

	private boolean isemailValid(String email) {
		return email != null;
	}

	private boolean isdeliveryaddress(String deliveryaddress) {
		return deliveryaddress != null;
	}

	private boolean isValidNewUser(User user) throws ImsException {
		List<String> errMsg = new ArrayList<>();

		boolean isValid = true;

		if (!isfirstnameValid(user.getFirstname())) {
			isValid = false;
			errMsg.add("FirstName can not be null ");
		}

		if (!isemailValid(user.getEmail())) {
			isValid = false;
			errMsg.add("Email can not be blank");
		}

		if (!isdeliveryaddress(user.getDeliveryAddress())) {
			isValid = false;
			errMsg.add("DeliveryAddress can not be null");
		}

		if (!isValid) {
			throw new ImsException(errMsg.toString());
		}

		return isValid;
	}

	public User validateAndSaveNewUser(User user) throws ImsException {
		if (user != null) {
			if (isValidNewUser(user)) {
				userDao.add(user);
			}
		}
		return user;
	}

	public boolean validateUser(String username, String password) throws ImsException {
		
		String dbPassword = null;
		if(username != null) {
			dbPassword = userDao.validate(username);
		}
		if (password.equals(dbPassword)) {
			return true;
		}
		return false;
	}

}
