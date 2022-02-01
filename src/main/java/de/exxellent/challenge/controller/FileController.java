package de.exxellent.challenge.controller;

import de.exxellent.challenge.modul.Football;
import de.exxellent.challenge.modul.Weather;
import de.exxellent.challenge.message.ResponseMessage;
import de.exxellent.challenge.service.serviceImpl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/injectCSVData")
public class FileController {
    @Autowired
    FileServiceImpl fileService;

    @PostMapping(path = "/weatherData")
    public ResponseEntity<ResponseMessage> injectWeatherData(@RequestParam("file") MultipartFile file) {
        String message = "";
        if(fileService.hasCSVFormat(file)) {
            try {
                ArrayList<Weather> result = fileService.readCSVToWeatherObject(file);
                fileService.injectWeatherData(result);
                message = "Uploaded the weather file successfully!";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getName() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping(path = "/footballData")
    public ResponseEntity<ResponseMessage> injectFootballData(@RequestParam("file") MultipartFile file) {
        String message = "";
        if(fileService.hasCSVFormat(file)) {
            try {
                ArrayList<Football> result = fileService.readCSVToFootballObject(file);
                fileService.injectFootballData(result);
                message = "Uploaded the football file successfully!";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getName() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
