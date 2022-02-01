package de.exxellent.challenge.serviceLayer.serviceImpl;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import de.exxellent.challenge.dataAccessLayer.repository.WeatherRepository;
import de.exxellent.challenge.serviceLayer.serviceInterface.UtilsInterface;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UtilsImpl implements UtilsInterface {



    @Override
    public int getDayWithSmallestTemperatureSpread( WeatherRepository weatherRepository) {
        BidiMap tmpSpreadMap = new DualHashBidiMap();
        Iterable<Weather> weathers = weatherRepository.findAll();
        List<Double> tmpSpreadCollection = new ArrayList<>();
        for(Weather weather: weathers){
            Double spread = weather.getMxt()-weather.getMnt();
            tmpSpreadCollection.add(spread);
            tmpSpreadMap.put(weather.getDay(), spread);
        }

        Double minSpread = Collections.min(tmpSpreadCollection);
        Integer dayWithSmallestSpread = (Integer) tmpSpreadMap.getKey(minSpread);

        return dayWithSmallestSpread;
    }
}
