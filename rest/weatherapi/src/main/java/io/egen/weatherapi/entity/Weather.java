package io.egen.weatherapi.entity;

import java.util.Map;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@NamedQueries({
	@NamedQuery(name="Weather.findAll", query="SELECT w FROM Weather w ORDER BY w.city"),
	@NamedQuery(name="Weather.findByCity", query="SELECT w FROM Weather w where w.city=:wCity"),
	@NamedQuery(name="Weather.findCities",query="SELECT DISTINCT(w.city) FROM Weather w ORDER BY w.city"),
	@NamedQuery(name="Weather.findHourly",query="SELECT avg(w.temperature) FROM Weather w WHERE w.city=:wCity GROUP BY Hour(w.timestamp)"),
	@NamedQuery(name="Weather.findDaily",query="SELECT avg(w.temperature) FROM Weather w WHERE w.city=:wCity GROUP BY Day(w.timestamp)")
	/*SELECT avg(weatherdb.weather.temperature), hour(weatherdb.weather.timestamp) from weatherdb.weather where weatherdb.weather.city='Austin' GROUP BY Hour(weatherdb.weather.timestamp);
	*/
	/*@NamedQuery(name="Weather.findHourly",query="SELECT avg(w.temperature) AS Temp FROM Weather w WHERE DATE_FORMAT(w.timestamp, '%Y-%m-%d %H:00:00') >= DATE_FORMAT(DATE_ADD(NOW(), INTERVAL -1 DAY), '%Y-%m-%d %H:00:00') AND w.city=:wCity  GROUP BY  DATE_FORMAT(w.timestamp, '%Y-%m-%d %H:00:00')  ")*/
	
	
})
public class Weather {
	@Id
	String id;
	
	String city;
	String description;
	String humidity;
	String pressure;
	String temperature;
	String wind_speed;
	String wind_degree;
	String timestamp;
	public Weather() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(String wind_speed) {
		this.wind_speed = wind_speed;
	}
	public String getWind_degree() {
		return wind_degree;
	}
	public void setWind_degree(String wind_degree) {
		this.wind_degree = wind_degree;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@JsonProperty("wind")
	 	private void unpackNameFromNestedObject(Map<String, String> wind) {
	 	    setWind_speed(wind.get("speed"));
	 	    setWind_degree(wind.get("degree"));
	 	}
	

}
