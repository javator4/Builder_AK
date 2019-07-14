package pl.sda;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {

    private String url;
    private String apiKey;
    private String finalURL;
    private String data = "";

    public WeatherService(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
        this.finalURL = this.url + "?key=" + apiKey + "&q=";
    }

    public String getJSONData(String city) {
        if(this.data.isEmpty()) {
            this.finalURL = this.finalURL + city;
            try {
                this.data = IOUtils.toString(new URL(this.finalURL),
                        Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public Current getCityWeather() {
        JSONObject jsonObject = new JSONObject(this.data);

        String temp = jsonObject.getJSONObject("current")
                .get("temp_c").toString();
        System.out.println(temp);

        Current current = Current.builder()
                .temp_c(Float.parseFloat(temp))
                .build();
        return current;
    }
    public Weather getWeather(){


        JSONObject jsonObject = new JSONObject(this.data);
        String lat = jsonObject.getJSONObject("location")
                .get("lat").toString();
        String lon = jsonObject.getJSONObject("location")
                .get("lon").toString();
        String temp = jsonObject.getJSONObject("current")
                .get("temp_c").toString();
        String feelslike_c = jsonObject.getJSONObject("current")
                .get("feelslike_c").toString();
        Weather weather = new Weather();
        //$
        Location location = Location.builder()
                .lat(Float.parseFloat(lat))
                .lon(Float.parseFloat(lon))
                .build();
        //&
        Current current = Current.builder()
                .temp_c(Float.parseFloat(temp))
                .feelslike_c(Float.parseFloat(feelslike_c))
                .build();

        weather.setCurrent(current);//%)
        weather.setLocation(location);//$

         return weather;
    }

    public Location getLocation() {
        JSONObject jsonObject = new JSONObject(this.data);
        String lat = jsonObject.getJSONObject("location")
                .get("lat").toString();
        String lon = jsonObject.getJSONObject("location")
                .get("lon").toString();
        Location location = Location.builder()
                .lat(Float.parseFloat(lat))
                .lon(Float.parseFloat(lon)).build();
        return location;



    }
}
