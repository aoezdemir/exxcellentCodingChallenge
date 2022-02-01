package de.exxellent.challenge.serviceTest;

import de.exxellent.challenge.repository.WeatherRepository;
import de.exxellent.challenge.service.serviceImpl.UtilsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilsImplTest {
    @Autowired
    WeatherRepository weatherRepository;

    @InjectMocks
    UtilsServiceImpl utilsImpl;

    @Test
    @Sql({"/weatherTestData2.sql"})
    void getDayWithSmallestTemperatureSpreadTest() {
//        ArrayList<Weather> weathers = (ArrayList<Weather>) weatherRepository.findAll();

        Integer dayWithSmallestSpread = utilsImpl.dayWithSmallestTemperatureSpread();

        Assertions.assertThat(dayWithSmallestSpread).isEqualTo(5);
    }
}
