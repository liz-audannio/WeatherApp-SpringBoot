package com.tts.WeatherAPP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//This class is going to be a utility class that is used to make an API call.
//Very common in SpringBootApps to create 'service' classss that are essentially utility classes.

@Service
public class WeatherService {
	@Autowired
	RequestRepository zipCodeRepository;
	@Value("${api_key}")
	private String apiKey;

	//The correct way to build a url is to use a builtin java library. 
	//URIcomponents builder
	
	public Response getForecast(String zipCode) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("api.openweathermap.org")
				.path("/data/2.5/weather")
				.queryParam("zip",  zipCode)
				.queryParam("units",  "imperial")
				.queryParam("appid",  apiKey)
				.build();
		String url = uriComponents.toUriString();
	
		
		//Any time you want to make a REST API call or just generally any request
		//over HHTP via SB you create a RestTemplate.
		RestTemplate restTemplate = new RestTemplate();
		
		//We want to do a HTTP GET to get to the page
		//LOOKUP JAVA EXCEPTION HANDLING
		Response response;
		try {
			response = restTemplate.getForObject(url, Response.class);
		}
		catch (HttpClientErrorException e) {
			response = new Response();
			response.setName("error");
		}
		return response;
	
	}
	public List<ZipCode> getLastEntries()
	{
		Pageable limit = PageRequest.of(0, 10);
		return zipCodeRepository.findAllByOrderByIdAsc(limit);
	}
	
}