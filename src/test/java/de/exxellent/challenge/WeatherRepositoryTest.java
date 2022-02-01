package de.exxellent.challenge;
import de.exxellent.challenge.serviceLayer.serviceImpl.ReaderImpl;
import org.assertj.core.api.Assertions;
import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WeatherRepositoryTest {
    @Autowired
    WeatherRepository weatherRepository;

    @InjectMocks
    ReaderImpl reader;

    private File csvFile = new File("/Users/aliozdemir/Documents/challenge/src/main/resources/weather.csv");

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
    void saveAllTest() throws FileNotFoundException {
        ArrayList<Weather> weatherArrayList = reader.csvReader(csvFile);
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
