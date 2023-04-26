package com.gym.application.gymapplication.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gym.application.gymapplication.entities.Exercise;
import com.gym.application.gymapplication.model.ExerciseApi;
import com.gym.application.gymapplication.services.ExerciseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    @PostMapping("")
    public  ResponseEntity<?> createAnExercise(@RequestBody ExerciseApi exercise) throws Exception {
        try {
            Exercise createdExercise =  this.exerciseService
                                        .createExercise(convertToEntity(exercise));
            return  ResponseEntity.ok(convertToApi(createdExercise));
        }catch (Exception e){
            throw new Exception("An error happened while trying to create new exercise");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> testing(){
        return ResponseEntity.ok("Welcome to our gym exercises application");
    }

    @GetMapping("")
    public  ResponseEntity<?> getAllExercises() throws Exception{
        try {
            List<ExerciseApi> exercises =  this.exerciseService.getAllExercises()
                    .stream().map(this::convertToApi).collect(Collectors.toList());

            return  ResponseEntity.ok(exercises);
        }catch (Exception e){
            throw  new Exception("An Error occured while trying to fetch all exercises");
        }
    }


    @GetMapping("/{exerciseId}")
    public  ResponseEntity<?> getExerciseById(@PathVariable("exerciseId") String exerciseId) throws  Exception{
        try {
            return  ResponseEntity.ok(this.exerciseService.getExerciseById(exerciseId));
        }catch (Exception e){
            throw  new Exception("Exception happened while trying to get exercise");
        }
    }

    @PatchMapping("/{exerciseId}")
    public ResponseEntity<?> updateExerciseById(@PathVariable("exerciseId") String exerciseId,
                                                @RequestBody ExerciseApi exerciseApi) throws Exception {
        try {
            Exercise updatedExercise =  this.exerciseService
                    .createExercise(convertToEntity(exerciseApi));
            return  ResponseEntity.ok(convertToApi(updatedExercise));

        }catch (Exception e){
            throw new Exception("Exception while trying to update exercise information");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                ExerciseApi exercise = new ExerciseApi();
                exercise.setCategory(csvRecord.get("Category"));
                exercise.setBodySection(csvRecord.get("BodySection"));
                exercise.setEquipments(csvRecord.get("Equipments").split(","));
                exercise.setName(csvRecord.get("Name"));
                exercise.setPrimaryMuscles(csvRecord.get("PrimaryMuscles").split(","));
                exercise.setSecondaryMuscles(csvRecord.get("SecondaryMuscles").split(","));
                exercise.setCreatedOn(new Date());
                exercise.setVideoLink(csvRecord.get("videoLink"));
                exercise.setPictureLink(csvRecord.get("pictureLink"));
                exerciseService.
                            createExercise(convertToEntity(exercise));
            }
            csvParser.close();
            return new ResponseEntity<>("CSV file uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Could not upload CSV file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Exercise convertToEntity(ExerciseApi exerciseApi){
        Exercise exercise = new Exercise();
        exercise.setCategory(exerciseApi.getCategory());
        exercise.setBodySection(exerciseApi.getBodySection());
        exercise.setEquipment(String.join(",",exerciseApi.getEquipments()));
        exercise.setName(exerciseApi.getName());
        exercise.setPrimaryMuscle(String.join(",",exerciseApi.getPrimaryMuscles()));
        exercise.setSecondaryMuscle(String.join(",",exerciseApi.getSecondaryMuscles()));
        exercise.setCreatedOn(new Date());
        exercise.setVideoLink(exercise.getVideoLink());
        exercise.setPictureLink(exercise.getPictureLink());
        return  exercise;
    }

    private ExerciseApi convertToApi(Exercise exercise){
        ExerciseApi exerciseApi = new ExerciseApi();
        exerciseApi.setCategory(exercise.getCategory());
        exerciseApi.setBodySection(exercise.getBodySection());
        exerciseApi.setEquipments(exercise.getEquipment().split(","));
        exerciseApi.setName(exercise.getName());
        exerciseApi.setPrimaryMuscles(exercise.getPrimaryMuscle().split(","));
        exerciseApi.setSecondaryMuscles(exercise.getSecondaryMuscle().split(","));
        exerciseApi.setCreatedOn(exercise.getCreatedOn());
        exerciseApi.setVideoLink(exercise.getVideoLink());
        exerciseApi.setPictureLink(exercise.getPictureLink());
        exerciseApi.setId(exercise.getId());
        return  exerciseApi;
    }
}

