package com.workout.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.workout.model.Employee;
import com.workout.model.Item;
import com.workout.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/items")
	public List<Item> getItems() {
		return itemService.getItems();
	}

	@RequestMapping("/db")
	public Employee getData() {
		return itemService.getEmployee(1);
	}

}
