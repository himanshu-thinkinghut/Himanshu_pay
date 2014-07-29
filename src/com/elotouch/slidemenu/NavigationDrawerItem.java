package com.elotouch.slidemenu;

public class NavigationDrawerItem {
	private String title;
	private int icon;
	private String count = "0";
	// boolean to set visiblity of the counter
	private boolean isCounterVisible = false;
	public NavigationDrawerItem() {
		
	}
	
	
	public NavigationDrawerItem(int icon) {
		this.icon = icon;
	}


	public NavigationDrawerItem(String title, int icon) {
		
		this.title = title;
		this.icon = icon;
	}
	public NavigationDrawerItem(String title, int icon, String count,
			boolean isCounterVisible) {
		this.title = title;
		this.icon = icon;
		this.count = count;
		this.isCounterVisible = isCounterVisible;
	}
	public String getTitle(){
		return this.title;
	}
	
	public int getIcon(){
		return this.icon;
	}
	
	public String getCount(){
		return this.count;
	}
	
	public boolean getCounterVisibility(){
		return this.isCounterVisible;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setIcon(int icon){
		this.icon = icon;
	}
	
	public void setCount(String count){
		this.count = count;
	}
	
	public void setCounterVisibility(boolean isCounterVisible){
		this.isCounterVisible = isCounterVisible;
	}

}
