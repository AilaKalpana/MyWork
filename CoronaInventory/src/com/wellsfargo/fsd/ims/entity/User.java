package com.wellsfargo.fsd.ims.entity;

import java.time.LocalDate;

public class User {

		private String firstname;
		private String password;
		private String email;
		private String deliveryaddress;
		
		public User() {
			
		}

		public User(String firstname,String password,String email,String deliveryaddress) {
			super();
			this.firstname = firstname;
			this.password = password;
			this.email = email;
			this.deliveryaddress = deliveryaddress;
			
		}

		
		
		public String getFirstname() {
			return firstname;
		}

		public void setfirstname(String firstname) {
			this.firstname = firstname;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getDeliveryAddress() {
			return deliveryaddress;
		}

		public void setDeliveryAddress(String deliveryaddress) {
			this.deliveryaddress = deliveryaddress;
		}

		

		@Override
		public String toString() {
			return "User [FirstName =" +firstname +",email =" +email +",deliveryaddress =" +deliveryaddress+"]";
		}
	}

