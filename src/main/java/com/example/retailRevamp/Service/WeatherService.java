package com.example.retailRevamp.Service;

import com.example.retailRevamp.Model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String API_KEY;
    private String URL="https://api.weatherapi.com/v1/current.json?key=API_KEY&q=PLACE";

    @Value("${weather.api.url}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    public String getTempInC(String place){
//        String finalAPI = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + place;
        log.info("BASE_URL : "+BASE_URL);
        log.info("API_KEY : "+API_KEY);
        String finalURL = BASE_URL
                .replace("API_KEY", API_KEY)
                .replace("PLACE", place);

        try {
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalURL, HttpMethod.GET, null, WeatherResponse.class);
            System.out.println(response.toString());
            double feelsLike = 0.0;
            if (response.getStatusCode().is2xxSuccessful()) {
                feelsLike = response.getBody().getCurrent().getFeelslike_c();
                return "Temperature in " + place + ": " + feelsLike + "°C";
            }
        }catch (Exception e){
            log.info("exception in getTempInC : "+e.getMessage());
            e.printStackTrace();
        }

        return "Temperature in " + place + ": " + "5" + "°C";
//        // Parse JSON response
//        JsonObject json = new JSONObject(response);
//        double tempC = json.getJSONObject("current").getDouble("temp_c");



    }
}
