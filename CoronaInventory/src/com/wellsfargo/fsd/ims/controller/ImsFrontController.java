package com.wellsfargo.fsd.ims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.fsd.ims.entity.CartItem;
import com.wellsfargo.fsd.ims.entity.Item;
import com.wellsfargo.fsd.ims.entity.User;
import com.wellsfargo.fsd.ims.exception.ImsException;
import com.wellsfargo.fsd.ims.service.CartItemService;
import com.wellsfargo.fsd.ims.service.CartItemServiceImpl;
import com.wellsfargo.fsd.ims.service.ItemService;
import com.wellsfargo.fsd.ims.service.ItemServiceImpl;
import com.wellsfargo.fsd.ims.service.UserService;
import com.wellsfargo.fsd.ims.service.UserServiceImpl;

/**
 * Servlet implementation class ImsFrontController
 */
@WebServlet({ "/list", "/newItem", "/addItem", "/deleteItem", "/editItem", "/saveItem", "/visitor", "/addUser",
		"/login", "/addCart", "/viewCart" })
public class ImsFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ItemService itemService;
	private UserService userService;
	private CartItemService cartItemService;
	HttpSession session = null;

	@Override
	public void init() throws ServletException {
		itemService = new ItemServiceImpl();
		userService = new UserServiceImpl();
		cartItemService = new CartItemServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		String url = request.getServletPath();
		String viewName = "";

		switch (url) {
		case "/list":
			viewName = doList(request, response);
			break;
		case "/newItem":
			viewName = doNewITem(request, response);
			break;
		case "/addItem":
			viewName = doAddItem(request, response);
			break;
		case "/deleteItem":
			viewName = doDeleteItem(request, response);
			break;
		case "/editItem":
			viewName = doEditItem(request, response);
			break;
		case "/saveItem":
			viewName = doSaveItem(request, response);
			break;
		// case "/visitor":viewName=doNewUser(request, response);break;
		case "/addUser":
			viewName = doAddUser(request, response);
			break;
		case "/login":
			viewName = doValidateUser(request, response);
			break;
		case "/addCart":
			viewName = doAddtoCart(request, response);
			break;
		case "/viewCart":
			viewName = doViewCart(request, response);
			break;
		}
		request.getRequestDispatcher(viewName).forward(request, response);
	}

	private String doAddtoCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CartItem cartItem = new CartItem();
		String username = (String) session.getAttribute("username");
		cartItem.setUsername(username);
		int icode = Integer.parseInt(request.getParameter("icode"));
		cartItem.seticode(icode);
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		cartItem.setQuantity(quantity);

		String view = "";
		int totalQty = 0;
		try {
			Item item = itemService.getItemById(icode);
			int avaialbleQty = 0;
			if (item != null) {
				avaialbleQty = Integer.parseInt(item.getUnit());
				
			}
			
			CartItem dbCartItem = cartItemService.getItemByUserAndId(username, icode);
			int dbQuantity = 0;
			if (dbCartItem != null) {
				dbQuantity = dbCartItem.getQuantity();
				totalQty = dbQuantity + quantity;
				if (totalQty > avaialbleQty) {
					view = "errPage.jsp";
					throw new ImsException("Quantity exceeds the available units");
				} else {
					
					// reduce the quantity from db
					item.setUnit(new Integer(avaialbleQty - quantity).toString());
					itemService.validateAndSave(item);
					
					//cartItem.setTitle(item.getTitle());
					dbCartItem.setQuantity(totalQty);
					dbCartItem.setCostPrice(totalQty * item.getCostPrice());
					cartItemService.updateItem(dbCartItem);
					
					List<CartItem> cartItems = new ArrayList<CartItem>();
					cartItems.add(dbCartItem);
					request.setAttribute("cartItems", cartItems);
				}
				
			} else {
				
					
				// reduce the quantity from db
				item.setUnit(new Integer(avaialbleQty - quantity).toString());
				itemService.validateAndSave(item);
			
				cartItem.seticode(item.geticode());
				cartItem.setTitle(item.getTitle());
				cartItem.setQuantity(quantity);
				cartItem.setCostPrice(item.getCostPrice());
				cartItemService.addItem(cartItem);

				List<CartItem> cartItems = new ArrayList<CartItem>();
				cartItems.add(cartItem);
				request.setAttribute("cartItems", cartItems);
			}

			view = "ViewCart.jsp";
		} catch (ImsException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String doViewCart(HttpServletRequest request, HttpServletResponse response) {

		String username = (String) session.getAttribute("username");
		String view = "";

		try {
			List<CartItem> cartItems = cartItemService.getAllItem(username);
			request.setAttribute("cartItems", cartItems);

			view = "ViewCart.jsp";
		} catch (ImsException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String doValidateUser(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String view = "";
		try {
			boolean isValid = userService.validateUser(username, password);

			session.setAttribute("username", username);
			session.setAttribute("role", "user");
			if (isValid) {
				if (username.equals("admin")) {
					// String role = "admin";
					session.setAttribute("role", "admin");
				}
				view = "itemsListPage.jsp";
			} else {
				request.setAttribute("msg", "Invalid username or password");
				view = "errPage.jsp";
			}

		} catch (ImsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}

	private String doAddUser(HttpServletRequest request, HttpServletResponse response) {

		User user = new User();
		user.setfirstname(request.getParameter("firstname"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setDeliveryAddress(request.getParameter("deliveryaddress"));

		String view = "";
		try {
			User dbUser = userService.validateAndSaveNewUser(user);
			request.setAttribute("msg", "Welcome " + dbUser.getFirstname() + "! Please login");
			view = "Login.jsp";
		} catch (ImsException e) {
			System.out.println("Exception");
			e.printStackTrace();
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String doList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = "";

		try {
			List<Item> items = itemService.getAllItems();
			request.setAttribute("items", items);
			view = "itemsListPage.jsp";
		} catch (ImsException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}

	private String doNewITem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Item item = new Item();
		request.setAttribute("item", item);

		return "itemFormPage.jsp";
	}

	private String doAddItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Item item = new Item();

		item.seticode(Integer.parseInt(request.getParameter("icode")));
		item.setTitle(request.getParameter("title"));
		item.setUnit(request.getParameter("unit"));
		item.setCostPrice(Double.parseDouble(request.getParameter("costPrice")));

		String view = "";

		try {
			itemService.validateAndAdd(item);
			request.setAttribute("msg", "Item Got Added!");
			view = "index.jsp";
		} catch (ImsException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String doDeleteItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int icode = Integer.parseInt(request.getParameter("icode"));
		String view = "";

		try {
			itemService.deleteItem(icode);
			request.setAttribute("msg", "Item Got Deleted!");
			view = "index.jsp";
		} catch (ImsException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String doEditItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int icode = Integer.parseInt(request.getParameter("icode"));
		String view = "";

		try {
			Item item = itemService.getItemById(icode);
			request.setAttribute("item", item);
			view = "itemFormPage.jsp";
		} catch (ImsException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String doSaveItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Item item = new Item();

		item.seticode(Integer.parseInt(request.getParameter("icode")));
		item.setTitle(request.getParameter("title"));
		// item.setFragile(Boolean.parseBoolean(request.getParameter("fragile")));
		item.setUnit(request.getParameter("unit"));
		// item.setPackageDate(LocalDate.parse(request.getParameter("packageDate")));
		item.setCostPrice(Double.parseDouble(request.getParameter("costPrice")));
		// item.setSellingPrice(Double.parseDouble(request.getParameter("sellingPrice")));

		String view = "";

		try {
			itemService.validateAndSave(item);
			request.setAttribute("msg", "Item Got Saved!");
			view = "index.jsp";
		} catch (ImsException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}
}
