package de.exxellent.challenge;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;
import de.exxellent.challenge.serviceLayer.serviceImpl.ReaderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) throws FileNotFoundException {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(ChallengeApplication.class, args);
		WeatherRepository weatherRepository =
				configurableApplicationContext.getBean(WeatherRepository.class);
		ReaderImpl reader = new ReaderImpl();
		File csvFile = new File("/Users/aliozdemir/Documents/challenge/src/main/resources/weather.csv");
		ArrayList<Weather> weatherArrayList = reader.csvReader(csvFile);
		weatherRepository.saveAll(weatherArrayList);
	}

}
