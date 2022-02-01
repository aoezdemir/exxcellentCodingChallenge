package de.exxellent.challenge.controller;

import de.exxellent.challenge.message.ResponseMessage;
import de.exxellent.challenge.service.serviceImpl.UtilsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utils")
public class UtilsController {

    @Autowired
    UtilsServiceImpl utilsService;

    @GetMapping(path = "/dayWithSmallestTemperatureSpread")
    public ResponseEntity<ResponseMessage> dayWithSmallestTemperatureSpreadTest() {
        String message = "";
        HttpStatus status = null;
        try {
            int dayWithSmallestTempSpread = utilsService.dayWithSmallestTemperatureSpread();
            message = "Day with smallest temperature spread :" + dayWithSmallestTempSpread + " !";
            status = HttpStatus.OK;
        } catch (Exception e) {
            message = "Could not calculate day with smallest temperature spread!";
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        } finally {
            return ResponseEntity.status(status).body(new ResponseMessage(message));
        }

    }

    @GetMapping(path = "/teamWithSmallestGoalSpread")
    public ResponseEntity<ResponseMessage> teamWithSmallestGoalSpreadTest() {
        String message = "";
        HttpStatus status = null;
        try {
            String teamWithSmallestGoalSpread = utilsService.teamWithSmallestGoalSpread();
            message = "Team with smallest goal spread :" + teamWithSmallestGoalSpread + " !";
            status = HttpStatus.OK;
        } catch (Exception e) {
            message = "Could not calculate Team with smallest goal spread!";
            status = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        } finally {
            return ResponseEntity.status(status).body(new ResponseMessage(message));
        }

    }

}
