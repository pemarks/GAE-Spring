package net.magus.example.web.controllers;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/main", "/"})
public class MainController {
	private static Logger logger = Logger.getLogger(MainController.class);
	
	@PostConstruct
	private void init(){}
	
	@RequestMapping
	public String getMain(){		
		logger.debug("Getting the main page");
		return "main";
	}
}

