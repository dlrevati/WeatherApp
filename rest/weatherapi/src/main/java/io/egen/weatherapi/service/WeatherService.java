package io.egen.weatherapi.service;

import java.util.List;

import io.egen.weatherapi.entity.Weather;


public interface WeatherService {
	public List<Weather> findAll();

	public Weather findByCity(String city);
	public Weather create(Weather weather);
	public List<String> findCities();
	public String findByProperty(String city,String property);
	public List<String> hourlyAvgWeather(String city);
	public List<String> dailyAvgWeather(String city);

}
