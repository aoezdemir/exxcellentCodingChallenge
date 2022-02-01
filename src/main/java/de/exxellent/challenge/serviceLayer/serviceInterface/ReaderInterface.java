package de.exxellent.challenge.serviceLayer.serviceInterface;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReaderInterface {

    ArrayList<Weather> csvReader(File csvFile) throws FileNotFoundException;

    void injectData(ArrayList<Weather> weatherArrayList, WeatherRepository weatherRepository) throws FileNotFoundException;
}
