package de.exxellent.challenge.repository;

import de.exxellent.challenge.modul.Weather;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, Integer> {
}
