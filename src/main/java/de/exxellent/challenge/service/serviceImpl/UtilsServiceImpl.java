package de.exxellent.challenge.service.serviceImpl;

import de.exxellent.challenge.modul.Weather;
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

    @Override
    public int dayWithSmallestTemperatureSpread() {
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
