package example.app.service;

import example.app.bean.WeatherResult;


/**
 * Service method for getting Weather data.
 * 
 * @author mo
 */
public interface WeatherAppService {

	/**
	 * Get Weather details based on the cityName.
	 * 
	 * @param cityName city name
	 * @return {@link WeatherResult}
	 */
	WeatherResult getWeatherForCity(final String cityName);
    
}
