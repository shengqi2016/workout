package com.workout.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.workout.DatabaseConfig;
import com.workout.model.Employee;
import com.workout.model.Item;
import com.workout.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private DatabaseConfig databaseConfig;

	private List<Item> items = new ArrayList<>(); 

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
}
