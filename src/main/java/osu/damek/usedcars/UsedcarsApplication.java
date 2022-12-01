package osu.damek.usedcars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import osu.damek.usedcars.model.*;
import osu.damek.usedcars.repository.CarRepository;
import osu.damek.usedcars.repository.MotorcycleRepository;
import osu.damek.usedcars.repository.TagRepository;
import osu.damek.usedcars.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class UsedcarsApplication {
	Logger loggerS = LoggerFactory.getLogger(UsedcarsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UsedcarsApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
				corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
						"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
						"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,
										TagRepository tagRepository,
										CarRepository carRepository,
										MotorcycleRepository motorcycleRepository
	) {
		return args -> {
			List<User> users = List.of(
					new User("admin", "admin", Role.ADMIN),
					new User("user", "user", Role.USER)
			);
			loggerS.info("2 users were created");

			List<Tag> tags = List.of(
					new Tag("tagA", users.get(0), new ArrayList<>(), new ArrayList<>()),
					new Tag("tagU", users.get(1), new ArrayList<>(), new ArrayList<>())
			);
			loggerS.info("2 tags were created");

			List<Car> cars = List.of(
					new Car("car-brand1", "car-type1", 100D, "car-image1", "no fuel", users.get(0), List.of(tags.get(0))),
					new Car("car-brand2", "car-type2", 200D, "car-image2", "no wheels", users.get(1), List.of(tags.get(1)))
			);
			loggerS.info("2 cars were created");

			List<Motorcycle> motorcycles = List.of(
					new Motorcycle("moto-brand1", "moto-type1", 100D, "moto-image1", "no fuel", users.get(0), List.of(tags.get(0))),
					new Motorcycle("moto-brand2", "moto-type2", 200D, "moto-image2", "no mirrors", users.get(1), List.of(tags.get(1)))
			);
			loggerS.info("2 motorcycles were created");

			userRepository.saveAll(users);
			tagRepository.saveAll(tags);
			carRepository.saveAll(cars);
			motorcycleRepository.saveAll(motorcycles);
		};
	}
}
