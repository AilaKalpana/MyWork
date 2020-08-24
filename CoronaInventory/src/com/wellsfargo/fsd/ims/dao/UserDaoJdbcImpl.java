package com.wellsfargo.fsd.ims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.wellsfargo.fsd.ims.entity.User;
import com.wellsfargo.fsd.ims.exception.ImsException;

public class UserDaoJdbcImpl implements UserDao {

	public static final String INS_USER_QRY = "INSERT INTO users(firstname,password,email,deliveryaddress) values(?,?,?,?)";
	public static final String UPD_USER_QRY = "UPDATE users SET deliveryaddress=?,password=?,email=? WHERE firstname=?";
	public static final String SEL_USERS_QRY = "SELECT firstname,password,email,deliveryaddress FROM users where firstname=?";

	@Override
	public String validate(String username) throws ImsException {
		String password = null;
		if (username != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement ust = con.prepareStatement(SEL_USERS_QRY)) {

				ust.setString(1, username);

				// ust.executeUpdate();
				ResultSet rs = ust.executeQuery();
				if (rs.next()) {

					password = rs.getString(2);
				}
				System.out.println("Retrived user details successfully");

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new ImsException("Added the item failed!");
			}
		}
		return password;
	}

	@Override
	public User add(User user) throws ImsException {
		if (user != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement ust = con.prepareStatement(INS_USER_QRY)) {

				ust.setString(1, user.getFirstname());
				ust.setString(2, user.getPassword());
				ust.setString(3, user.getEmail());
				ust.setString(4, user.getDeliveryAddress());

				ust.executeUpdate();
				System.out.println("Users table updated successfully");

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new ImsException("Added the item failed!");
			}
		}
		return user;
	}

	@Override
	public User save(User user) throws ImsException {
		if (user != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement ust = con.prepareStatement(UPD_USER_QRY)) {
				ust.setString(1, user.getFirstname());
				ust.setString(2, user.getPassword());
				ust.setString(3, user.getEmail());
				ust.setString(4, user.getDeliveryAddress());

				ust.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new ImsException("Saving the item failed!");
			}
		}
		return user;
	}
}
