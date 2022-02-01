package de.exxellent.challenge.serviceTest;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.service.serviceImpl.FileServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;import org.springframework.mock.web.MockMultipartFile;


@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    private static MockMultipartFile multipartFileCSV;
    private static MockMultipartFile multipartFileCSVFootball;
    private static MockMultipartFile multipartFileNoCSV;

    @InjectMocks
    private FileServiceImpl fileService;


    @BeforeAll
    public static void init() {
        Path path = Paths.get("src/main/resources/weather.csv");
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

        name = "noCSV.txt";
        originalFileName = "noCSV.txt";
        contentType = "text/plain";
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        multipartFileNoCSV = new MockMultipartFile(name, originalFileName, contentType, content);

        // for Football
        Path pathFootball = Paths.get("src/main/resources/football.csv");
        String nameFootball = "football.csv";
        String originalFileNameFootball = "football.csv";
        String contentTypeFootball = "text/csv";
        byte[] contentFootball = null;
        try {
            contentFootball = Files.readAllBytes(pathFootball);
        } catch (final IOException e) {
        }
        multipartFileCSVFootball = new MockMultipartFile(nameFootball,
                originalFileNameFootball, contentTypeFootball, contentFootball);
    }

    @Test
    void hasCSVFormatTest() {
        boolean result = fileService.hasCSVFormat(multipartFileCSV);
        Assertions.assertThat(result).isTrue();

        result = fileService.hasCSVFormat(multipartFileNoCSV);
        Assertions.assertThat(result).isFalse();
    }


    @Test
    void readCSVToWeatherObjectTest() throws IOException, CsvException {
        File file = new File("src/main/resources/weather.csv");
        ArrayList<Weather> result =  fileService.readCSVToWeatherObject(multipartFileCSV);

        // get length of CSV File
        CSVReader csvReader = new CSVReader(new FileReader(file));
        List<String[]> allElements = csvReader.readAll();
        allElements.remove(0);
        int lengthOfData = allElements.size();

        //test compare length of CSV File to length of resulting ArrayList<Weather>
        Assertions.assertThat(result.size()).isEqualTo(lengthOfData);
    }

    @Test
    void readCSVToFootballObjectTest() throws IOException, CsvException {
        File file = new File("src/main/resources/football.csv");
        ArrayList<Weather> result =  fileService.readCSVToFootballObject(multipartFileCSVFootball);

        // get length of CSV File
        CSVReader csvReader = new CSVReader(new FileReader(file));
        List<String[]> allElements = csvReader.readAll();
        allElements.remove(0);
        int lengthOfData = allElements.size();

        //test compare length of CSV File to length of resulting ArrayList<Weather>
        Assertions.assertThat(result.size()).isEqualTo(lengthOfData);
    }

}
