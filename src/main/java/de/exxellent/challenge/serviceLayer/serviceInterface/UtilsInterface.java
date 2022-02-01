package de.exxellent.challenge.serviceLayer.serviceInterface;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;

import java.util.ArrayList;

public interface UtilsInterface {
    int getDayWithSmallestTemperatureSpread( WeatherRepository weatherRepository);
}
