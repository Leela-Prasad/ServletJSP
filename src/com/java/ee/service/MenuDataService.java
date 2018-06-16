package com.java.ee.service;

import java.util.ArrayList;
import java.util.List;

import com.java.ee.domain.MenuCategory;
import com.java.ee.domain.MenuItem;

public class MenuDataService {

	private List<MenuItem> menuItems = new ArrayList<>();
	
	public MenuDataService() {
		menuItems.add(new MenuItem(1,"Breakfast","Breakfast",MenuCategory.BREAKFAST,20.0));
		menuItems.add(new MenuItem(1,"Lunch","Lunch",MenuCategory.LUNCH,50.0));
		menuItems.add(new MenuItem(1,"Dinner","Dinner",MenuCategory.DINNER,40.0));
	}
	
	public List<MenuItem> getMenu() {
		return menuItems;
	}
	
}
