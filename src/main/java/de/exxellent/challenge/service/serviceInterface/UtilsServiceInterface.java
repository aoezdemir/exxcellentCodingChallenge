package de.exxellent.challenge.service.serviceInterface;

import de.exxellent.challenge.modul.Football;
import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.repository.WeatherRepository;
import org.apache.commons.collections.BidiMap;

import java.util.ArrayList;

public interface UtilsServiceInterface {
    double smallestTempSpreadCalculator(ArrayList<Weather> weathers, BidiMap tmpSpreadMap );
    int dayWithSmallestTemperatureSpread();

    int smallestGoalSpread(ArrayList<Football> footballs,BidiMap tmpSpreadMap);
    String teamWithSmallestGoalSpread();
}
