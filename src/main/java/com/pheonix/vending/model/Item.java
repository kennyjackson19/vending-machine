package com.pheonix.vending.model;

public class Item {
	private String name;
	private int count;
	private int canBuyCount;
	private int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCanBuyCount() {
		return canBuyCount;
	}

	public void setCanBuyCount(int canBuyCount) {
		this.canBuyCount = canBuyCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
