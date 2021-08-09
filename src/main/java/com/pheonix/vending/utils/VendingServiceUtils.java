package com.pheonix.vending.utils;

import com.pheonix.vending.model.Item;

public class VendingServiceUtils {

	public static Item getSodaForQuater(int quaterCount, int sodaCount) {
		Item item = new Item();
		if (quaterCount > 0) {
			item.setCount(sodaCount);
			int canBuyCount = quaterCount / Constants.QUATER_VALUE;
			if (sodaCount > 0 && canBuyCount > 0) {
				item.setName("Soda");
				item.setCanBuyCount(canBuyCount);
				int balance = quaterCount - (canBuyCount * Constants.QUATER_VALUE);
				item.setBalance(balance);
			} else {
				item.setCanBuyCount(0);
			}
		}
		return item;
	}
}
