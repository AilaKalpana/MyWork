package com.wellsfargo.fsd.ims.service;

import java.util.List;

import com.wellsfargo.fsd.ims.dao.CartItemDao;
import com.wellsfargo.fsd.ims.dao.CartItemDaoImpl;
import com.wellsfargo.fsd.ims.entity.CartItem;
import com.wellsfargo.fsd.ims.exception.ImsException;

public class CartItemServiceImpl implements CartItemService {
	
	CartItemDao cartItemDao;
	
	public CartItemServiceImpl() {
		cartItemDao = new CartItemDaoImpl();
	}
	
	@Override
	public CartItem addItem(CartItem cartItem) throws ImsException {
		
		if(cartItem != null) {
			cartItemDao.add(cartItem);
		}
		
		return cartItem;
	}

	@Override
	public List<CartItem> getAllItem(String username) throws ImsException {

		return cartItemDao.getAllItem(username);
	}

	@Override
	public CartItem getItemByUserAndId(String username, int icode) throws ImsException {
		
		return cartItemDao.getItemByUserAndId(username, icode);
	}

	@Override
	public CartItem updateItem(CartItem cartItem) throws ImsException {
		// TODO Auto-generated method stub
		return cartItemDao.update(cartItem);
	}
}
