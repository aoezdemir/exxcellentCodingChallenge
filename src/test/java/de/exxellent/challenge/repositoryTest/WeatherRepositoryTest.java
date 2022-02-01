package de.exxellent.challenge.repositoryTest;
import de.exxellent.challenge.modul.Football;
import de.exxellent.challenge.repository.FootballRepository;
import de.exxellent.challenge.service.serviceImpl.FileServiceImpl;
import org.assertj.core.api.Assertions;
import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.repository.WeatherRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WeatherRepositoryTest {
    @Autowired
    WeatherRepository weatherRepository;

    @InjectMocks
    private static MockMultipartFile multipartFileCSV;

    @InjectMocks
    private FileServiceImpl fileService;

    @BeforeAll
    public static void init() {
        Path path = Paths.get("src/main/resources/football.csv");
        String name = "weather.csv";
        String originalFileName = "weather.csv";
        String contentType = "text/csv";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        multipartFileCSV = new MockMultipartFile(name,
                originalFileName, contentType, content);
    }

    @Test
    @Sql({"/weatherTestData.sql"})
    void createTest() {
        Weather weather1 = new Weather(1, 88.0, 60.0);
        Optional<Weather> weather = weatherRepository.findById(1);

        Assertions.assertThat(weather.get().getDay()).isEqualTo(weather1.getDay());
        Assertions.assertThat(weather.get().getMxt()).isEqualTo(weather1.getMxt());
        Assertions.assertThat(weather.get().getMnt()).isEqualTo(weather1.getMnt());

    }

    @Test
    @Sql({"/weatherTestData2.sql"})
    void getAllTest() {
        Iterable<Weather> weathers = weatherRepository.findAll();
        int dataCount = ((ArrayList) weathers).size();
        Assertions.assertThat(dataCount).isEqualTo(5);
    }

    @Test
    void saveAllTest(){
        ArrayList<Weather> weatherArrayList = fileService.readCSVToWeatherObject(multipartFileCSV);
        weatherRepository.saveAll(weatherArrayList);

        // get saved Data
        Iterable<Weather> weathers = weatherRepository.findAll();
        ArrayList<Weather> weatherArrayListFromDatabase = (ArrayList<Weather>) weathers;

        //test
        int index = 0;
        while(index < weatherArrayListFromDatabase.size()) {
            Assertions.assertThat(weatherArrayList.get(index).getDay()).isEqualTo(weatherArrayListFromDatabase.get(index).getDay());
            Assertions.assertThat(weatherArrayList.get(index).getMxt()).isEqualTo(weatherArrayListFromDatabase.get(index).getMxt());
            Assertions.assertThat(weatherArrayList.get(index).getMnt()).isEqualTo(weatherArrayListFromDatabase.get(index).getMnt());
            index++;
        }

    }


}
