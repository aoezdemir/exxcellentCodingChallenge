package de.exxellent.challenge.service.serviceInterface;

import de.exxellent.challenge.modul.Weather;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public interface FileServiceInterface {


    ArrayList<Weather> readCSVToWeatherObject(MultipartFile multipartFile) throws IOException;

    void injectData(ArrayList<Weather> weatherArrayList) throws FileNotFoundException;

    boolean hasCSVFormat(MultipartFile file);


}
