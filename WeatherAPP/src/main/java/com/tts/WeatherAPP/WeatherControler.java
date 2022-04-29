package com.tts.WeatherAPP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


//This class is going to be responsible for handling our web pages requests.

@Controller
public class WeatherControler {
	@Autowired
	WeatherService weatherService; 
	
	//This method will handle requests to "/".
	//RequestMapping will handle any type of web request. 
	//GET/PUT/POST/PATCH/DELETE
	
//@RequestMapping(value="/", method=RequestMethod.GET)
	
	@GetMapping(value="/")
	public String getIndex(Model model) {
		//a model is an object that holds key value pairs----like a hashmap.
		//specifically to hold data you want to pass between java code 
		//and your templating engine (thymeleaf).
		model.addAttribute("last", weatherService.getLastEntries());
		Request request = new Request();
		model.addAttribute("request", request);		
		return "index";
		
	}
	
	@PostMapping(value="/")
	public String postIndex(Request request, Model model) {
		Response data = weatherService.getForecast(request.getZipCode());
		model.addAttribute("last", weatherService.getLastEntries());
		model.addAttribute("data", data);
		return "index";
		
	}
	
}
