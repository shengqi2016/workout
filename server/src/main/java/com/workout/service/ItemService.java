package com.workout.service;

import java.util.List;

import com.workout.model.Employee;
import com.workout.model.Item;
public interface ItemService {

	List<Item> getItems();

	Employee getEmployee(long id);
	
}
