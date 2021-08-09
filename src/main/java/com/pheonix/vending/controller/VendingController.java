package com.pheonix.vending.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pheonix.vending.model.Item;
import com.pheonix.vending.service.VendingService;

@RestController
public class VendingController {

	protected static final Log LOGGER = LogFactory.getLog(VendingController.class);

	@Autowired
	private VendingService vendingService;

	@RequestMapping("/")
	public ModelAndView login() {
		LOGGER.info("inside login");
		ModelAndView mv = new ModelAndView();
		try {
			int initialCount = vendingService.getSodaCount();
			mv.setViewName("login.jsp");
			mv.addObject("sodaCount", initialCount);
		} catch (Exception e) {
			LOGGER.error("General Exception");
		}
		return mv;
	}

	@RequestMapping(value = "/getSoda/{count}/{quaterCount}")
	public int getSoda(@PathVariable("count") int count, @PathVariable("quaterCount") int quaterCount) {
		System.out.println("inside getSoda" + count);
		int remainingCount = vendingService.buySoda(count, quaterCount);
		System.out.println("after getSoda" + remainingCount);
		return remainingCount;
	}

	@RequestMapping(value = "/validateQuater/{quaterCount}")
	public Item validateQuater(@PathVariable("quaterCount") int quaterCount) {
		return vendingService.validateQuater(quaterCount);
	}

}
