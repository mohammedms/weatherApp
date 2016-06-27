package example.app.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import example.app.bean.WeatherResult;

/**
 * Implementation of weather data from http://openweathermap.org/.
 * 
 * @author mo
 */
@Service("OpenWeatherMap")
public class OpenWeatherMapImpl implements WeatherAppService  {

	/** the logger. */
	private static Logger LOGGER = Logger.getLogger(OpenWeatherMapImpl.class);
	
	/** URL to the Open Weather Map. */
    private static final String URL_FOR_OPENWEATEHRMAP = "http://api.openweathermap.org/data/2.5/forecast/";

    /** prepare to make HTTP requests. */
    private static final HttpClient client = HttpClientBuilder.create().build();
    
	@Override
	public WeatherResult getWeatherForCity(final String cityName) {
		
    	WeatherResult weatherResult = null;
        try {

        	HttpGet request = new HttpGet(
        		getUrlForOpenWeatherMap(cityName)
        	);
            
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity());
            
            JSONObject jsonObj = new JSONObject(content);
            
            weatherResult = new WeatherResult();
            weatherResult.setCityName(
            	jsonObj.getJSONObject("city").getString("name")
            );
            weatherResult.setWeatherSummary(
            	jsonObj.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description")
            );
            weatherResult.setTempertureInKelvin(
           		jsonObj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp")
            );

        } catch (IOException e) {
        	LOGGER.error(e.getMessage(), e);
        }
        
        return weatherResult;
	}
    
    private String getUrlForOpenWeatherMap(final String cityName) throws UnsupportedEncodingException {
    	StringBuilder url = new StringBuilder();
    	url.append(URL_FOR_OPENWEATEHRMAP);
    	url.append("city?q=").append(URLEncoder.encode(cityName, "UTF-8"));
    	url.append("&APPID=e62b77f0f2abe6ee39d6a88f820f79a6");	
    	return url.toString();
    }

}
