package com.tranza.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="cart_id")
	private int cartId; 
	
	@Column(name = "grand_total")
	private double grandTotal;
	
	@Column(name = "cart_lines")
	private int cartLines;
	
	
	// linking the cart with a user
		@OneToOne
		@JoinColumn(name="user_id")
		private User user;


		public int getCartId() {
			return cartId;
		}


		public void setCartId(int cartId) {
			this.cartId = cartId;
		}


		public double getGrandTotal() {
			return grandTotal;
		}


		public void setGrandTotal(double grandTotal) {
			this.grandTotal = grandTotal;
		}


		public int getCartLines() {
			return cartLines;
		}


		public void setCartLines(int cartLines) {
			this.cartLines = cartLines;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		@Override
		public String toString() {
			return "Cart [cartId=" + cartId + ", grandTotal=" + grandTotal + ", cartLines=" + cartLines + ", user="
					+ user + "]";
		}


		

		
}
