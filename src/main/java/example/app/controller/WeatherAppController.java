package example.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import example.app.bean.WeatherForm;
import example.app.bean.WeatherResult;
import example.app.service.WeatherAppService;

/**
 * Controller for processing request from Weather App page.
 * 
 * @author mo
 */
@Controller
public class WeatherAppController {

	/** the logger. */
	private static Logger LOGGER = Logger.getLogger(WeatherAppController.class);
	
    private static final String PAGE_WEATHER_FORM = "weatherForm";
    private static final String FORM_WEATHER = "weatherFrom";
    private static final String FORM_RESULT_WEATHER = "formResult";


    @Autowired
    @Qualifier("OpenWeatherMap")
    private WeatherAppService weatherService;

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ModelAndView weather() {
		return new ModelAndView(PAGE_WEATHER_FORM, FORM_WEATHER, new WeatherForm());
	}
	  
	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public String weatherResult(final @ModelAttribute(FORM_WEATHER) WeatherForm form, final ModelMap model) {
		
		WeatherResult result = null;
		if (StringUtils.isEmpty(form.getCityName().trim()) == false) {
			result = weatherService.getWeatherForCity(form.getCityName());
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("weather request for city-name='" + form.getCityName() + "' result =" + result);
		}
		
		model.addAttribute(FORM_RESULT_WEATHER, result);
		return PAGE_WEATHER_FORM;
	}
	
}
