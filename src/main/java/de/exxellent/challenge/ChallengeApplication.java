package de.exxellent.challenge;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;
import de.exxellent.challenge.serviceLayer.serviceImpl.ReaderImpl;
import de.exxellent.challenge.serviceLayer.serviceImpl.UtilsImpl;
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
		UtilsImpl utilsImpl = new UtilsImpl();
		File csvFile = new File("/Users/aliozdemir/Documents/challenge/src/main/resources/weather.csv");
		ArrayList<Weather> weatherArrayList = reader.csvReader(csvFile);
		reader.injectData(weatherArrayList, weatherRepository);

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		int dayWithSmallestTempSpread = utilsImpl.getDayWithSmallestTemperatureSpread(weatherRepository);

		System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

		String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
		System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
	}

}
