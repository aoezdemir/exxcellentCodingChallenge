package de.exxellent.challenge;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;
import de.exxellent.challenge.serviceLayer.serviceImpl.UtilsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilsImplTest {
    @Autowired
    WeatherRepository weatherRepository;

    @InjectMocks
    UtilsImpl utilsImpl;

    @Test
    @Sql({"/weatherTestData2.sql"})
    void getDayWithSmallestTemperatureSpreadTest() {
//        ArrayList<Weather> weathers = (ArrayList<Weather>) weatherRepository.findAll();

        Integer dayWithSmallestSpread = utilsImpl.getDayWithSmallestTemperatureSpread(weatherRepository);

        Assertions.assertThat(dayWithSmallestSpread).isEqualTo(5);

    }
}
