package de.exxellent.challenge.repositoryTest;
import de.exxellent.challenge.modul.Football;

import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.repository.FootballRepository;
import de.exxellent.challenge.service.serviceImpl.FileServiceImpl;
import org.assertj.core.api.Assertions;
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

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FootballRepositoryTest {
    @Autowired
    FootballRepository footballRepository;

    @InjectMocks
    private static MockMultipartFile multipartFileCSVFootball;

    @InjectMocks
    private FileServiceImpl fileService;

    @BeforeAll
    public static void init() {
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
    @Sql({"/footballTestData.sql"})
    void createTest() {
        Football footballToCompare = new Football(1, "Liverpool", 67, 30);
        Optional<Football> football = footballRepository.findById(1);

        Assertions.assertThat(football.get().getTeam()).isEqualTo(footballToCompare.getTeam());
        Assertions.assertThat(football.get().getGoals_made()).isEqualTo(footballToCompare.getGoals_made());
        Assertions.assertThat(football.get().getGoals_allowed()).isEqualTo(footballToCompare.getGoals_allowed());
    }

    @Test
    @Sql({"/footballTestData2.sql"})
    void getAllTest() {
        Iterable<Football> footballs = footballRepository.findAll();
        int dataCount = ((ArrayList) footballs).size();
        Assertions.assertThat(dataCount).isEqualTo(5);
    }

    @Test
    void saveAllTest(){
        ArrayList<Football> footballArrayList = fileService.readCSVToFootballObject(multipartFileCSVFootball);
        footballRepository.saveAll(footballArrayList);

        // get saved Data
        Iterable<Football> footballs = footballRepository.findAll();
        ArrayList<Football> footballsArrayListFromDatabase = (ArrayList<Football>) footballs;

        //test
        int index = 0;
        while(index < footballsArrayListFromDatabase.size()) {
            Assertions.assertThat(footballArrayList.get(index).getTeam()).isEqualTo(footballsArrayListFromDatabase.get(index).getTeam());
            Assertions.assertThat(footballArrayList.get(index).getGoals_made()).isEqualTo(footballsArrayListFromDatabase.get(index).getGoals_made());
            Assertions.assertThat(footballArrayList.get(index).getGoals_allowed()).isEqualTo(footballsArrayListFromDatabase.get(index).getGoals_allowed());
            index++;
        }
       }
}
