package io.egen.weatherapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.egen.weatherapi.constants.URI;
import io.egen.weatherapi.entity.Weather;
import io.egen.weatherapi.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value=URI.WEATHER)
@Api(tags = "weather")
public class WeatherController {
	@Autowired
	private WeatherService service;

	public WeatherController(WeatherService service) {
		this.service = service;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Return All Weather data", notes = "Returns a list of weather data in the DB")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<Weather> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, params="city")
	@ApiOperation(value = "Latest weather for a given city", notes = "Returns weather data for a city if it exists in the DB")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findByCity(@RequestParam("city") String city){
		return service.findByCity(city);
	}
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Creating Weather Data", notes = "Creates a weather data in the DB by fetching data from mocker sensor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather create(@RequestBody Weather weather){
		return service.create(weather);
	}
	 @RequestMapping(method = RequestMethod.GET, value = "cities")
	 @ApiOperation(value = "List of Cities", notes = "Returns a list of cities that have reported their weather in the past")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	            @ApiResponse(code = 500, message = "Internal Server Error"), })
	        
	    public List<String> findCities() {
	        return service.findCities();
	    }
	
   @RequestMapping(method = RequestMethod.GET, params={"city","property"})
   @ApiOperation(value = "Latest weather Property for given City", notes = "Get the latest weather property for a given city")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	            @ApiResponse(code = 500, message = "Internal Server Error"), })
	  public String findByProperty(@RequestParam("city") String city,@RequestParam(required=false, value="property") String property) {
      return service.findByProperty(city,property);
  }
   @RequestMapping(method = RequestMethod.GET, value="hourly")
   @ApiOperation(value = "Hourly Averaged Weather", notes = "Get hourly averaged weather for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse(code = 500, message = "Internal Server Error"), })
   public List<String> hourlyAvgWeather(@RequestParam("city")String city) {
   
	   return service.hourlyAvgWeather(city);
   
   }
   @RequestMapping(method = RequestMethod.GET, value="daily")
   @ApiOperation(value = "Daily Averaged Weather", notes = "Get daily averaged weather for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse(code = 500, message = "Internal Server Error"), })
   public List<String> dailyAvgWeather(@RequestParam("city")String city) {
   
	   return service.dailyAvgWeather(city);
   
   }
   
   
   
  

}
