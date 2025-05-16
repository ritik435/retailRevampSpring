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
    @Autowired
    RedisService redisService;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;


    public String getTempInC(String place){
        log.info("Connection redis at : "+redisHost+" :::at:::: "+redisPort );
        WeatherResponse weather = redisService.get("weather_of_"+place, WeatherResponse.class);
        //check weather is in cache
        if (weather!=null){
            log.info("cache checked::: "+weather.getCurrent().getTemp_c());
            return "Temperature in " + place + ": " + weather.getCurrent().getTemp_c() + "°C";
        }else{
            log.info("called api::: ");
//        String finalAPI = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + place;
            log.info("BASE_URL : "+BASE_URL);
            log.info("API_KEY : "+API_KEY);
            String finalURL = BASE_URL
                    .replace("API_KEY", API_KEY)
                    .replace("PLACE", place);

            try {
                ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalURL, HttpMethod.GET, null, WeatherResponse.class);
                log.info(response.toString());

                WeatherResponse weatherResponse= response.getBody();
                log.info("weatherResponse : "+weatherResponse);
                redisService.set("weather_of_"+place, weatherResponse,1L);
                double feelsLike = 0.0;
                if (response.getStatusCode().is2xxSuccessful()) {
                    feelsLike = response.getBody().getCurrent().getTemp_c();
                    return "Temperature in " + place + ": " + feelsLike + "°C";
                }
            }catch (Exception e){
                log.info("exception in getTempInC : "+e.getMessage());
                e.printStackTrace();
            }

            return "Temperature in " + place + ": " + "5" + "°C";
        }
//        // Parse JSON response
//        JsonObject json = new JSONObject(response);
//        double tempC = json.getJSONObject("current").getDouble("temp_c");



    }
}
