package com.gym.application.gymapplication.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gym.application.gymapplication.entities.Exercise;
import com.gym.application.gymapplication.model.ExerciseApi;
import com.gym.application.gymapplication.model.ExerciseFileApi;
import com.gym.application.gymapplication.model.ExerciseSearch;
import com.gym.application.gymapplication.services.ExerciseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
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

            return  ResponseEntity.ok(convertToApi(this.exerciseService.getExerciseById(exerciseId)));
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

    @PostMapping("/search")
    public ResponseEntity<?> searchExercises(@RequestBody ExerciseSearch exerciseSearch) throws Exception {
        try {
            if(exerciseSearch == null){
                throw  new Exception("Please provide search related data");
            }

            if(exerciseSearch.getFieldName() == null ||  exerciseSearch.getFieldName().toString()
                    .isEmpty()){
                throw  new Exception("Pleas provide field you trying to search on");
            }

            if(exerciseSearch.getValue() == null  ||  exerciseSearch.getValue().toString().isEmpty()){
                throw new Exception("Please provide search value");
            }
            List<ExerciseApi> exercises =  this.exerciseService.searchExercise(exerciseSearch)
                    .stream().map(this::convertToApi).collect(Collectors.toList());

            return  ResponseEntity.ok(exercises);
        }catch (Exception e){
            throw new Exception("Exception happened while trying to search for exercises");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestBody MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Replace with your sheet index
            List<Exercise> exercises = new ArrayList<>();
            for (Row row : sheet) {
                if(row.getCell(0) != null) {
                    Exercise exercise = new Exercise();
                    Cell category = row.getCell(0);
                    Cell bodySection = row.getCell(1);
                    Cell equipment = row.getCell(2);
                    Cell name = row.getCell(3);
                    Cell primaryMuscles = row.getCell(4);
                    Cell secondaryMuscles = row.getCell(5);
                    Cell videoLink = row.getCell(6);
                    Cell pictureLink = row.getCell(7);


                    exercise.setCategory(category != null ? category.toString() : "");
                    exercise.setBodySection(bodySection != null ? bodySection.toString() : "");
                    exercise.setName(name != null ? name.toString() : "");
                    exercise.setEquipment(equipment != null ? equipment.toString() : "");
                    exercise.setPrimaryMuscle(primaryMuscles != null ?
                            primaryMuscles.toString() : "");
                    exercise.setSecondaryMuscle(secondaryMuscles != null ?
                            secondaryMuscles.toString() : "");
                    if (videoLink != null && videoLink.toString().length() <= 500) {
                        exercise.setVideoLink(videoLink.toString());
                    }
                    if (pictureLink != null && pictureLink.toString().length() <= 500) {
                        exercise.setPictureLink(pictureLink.toString());
                    }
                    exercises.add(exercise);
                }
            }
            workbook.close();
            inputStream.close();
            System.out.print("total exercised uploaded is "+exercises.size());
            this.exerciseService.bulkSave(exercises);
            return new ResponseEntity<>("Excel uploaded successfully with " + exercises.size() + " exercises", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading Excel: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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

