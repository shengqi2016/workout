package com.workout.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workout.DatabaseConfig;
import com.workout.model.Item;
import com.workout.model.Member;
import com.workout.model.Profile;
import com.workout.model.Record;
import com.workout.model.User;
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
	public User getUser(int id) {
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
	public List<User> getUsers() {
		Users.clear();
		String url = databaseConfig.getUrl();
		System.out.println("DB URL:-- " + url);
		User user = null;

		try (Connection conn = DriverManager.getConnection(url)) {
			String sql = "SELECT * FROM users";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
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

		String url = databaseConfig.getUrl();
		System.out.println("Im in adduser()");
		try (Connection connection = DriverManager.getConnection(url)) {
			String sql = "Insert into users(username,email) values(?,?);";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				String username = user.getUsername();
				String email = user.getEmail();
				System.out.println("username: " + username + "|email:" + email);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, email);
				preparedStatement.executeUpdate();
				System.out.println("insert succeed");

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void Record(Record record) {
		String url = databaseConfig.getUrl();
		System.out.println("Im in record()");
		try (Connection connection = DriverManager.getConnection(url)) {

			String sql = "Insert into record(weight,times,duration,mood,calorioesburned) values(?,?,?,?,?);";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				double weight = record.getWeight();
				double times = record.getTimes();
				int duration = record.getDurattion();
				double mood = record.getMood();
				double calorioesburned = record.getCalorioesburned();
				preparedStatement.setDouble(1, weight);
				preparedStatement.setDouble(2, times);
				preparedStatement.setInt(3, duration);
				preparedStatement.setDouble(4, mood);
				preparedStatement.setDouble(5, calorioesburned);
				preparedStatement.executeUpdate();
				System.out.println("insert succeed");

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public int validation(Member member) {
		String url = databaseConfig.getUrl();
		int id = 0;
		System.out.println("Im in Validation()");
		try (Connection connection = DriverManager.getConnection(url)) {
			String sql = "Select * FROM account where username=? AND password=?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, member.getUsername());
				preparedStatement.setString(2, member.getPassword());
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						id = searchId(member.getUsername());
						System.out.println("Account found:  " + id);

					} else {
						System.out.println("Account not found.");
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return id;
	}

	public int searchId(String username) {
		String url = databaseConfig.getUrl();
		int id = 0;

		try (Connection connection = DriverManager.getConnection(url)) {
			String sql = "Select id FROM account where username=?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, username);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					id = resultSet.getInt("id");

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return id;
	}



	@Override
	public Profile getProfile(int id) {
		String url = databaseConfig.getUrl();
		Profile profile= new Profile();
		try (Connection connection = DriverManager.getConnection(url)) {
			String sql = "Select * FROM profile where id=?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, id);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					
					profile.setEmail(resultSet.getString("email"));
					profile.setAge(resultSet.getInt("Age"));
					profile.setGender(resultSet.getString("gender"));
					profile.setDate(resultSet.getString("date"));
					profile.setWeight(resultSet.getDouble("weight"));
					profile.setWeight(resultSet.getDouble("height"));

				}
				} 
			}catch (SQLException e) {
					e.printStackTrace();
		
				}
		
	


		return profile;
	}


	
}