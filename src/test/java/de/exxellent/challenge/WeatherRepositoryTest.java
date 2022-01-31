package de.exxellent.challenge;
import org.assertj.core.api.Assertions;
import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.repository.WeatherRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WeatherRepositoryTest {
    @Autowired
    WeatherRepository weatherRepository;

    @Test
    @Sql({"/weatherTestData.sql"})
    void testCreate() {
        Weather weather1 = new Weather(1, 88, 60);
        Optional<Weather> weather = weatherRepository.findById(1);

        Assertions.assertThat(weather.get().getDay()).isEqualTo(weather1.getDay());
        Assertions.assertThat(weather.get().getMxt()).isEqualTo(weather1.getMxt());
        Assertions.assertThat(weather.get().getMnt()).isEqualTo(weather1.getMnt());

    }

    @Test
    @Sql({"/weatherTestData2.sql"})
    void testGetAll() {
        Iterable<Weather> weathers = weatherRepository.findAll();
        int dataCount = ((ArrayList) weathers).size();
        Assertions.assertThat(dataCount).isEqualTo(5);
    }
}
