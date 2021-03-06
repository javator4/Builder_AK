package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;

public class JsonDataFaster extends AbstractJsonData {
    @Override
    Weather getWeather() {

        //wklejenie z FasterImplementation
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(this.getJSONData(this.getCity()),
                    Weather.class)

            ;
            objectMapper.writeValue(new File("data.json"), weather);
            System.out.println(weather.getLocation().getCountry());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
