package pl.sda;

import lombok.Data;
import org.apache.commons.io.IOUtils;
import pl.sda.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
//dodanie getterow i setterow :
@Data

public abstract class AbstractJsonData {

    //pola z WeatherService:
    //czesc wspolna z klasa weatherService i klasa abstrakcyjna
    private String url = "http://api.apixu.com/v1/current.json";
    private String apiKey = "91977fee60544a1e88170057191407";
    private String finalURL;
    private String data = "";
    private String city;


//robimy konstruktor:
    public AbstractJsonData() {
        this.finalURL = this.url + "?key=" + apiKey + "&q=";
    }
    public void build(){
        this.finalURL = this.url + "?key=" + apiKey + "&q=";
    }
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
