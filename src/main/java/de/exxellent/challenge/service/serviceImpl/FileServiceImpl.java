package de.exxellent.challenge.service.serviceImpl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import de.exxellent.challenge.modul.Football;
import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.repository.FootballRepository;
import de.exxellent.challenge.repository.WeatherRepository;
import de.exxellent.challenge.service.serviceInterface.FileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileServiceInterface {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private FootballRepository footballRepository;

    public FileServiceImpl() {
    }

    @Override
    public boolean hasCSVFormat(MultipartFile file) {
        if(file.getContentType().equals("text/csv")) {
            return true;
        }
        return false;
    }




    @Override
    public ArrayList readCSVToWeatherObject(MultipartFile csvFile){
        ArrayList<Weather> weatherArrayList= new ArrayList<>();
        try {
            Reader reader = new InputStreamReader(csvFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> allElements = csvReader.readAll();
            for (String[] values : allElements) {
                Double day = Double.parseDouble(values[0]);
                Double mxt = Double.parseDouble(values[1]);
                Double mnt = Double.parseDouble(values[2]);
                weatherArrayList.add(new Weather(day.intValue(), mxt, mnt));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } finally {
            return weatherArrayList;
        }
    }

    @Override
    public ArrayList readCSVToFootballObject(MultipartFile csvFile) {
        ArrayList<Football> footballArrayList= new ArrayList<>();
        try {
            Reader reader = new InputStreamReader(csvFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> allElements = csvReader.readAll();
            for (String[] values : allElements) {
                String team = values[0];
                Integer goalsMade = Integer.parseInt(values[5]);
                Integer goalsAllowed = Integer.parseInt(values[6]);
                footballArrayList.add(new Football(team, goalsMade, goalsAllowed));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } finally {
            return footballArrayList;
        }
    }

    @Override
    public void injectWeatherData(ArrayList<Weather> weatherArrayList) {
        weatherRepository.saveAll(weatherArrayList);
    }

    @Override
    public void injectFootballData(ArrayList<Football> footballArrayList) {
        footballRepository.saveAll(footballArrayList);
    }
}
