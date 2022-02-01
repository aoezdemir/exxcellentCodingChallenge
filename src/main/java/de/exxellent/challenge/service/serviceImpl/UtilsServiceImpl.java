package de.exxellent.challenge.service.serviceImpl;

import de.exxellent.challenge.modul.Football;
import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.repository.FootballRepository;
import de.exxellent.challenge.repository.WeatherRepository;
import de.exxellent.challenge.service.serviceInterface.UtilsServiceInterface;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UtilsServiceImpl implements UtilsServiceInterface {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    FootballRepository footballRepository;

    @Override
    public double smallestTempSpreadCalculator(ArrayList<Weather> weathers, BidiMap tmpSpreadMap ) {
        List<Double> tmpSpreadCollection = new ArrayList<>();
        for(Weather weather: weathers){
            Double spread = weather.getMxt()-weather.getMnt();
            tmpSpreadCollection.add(spread);
            tmpSpreadMap.put(weather.getDay(), spread);
        }
        double minSpread = Collections.min(tmpSpreadCollection);

        return minSpread;
    }
    @Override
    public int dayWithSmallestTemperatureSpread() {
        BidiMap tmpSpreadMap = new DualHashBidiMap();
        Iterable<Weather> weathers = weatherRepository.findAll();
        double minSpread = smallestTempSpreadCalculator((ArrayList<Weather>) weathers, tmpSpreadMap);
        int dayWithSmallestSpread = (int) tmpSpreadMap.getKey(minSpread);

        return dayWithSmallestSpread;
    }

    @Override
    public int smallestGoalSpread(ArrayList<Football> footballs,BidiMap tmpSpreadMap) {
        List<Integer> tmpSpreadCollection = new ArrayList<>();
        for(Football football: footballs){
            int spread = football.getGoals_made()-football.getGoals_allowed();
            tmpSpreadCollection.add(spread);
            tmpSpreadMap.put(football.getTeam(), spread);
        }
        int minSpread = Collections.min(tmpSpreadCollection);

        return minSpread;
    }

    @Override
    public String teamWithSmallestGoalSpread() {
        BidiMap goalSpreadMap = new DualHashBidiMap();
        Iterable<Football> footballs = footballRepository.findAll();
        int minSpread  = smallestGoalSpread((ArrayList<Football>)footballs, goalSpreadMap);
        String teamWithSmallestSpread = (String) goalSpreadMap.getKey(minSpread);

        return teamWithSmallestSpread;
    }
}
