package de.exxellent.challenge.serviceTest;

import de.exxellent.challenge.modul.Football;
import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.repository.FootballRepository;
import de.exxellent.challenge.repository.WeatherRepository;
import de.exxellent.challenge.service.serviceImpl.UtilsServiceImpl;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UtilsImplTest {
    @InjectMocks
    UtilsServiceImpl utilsService;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    FootballRepository footballRepository;



    @Test
    @Sql({"/weatherTestData2.sql"})
    void getDayWithSmallestTemperatureSpreadTest() {
        Iterable<Weather> result = weatherRepository.findAll();
        BidiMap tmpSpreadMap = new DualHashBidiMap();
        double minSpread = utilsService.smallestTempSpreadCalculator((ArrayList<Weather>) result, tmpSpreadMap);
        int dayWithSmallestSpread = (int) tmpSpreadMap.getKey(minSpread);
        Assertions.assertThat(dayWithSmallestSpread).isEqualTo(5);
    }

    @Test
    @Sql({"/footballTestData2.sql"})
    void getTeamWithSmallestGoalSpreadTest() {
        Iterable<Football> result = footballRepository.findAll();
        BidiMap goalSpreadMap = new DualHashBidiMap();
        int minSpread  = utilsService.smallestGoalSpread((ArrayList<Football>)result, goalSpreadMap);
        String teamWithSmallestSpread = (String) goalSpreadMap.getKey(minSpread);

        Assertions.assertThat(teamWithSmallestSpread).isEqualTo("Chelsea");
    }
}
