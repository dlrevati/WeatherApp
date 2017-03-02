package io.egen.weatherapi.repository.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List; 
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.weatherapi.entity.Weather;
import io.egen.weatherapi.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository{
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<Weather> findAll() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		return query.getResultList();
	}

	@Override
	public Optional<Weather> findByCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("wCity", city);
		List<Weather> weather = query.getResultList();
		if (!weather.isEmpty()) {
			return Optional.of(weather.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Weather create(Weather weather) {
		em.persist(weather);
		return weather;
	}
	@Override
	public List<String> findCities() {
		TypedQuery<String> query = em.createNamedQuery("Weather.findCities", String.class);
		List<String> weatherList= query.getResultList();
		return weatherList;
	}

	@Override
	public String findByProperty(String city, String property) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("wCity", city);
		List<Weather> weather = query.getResultList();
		String propertyValue = null;
		if (!weather.isEmpty()) {
			if(property.equals("temperature")){
				propertyValue= (String) weather.get(0).getTemperature();
			}
			else if(property.equals("pressure")){
				propertyValue= (String) weather.get(0).getPressure();
				}
			else if(property.equals("humidity")){
				propertyValue= (String) weather.get(0).getHumidity();
				}
			else if(property.equals("wind_speed")){
				propertyValue= (String) weather.get(0).getWind_speed();
				}
			else if(property.equals("wind_degree")){
				propertyValue= (String)weather.get(0).getWind_degree();
				}
		return propertyValue;
			
		} else {
			return null;
		}
		
	}

	@Override
	public List<String> hourlyAvgWeather(String city) {
		TypedQuery<Double> query = em.createNamedQuery("Weather.findHourly", Double.class);
		query.setParameter("wCity", city);
		List<Double> temperature = query.getResultList();
		List<String> avgTemp= new ArrayList<String>();
		if (!temperature.isEmpty()) {
			for(Double t:temperature){
				String val=new DecimalFormat("#.##").format(t);
				avgTemp.add(val);
			}
			return avgTemp;
		}
		else{
			return null;
		}
		
	}

	@Override
	public List<String> dailyAvgWeather(String city) {
		TypedQuery<Double> query = em.createNamedQuery("Weather.findDaily", Double.class);
		query.setParameter("wCity", city);
		List<Double> temperature = query.getResultList();
		List<String> avgTemp= new ArrayList<String>();
		if (!temperature.isEmpty()) {
			for(Double t:temperature){
				String val=new DecimalFormat("#.##").format(t);
				avgTemp.add(val);
			}
			return avgTemp;
		}
		else{
			return null;
		}
	}

	
}
