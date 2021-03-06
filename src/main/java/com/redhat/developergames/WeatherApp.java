package com.redhat.developergames;

import com.redhat.developergames.model.Weather;
import com.redhat.developergames.repository.WeatherRepository;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.remote.session.configuration.EnableInfinispanRemoteHttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@RestController
@EnableCaching
@EnableInfinispanRemoteHttpSession
public class WeatherApp {

   @Autowired
   WeatherRepository weatherRepository;

   Logger logger = LoggerFactory.getLogger(WeatherApp.class);

   @GetMapping("/")
   public String index() {
       logger.info("An INFO Message");
       System.out.println("teeeesttee");
      return "Greetings from Spring Boot with Data Grid!";
   }

   @GetMapping("/weather/{location}")
   //@Cacheable(value = "weather")
   public Object getByLocation(@PathVariable String location) {
      Weather weather = weatherRepository.getByLocation(location);
      if (weather == null) {
         return String.format("Weather for location %s not found", location);
      }
      return weather;
   }

   @GetMapping("/latest")
   public String latestLocation() {
      return "ops, I did it again!";
   }

   public static void main(String... args) {
      new SpringApplicationBuilder().sources(WeatherApp.class).run(args);
   }
}
