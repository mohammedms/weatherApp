package example.app.bean;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import example.app.bean.WeatherResult;

public class WeatherResultTest {

	private WeatherResult weatherResult;
	
    @Before
    public void setup() {
    	weatherResult = new WeatherResult();
    }
    
    @After
    public void teardown() {
    	weatherResult = null;	
    }
	
    @Test
    public void test_getTempertureInCelsius() {

    	weatherResult.setTempertureInKelvin(0);
    	Assert.assertEquals("-273.15", weatherResult.getTempertureInCelsius());
    	
    	weatherResult.setTempertureInKelvin(298.15);
    	Assert.assertEquals("25.00", weatherResult.getTempertureInCelsius());
    	
    	weatherResult.setTempertureInKelvin(300.65);
    	Assert.assertEquals("27.50", weatherResult.getTempertureInCelsius());
    }
    
    @Test
    public void test_getTempertureInFahrenheit() {
    	
    	weatherResult.setTempertureInKelvin(0);
    	Assert.assertEquals("-459.67", weatherResult.getTempertureInFahrenheit());
    	
    	weatherResult.setTempertureInKelvin(298.15);
    	Assert.assertEquals("77.00", weatherResult.getTempertureInFahrenheit());
    	
    	weatherResult.setTempertureInKelvin(300.65);
    	Assert.assertEquals("81.50", weatherResult.getTempertureInFahrenheit());
    }
}
