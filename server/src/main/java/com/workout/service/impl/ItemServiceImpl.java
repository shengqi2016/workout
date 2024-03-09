package com.workout.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirObjectFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.workout.DatabaseConfig;
import com.workout.model.User;
import com.workout.model.Employee;
import com.workout.model.Item;
import com.workout.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private DatabaseConfig databaseConfig;

	private List<Item> items = new ArrayList<>(); 
	private List<User> Users = new ArrayList<>(); 
	public ItemServiceImpl() {
		items.add(new Item(0, "Item 1"));
		items.add(new Item(1, "Item 2"));
		items.add(new Item(2, "Item 3"));
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public Employee getEmployee(long id) {
		String url = databaseConfig.getUrl();
		System.out.println("DB URL: " + url);
		Employee employee = null;

		try (Connection conn = DriverManager.getConnection(url)) {
			String sql = "SELECT * FROM employees WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				employee = new Employee(id, name, email, age);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employee;
	}

	@Override
	public User getUser(int id){
		String url = databaseConfig.getUrl();
		System.out.println("DB URL: " + url);
		User user = null;

		try (Connection conn = DriverManager.getConnection(url)) {
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String username = rs.getString("username");
				String email = rs.getString("email");
				user = new User(id, username, email);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return user;


	}

	@Override
	public List<User> getUsers(){
		Users.clear();
		String url= databaseConfig.getUrl();
		System.out.println("DB URL:-- " + url);
		User user=null;
		
		try (Connection conn = DriverManager.getConnection(url)) {
			String sql = "SELECT * FROM users";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
				int id=rs.getInt("id");
				String username = rs.getString("username");
				String email = rs.getString("email");
				user = new User(id, username, email);
				Users.add(user);
				}
			}			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
			return Users;
	}


	@Override
	public void AddUser(User user) {

		String url= databaseConfig.getUrl();
	
		System.out.println("Im in adduser()");
		try (Connection connection = DriverManager.getConnection(url)) {
			String sql="Insert into users(username,email) values(?,?);";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				
				String username=user.getUsername();
				String email=user.getEmail();
				System.out.println("username: "+username+"|email:"+email);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, email);			 
			preparedStatement.executeUpdate();
			System.out.println("insert succeed");

		}

	
	} catch (SQLException e) {
	e.printStackTrace();
		
	}

}
}