package com.pheonix.vending.service;

import com.pheonix.vending.model.Item;

public interface VendingService {
	
	public Item validateQuater(int quaterCount);

	public int getSodaCount() throws Exception ;

	public int buySoda(int count, int quaterCount);

}
