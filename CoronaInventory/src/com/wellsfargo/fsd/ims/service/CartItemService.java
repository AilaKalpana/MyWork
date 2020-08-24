package com.wellsfargo.fsd.ims.service;

import java.util.List;

import com.wellsfargo.fsd.ims.entity.CartItem;
import com.wellsfargo.fsd.ims.exception.ImsException;

public interface CartItemService {

	public CartItem addItem(CartItem cartItem) throws ImsException;
	
	public CartItem updateItem(CartItem cartItem) throws ImsException;

	public List<CartItem> getAllItem(String username) throws ImsException;

	public CartItem getItemByUserAndId(String username, int icode) throws ImsException;
}
