package example.app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for processing request from Weather App page.
 * 
 * @author mo
 */
@Controller
public class WeatherAppController {

    private static HttpClient client = HttpClientBuilder.create().build();

    private static final String PAGE_WEATHER_FORM = "weatherForm";
    private static final String FORM_WEATHER = "weatherFrom";
    private static final String RESULT_WEATHER = "formResult";

    private static final String URL_FOR_OPENWEATEHRMAP = "http://api.openweathermap.org/data/2.5/forecast/";
    
    private String getUrlForOpenWeatherMap(final String cityName) throws UnsupportedEncodingException {
    	StringBuilder url = new StringBuilder();
    	url.append(URL_FOR_OPENWEATEHRMAP);
    	url.append("city?q=").append(URLEncoder.encode(cityName, "UTF-8"));
    	url.append("&APPID=e62b77f0f2abe6ee39d6a88f820f79a6");	
    	return url.toString();
    }
    
    public WeatherResult getDetailsForCity(final String cityName) {
    	
    	WeatherResult weatherResult = new WeatherResult();
        try {
        	HttpGet request = new HttpGet(
        		getUrlForOpenWeatherMap(cityName)
        	);
            
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity());
            
            JSONObject jsonObj = new JSONObject(content);
            
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
        	
        	/** @TODO log using log4j */
            e.printStackTrace();
        }
        
        return weatherResult;
    }
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ModelAndView weather() {
		return new ModelAndView(PAGE_WEATHER_FORM, FORM_WEATHER, new WeatherForm());
	}
	  
	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public String weatherResult(@ModelAttribute(FORM_WEATHER) WeatherForm form, ModelMap model) {
		
		WeatherResult result = null;
		if (StringUtils.isEmpty(form.getCityName().trim()) == false) {
			result = getDetailsForCity(form.getCityName());
		}
		model.addAttribute(RESULT_WEATHER, result);
		return PAGE_WEATHER_FORM;
	}
	
}
