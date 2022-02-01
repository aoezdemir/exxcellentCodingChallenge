package de.exxellent.challenge.service.serviceImpl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import de.exxellent.challenge.modul.Weather;
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
    public ArrayList readCSVToWeatherObject(MultipartFile csvFile) throws IOException {
        ArrayList<Weather> weatherArrayList= new ArrayList<>();
        try {
            Reader reader = new InputStreamReader(csvFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> allElements = csvReader.readAll();
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
