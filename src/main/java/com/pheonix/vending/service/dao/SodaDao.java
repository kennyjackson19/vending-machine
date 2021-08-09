package com.pheonix.vending.service.dao;

import java.util.List;

import com.pheonix.vending.exception.VendingDataBaseException;
import com.pheonix.vending.model.Soda;

public interface SodaDao {
	public int addSoda(Soda soda)throws VendingDataBaseException, Exception ;

	public List<Soda> getSodaCount()throws VendingDataBaseException, Exception;

	public void createSoda() throws VendingDataBaseException, Exception;

	int buySoda(int buyCount) throws VendingDataBaseException, Exception;

}
