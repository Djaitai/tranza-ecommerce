package com.tranza.ecommerce.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tranza.ecommerce.dao.UserDAO;
import com.tranza.ecommerce.model.Address;
import com.tranza.ecommerce.model.Cart;
import com.tranza.ecommerce.model.Product;
import com.tranza.ecommerce.model.User;

@Repository(value="userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private User user;
	
	@Override
	public User getUserByEmail(String emailId) {
		String selectQuery = "FROM User WHERE emailId = :emailId";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,User.class)
						.setParameter("emailId",emailId)
							.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public User getUserById(int userId) {
		try {
			user = sessionFactory.getCurrentSession().get(User.class, userId);
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
/*	@Override
	public User getUserByContactNumber(String contactNumber) {
		try {
			user = sessionFactory.getCurrentSession().get(User.class, contactNumber);
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}*/

	@Override
	public boolean addUser(User user) {
		try {			
			sessionFactory.getCurrentSession().persist(user);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	

	@Override
	public boolean addAddress(Address address) {
		try {			
			// will look for this code later and why we need to change it
			sessionFactory.getCurrentSession().persist(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
	}
	}

	@Override
	public boolean updateAddress(Address address) {
		try {			
			sessionFactory.getCurrentSession().update(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND billing = :isBilling";
		try{
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isBilling", true)
						.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddresses(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :isShipping ORDER BY id DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isShipping", true)
							.getResultList();
	}


}
