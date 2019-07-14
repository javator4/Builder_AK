package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String url =
                "http://api.apixu.com/v1/current.json" +
                        "?key=91977fee60544a1e88170057191407&q=Paris";
        // ?key=d48c0d5e40054b6a9e571834181808&q=Paris

        WeatherService weatherService = new WeatherService(
                "http://api.apixu.com/v1/current.json",
                "91977fee60544a1e88170057191407"
        );
WeatherForecast weatherForecast
        = new OrgImplementation(weatherService, "Torun");
WeatherForecast weatherForecast1
        = new FasterImplementation(weatherService, "Torun");

        System.out.println(weatherForecast.getWeather());
        System.out.println(weatherForecast1.getWeather());

      //  Current current = weatherService.getJSONData("Torun").getCityWeather();
      //  Location location = weatherService.getJSONData("Torun").getLocation();

        //  System.out.println("LAT: " + location.getLat());
        //System.out.println("LON: " + location.getLon());
        // System.out.println(current);
      /*  ObjectMapper objectMapper = new ObjectMapper();
        try {
            Weather weather = objectMapper.readValue(new URL(url), Weather.class);
            objectMapper.writeValue(new File("data.json"), weather);
            System.out.println(weather.getLocation().getCountry());
        } catch (IOException e) {
            e.printStackTrace();
        }
       */
    }

}
