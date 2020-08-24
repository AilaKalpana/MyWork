package com.wellsfargo.fsd.ims.dao;

import java.util.List;

import com.wellsfargo.fsd.ims.entity.CartItem;
import com.wellsfargo.fsd.ims.exception.ImsException;

public interface CartItemDao {

	CartItem add(CartItem item) throws ImsException;

	CartItem update(CartItem item) throws ImsException;

	boolean deleteById(Integer icode) throws ImsException;

	CartItem getItemByUserAndId(String username, Integer icode) throws ImsException;

	List<CartItem> getAllItem(String username) throws ImsException;
}
