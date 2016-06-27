package example.app.bean;

import java.text.DecimalFormat;

/**
 * Weather App result data.
 * 
 * @author mo
 */
public final class WeatherResult {

	private String cityName;
	private String weatherSummary;
	private double tempertureInKelvin;
	
	public String getCityName() {
		return cityName;
	}
	
	public final void setCityName(final String cityName) {
		this.cityName = cityName;
	}
	
	public String getWeatherSummary() {
		return weatherSummary;
	}
	
	public void setWeatherSummary(final String weatherSummary) {
		this.weatherSummary = weatherSummary;
	}
	
	public double getTempertureInKelvin() {
		return tempertureInKelvin;
	}
	
	public void setTempertureInKelvin(final double tempertureInKelvin) {
		this.tempertureInKelvin = tempertureInKelvin;
	}
	
	public String getTempertureInCelsius() {
        DecimalFormat df = new DecimalFormat("#.00"); 
		return df.format(kelvinToCelsius(tempertureInKelvin));
	}

	public String getTempertureInFahrenheit() {
        DecimalFormat df = new DecimalFormat("#.00"); 
		return df.format(kelvinToFahrenheit(tempertureInKelvin));
	}
	
	private double kelvinToCelsius(final double degreeKelvin) {
		return (degreeKelvin - 273.15);
	}
	
	private double kelvinToFahrenheit(final double degreeKelvin) {
		return (((degreeKelvin - 273.15) * 9.0/5.0) + 32.0);
	}
}
