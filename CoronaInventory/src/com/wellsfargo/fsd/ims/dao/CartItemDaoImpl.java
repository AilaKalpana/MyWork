package com.wellsfargo.fsd.ims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.fsd.ims.entity.CartItem;
import com.wellsfargo.fsd.ims.exception.ImsException;

public class CartItemDaoImpl implements CartItemDao {

	public static final String INS_CART_ITEM_QRY = "INSERT INTO cartitem(title, username, icode, unit, costprice) values(?,?,?,?,?)";
	public static final String SEL_CART_ITEM_BY_ID_QRY = "SELECT title, username, icode, unit, costprice FROM cartitem where username=? and icode=?";
	public static final String UPD_CART_ITEM_BY_ID_QRY = "UPDATE cartitem SET unit=?, costprice=? where username=? and icode=?";
	public static final String SEL_CART_ITEMS_BY_USER_QRY = "SELECT title, username, icode, unit, costprice FROM cartitem where username=?";

	@Override
	public CartItem add(CartItem item) throws ImsException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement cst = con.prepareStatement(INS_CART_ITEM_QRY)) {

				cst.setString(1, item.getTitle());
				cst.setString(2, item.getUsername());
				cst.setLong(3, item.geticode());
				cst.setLong(4, item.getQuantity());
				cst.setDouble(5, item.getCostPrice());

				cst.executeUpdate();
				System.out.println("Add items to cart successfully");

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new ImsException("Added the item failed!");
			}
		}
		return item;
	}

	@Override
	public boolean deleteById(Integer icode) throws ImsException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CartItem getItemByUserAndId(String username, Integer icode) throws ImsException {
		CartItem item = null;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_CART_ITEM_BY_ID_QRY)) {
			pst.setString(1, username);
			pst.setLong(2, icode);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				item = new CartItem();
				item.setTitle(rs.getString(1));
				item.setUsername(rs.getString(2));
				item.seticode(Integer.parseInt(rs.getString(3)));
				item.setQuantity(Integer.parseInt(rs.getString(4)));
				item.setCostPrice(rs.getDouble(5));
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new ImsException("Retrival the item failed!");
		}
		return item;
	}

	@Override
	public List<CartItem> getAllItem(String username) throws ImsException {
		List<CartItem> items = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_CART_ITEMS_BY_USER_QRY)) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				CartItem item = new CartItem();
				item.setTitle(rs.getString(1));
				item.setUsername(rs.getString(2));
				item.seticode(Integer.parseInt(rs.getString(3)));
				item.setQuantity(Integer.parseInt(rs.getString(4)));
				item.setCostPrice(rs.getDouble(5));

				items.add(item);
			}

			if (items.isEmpty()) {
				items = null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new ImsException("Retrival the item failed!");
		}
		return items;
	}

	@Override
	public CartItem update(CartItem cartItem) throws ImsException {
		CartItem item = null;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(UPD_CART_ITEM_BY_ID_QRY)) {
			
			pst.setLong(1, cartItem.getQuantity());
			pst.setDouble(2, cartItem.getCostPrice());
			pst.setString(3, cartItem.getUsername());
			pst.setLong(4, cartItem.geticode());
			
			pst.executeUpdate();

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new ImsException("Retrival the item failed!");
		}
		return item;
	}

}
