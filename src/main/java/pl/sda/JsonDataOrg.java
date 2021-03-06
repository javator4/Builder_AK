package pl.sda;

import lombok.Data;
import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;
@Data

public class JsonDataOrg extends AbstractJsonData {
    private  WeatherService weatherService;
    private String city;
    @Override
    Weather getWeather() {
        JSONObject jsonObject =
                new JSONObject(this.getJSONData(this.city));
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

        weather.setCurrent(current);//%
        weather.setLocation(location);//$

        return weather;

    }
}
