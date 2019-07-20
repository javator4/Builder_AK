package pl.sda;

import org.apache.commons.io.IOUtils;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class AbstractJsonData {

    //pola z WeatherService:
    //czesc wspolna z klasa weatherService i klasa abstrakcyjna
    private String url;
    private String apiKey;
    private String finalURL;
    private String data = "";

//skopiowanie z WeatherService:
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


    //tu taką sygnaturę można tworzyć
    abstract Weather getWeather();
}
