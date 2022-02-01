package de.exxellent.challenge.controller;

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
@RequestMapping(path = "/injectData")
public class FileController {
    @Autowired
    FileServiceImpl fileService;

    @GetMapping(path="/greeting")
    public String greet() {
        return "Hi ich bin Hafti.";
    }

    @PostMapping(path = "/injectWeatherData")
    public ResponseEntity<ResponseMessage> injectData(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        if(fileService.hasCSVFormat(file)) {
            try {
                ArrayList<Weather> result = fileService.readCSVToWeatherObject(file);
                fileService.injectData(result);
                message = "Uploaded the file successfully: " + file.getName();
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
