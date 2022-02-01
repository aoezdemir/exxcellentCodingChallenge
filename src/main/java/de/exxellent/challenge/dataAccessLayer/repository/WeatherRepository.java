package de.exxellent.challenge.dataAccessLayer.repository;

import de.exxellent.challenge.dataAccessLayer.modul.Weather;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, Integer> {
}
