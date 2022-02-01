package de.exxellent.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
//	public static void main(String[] args) throws FileNotFoundException {
//		ConfigurableApplicationContext configurableApplicationContext =
//				SpringApplication.run(ChallengeApplication.class, args);
//		WeatherRepository weatherRepository =
//				configurableApplicationContext.getBean(WeatherRepository.class);
//		ReaderImpl reader = new ReaderImpl();
//		UtilsImpl utilsImpl = new UtilsImpl();
//		File csvFile = new File("/Users/aliozdemir/Documents/challenge/src/main/resources/weather.csv");
//		ArrayList<Weather> weatherArrayList = reader.csvReader(csvFile);
//		reader.injectData(weatherArrayList, weatherRepository);
//
//		System.out.println("--------------------------------------------------------------------------");
//		System.out.println("--------------------------------------------------------------------------");
//		int dayWithSmallestTempSpread = utilsImpl.getDayWithSmallestTemperatureSpread(weatherRepository);
//
//		System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
//
//		String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
//		System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
//	}

}
