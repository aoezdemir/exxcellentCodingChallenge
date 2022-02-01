package de.exxellent.challenge.serviceLayer.serviceImpl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;
import de.exxellent.challenge.serviceLayer.serviceInterface.ReaderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements ReaderInterface {

    @Autowired
    private WeatherRepository weatherRepository;

    public ReaderImpl() {
    }

    @Override
    public ArrayList<Weather> csvReader(File csvFile) throws FileNotFoundException {
        ArrayList<Weather> weatherArrayList= new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile));) {
            List<String[]> allElements = csvReader.readAll();
            allElements.remove(0);
            for (String[] values : allElements) {
                ArrayList<Double> row = new ArrayList();
                for (int i = 0; i < 3; i++) {
                    double n = Double.parseDouble(values[i]);
                    row.add(n);
                }
                weatherArrayList.add(new Weather(row.get(0).intValue(), row.get(1), row.get(2)));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } finally {
            return weatherArrayList;
        }
    }

    @Override
    public void injectData(ArrayList<Weather> weatherArrayList) throws FileNotFoundException {
        weatherRepository.saveAll(weatherArrayList);
    }


}
