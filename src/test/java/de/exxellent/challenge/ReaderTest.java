package de.exxellent.challenge;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;
import de.exxellent.challenge.serviceLayer.serviceImpl.ReaderImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ReaderTest {

    @InjectMocks
    private ReaderImpl reader;

    private File csvFile = new File("/Users/aliozdemir/Documents/challenge/src/main/resources/weather.csv");

    @Test
    void csvReaderTest() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader(csvFile));
        List<String[]> allElements = csvReader.readAll();
        allElements.remove(0);
        int lengthOfData = allElements.size();
        ArrayList<Weather> weatherArrayList = reader.csvReader(csvFile);

        //test
        Assertions.assertThat(weatherArrayList.size()).isEqualTo(lengthOfData);
    }



}
