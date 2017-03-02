package io.egen.weatherapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.weatherapi.entity.Weather;
import io.egen.weatherapi.exception.NotFoundException;
import io.egen.weatherapi.repository.WeatherRepository;
import io.egen.weatherapi.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{

	private WeatherRepository repository;
	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Weather> findAll() {
		
		return repository.findAll();
		}


	@Override
	@Transactional(readOnly = true)
	public Weather findByCity(String city) {
		return repository.findByCity(city)
				.orElseThrow(() -> new NotFoundException("City " + city + " does not exist"));
		
	}
	@Override
	@Transactional
	public Weather create(Weather user) {
		
		return repository.create(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findCities() {
		return repository.findCities();
	}

	@Override
	@Transactional(readOnly = true)
	public String findByProperty(String city, String property) {
		return repository.findByProperty(city,property);
	}


	@Override
	@Transactional /*(readOnly = true)*/
	public List<String> hourlyAvgWeather(String city) {
		return repository.hourlyAvgWeather(city);
	}

	@Override
	@Transactional /*(readOnly = true)*/
	public List<String> dailyAvgWeather(String city) {
		return repository.dailyAvgWeather(city);
	}

}
