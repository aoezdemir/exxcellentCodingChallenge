package de.exxellent.challenge.serviceLayer.serviceInterface;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReaderInterface {

    ArrayList<Weather> csvReader(File csvFile) throws FileNotFoundException;

    void injectData(ArrayList<Weather> weatherArrayList) throws FileNotFoundException;
}
