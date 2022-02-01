package de.exxellent.challenge.service.serviceInterface;

import de.exxellent.challenge.modul.Football;
import de.exxellent.challenge.modul.Weather;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public interface FileServiceInterface {


    ArrayList<Weather> readCSVToWeatherObject(MultipartFile multipartFile);

    ArrayList<Weather> readCSVToFootballObject(MultipartFile multipartFile);

    void injectWeatherData(ArrayList<Weather> weatherArrayList);

    void injectFootballData(ArrayList<Football> footballArrayList);

    boolean hasCSVFormat(MultipartFile file);


}
