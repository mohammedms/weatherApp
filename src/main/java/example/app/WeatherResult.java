package example.app;

import java.text.DecimalFormat;

/**
 * Weather App result data.
 * 
 * @author mo
 */
public class WeatherResult {

	private String cityName;
	private String weatherSummary;
	private double tempertureInKelvin;
	
	
	public final String getCityName() {
		return cityName;
	}
	
	public final void setCityName(final String cityName) {
		this.cityName = cityName;
	}
	
	public final String getWeatherSummary() {
		return weatherSummary;
	}
	
	public final void setWeatherSummary(final String weatherSummary) {
		this.weatherSummary = weatherSummary;
	}
	
	public final double getTempertureInKelvin() {
		return tempertureInKelvin;
	}
	
	public final void setTempertureInKelvin(final double tempertureInKelvin) {
		this.tempertureInKelvin = tempertureInKelvin;
	}
	
	public final String getTempertureInCelsius() {
        DecimalFormat df = new DecimalFormat("#.##"); 
		return df.format(kelvinToCelsius(tempertureInKelvin));
	}

	public final String getTempertureInFahrenheit() {
        DecimalFormat df = new DecimalFormat("#.##"); 
		return df.format(kelvinToFahrenheit(tempertureInKelvin));
	}
	
	private double kelvinToCelsius(double degreeKelvin) {
		return (degreeKelvin - 273.15);
	}
	
	private double kelvinToFahrenheit(double degreeKelvin) {
		return (((degreeKelvin - 273.15) * 9.0/5.0) + 32.0);
	}
}
