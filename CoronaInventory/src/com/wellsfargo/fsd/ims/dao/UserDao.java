package com.wellsfargo.fsd.ims.dao;

import com.wellsfargo.fsd.ims.entity.User;
import com.wellsfargo.fsd.ims.exception.ImsException;

public interface UserDao {
	User add(User user) throws ImsException;

	User save(User user) throws ImsException;

	String validate(String username) throws ImsException;
}
