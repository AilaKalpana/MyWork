package com.wellsfargo.fsd.ims.service;

import com.wellsfargo.fsd.ims.entity.User;
import com.wellsfargo.fsd.ims.exception.ImsException;

public interface UserService {
	User validateAndSaveNewUser(User user) throws ImsException;

	boolean validateUser(String username, String password) throws ImsException;
}
