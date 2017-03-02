package io.egen.weatherapi.repository;

import java.util.List;
import java.util.Optional;

import io.egen.weatherapi.entity.Weather;


public interface WeatherRepository {
	public List<Weather> findAll();

	public Optional<Weather> findByCity(String city);
	public Weather create(Weather weather);
	public List<String> findCities();
	public String findByProperty(String city,String property);
	public List<String> hourlyAvgWeather(String city);
	public List<String> dailyAvgWeather(String city);
	

}
