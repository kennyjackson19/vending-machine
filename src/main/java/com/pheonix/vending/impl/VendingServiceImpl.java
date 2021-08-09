package com.pheonix.vending.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pheonix.vending.model.Item;
import com.pheonix.vending.model.Soda;
import com.pheonix.vending.service.VendingService;
import com.pheonix.vending.service.dao.SodaDao;
import com.pheonix.vending.utils.VendingServiceUtils;

@Service
public class VendingServiceImpl implements VendingService {

	protected static final Log LOGGER = LogFactory.getLog(VendingServiceImpl.class);

	public int sodaCount;

	@Autowired
	SodaDao sodaService;

	/**
	 * method to validate quater
	 */
	@Override
	public Item validateQuater(int quaterCount) {
		getSodaCount();
		return VendingServiceUtils.getSodaForQuater(quaterCount, sodaCount);
	}

	/**
	 * method to get soda count
	 */
	@Override
	public int getSodaCount(){
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("buySoda Starts ");
		}
		try {
			List<Soda> items = sodaService.getSodaCount();
			sodaCount = items.get(0).getCount();
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("buySoda Ends ");
			}
		} catch (Exception e) {
			LOGGER.error("General Exception");
		}
		return sodaCount;
	}

	/**
	 * method to buy soda
	 */
	@Override
	public int buySoda(int count, int quaterCount) {
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("buySoda Starts ");
		}
		try{
		Item item = VendingServiceUtils.getSodaForQuater(quaterCount, count);
		if ((count > 0 && item.getCanBuyCount() > 0 && count == item.getCanBuyCount()) && sodaCount > 0) {
			sodaCount = sodaCount - count;
			int boughtCount = sodaService.buySoda(sodaCount);
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("boughtCount:: "+boughtCount);
			}
		}
		
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("buySoda Ends ");
		}
		}catch(Exception e){
			LOGGER.error("General Exception");
		}
		return sodaCount;
	}

}
