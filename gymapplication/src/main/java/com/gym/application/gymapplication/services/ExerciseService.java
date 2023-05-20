package com.gym.application.gymapplication.services;

import java.util.ArrayList;
import java.util.List;

import com.gym.application.gymapplication.entities.Exercise;
import com.gym.application.gymapplication.model.ExerciseSearch;
import com.gym.application.gymapplication.model.Type;
import com.gym.application.gymapplication.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(String id) {
        return exerciseRepository.findById(id).orElse(null);
    }


    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(String id) {
        exerciseRepository.deleteById(id);
    }

    public  List<Exercise> bulkSave(List<Exercise> exercises){
        return  this.exerciseRepository.saveAll(exercises);
    }

    public List<Exercise> searchExercise(ExerciseSearch exerciseSearch){
        if(exerciseSearch.getFieldName().equals(Type.name)){
            return  this.exerciseRepository.findByNameContainingIgnoreCase(exerciseSearch.getValue());
        }
        if(exerciseSearch.getFieldName().toString().equalsIgnoreCase(Type.category.toString())){
            return  this.exerciseRepository.findByCategoryContainingIgnoreCase(exerciseSearch.getValue());
        }

        if(exerciseSearch.getFieldName().toString().equalsIgnoreCase(Type.bodySection.toString())){
            return  this.exerciseRepository.findByBodySectionContainingIgnoreCase(exerciseSearch.getValue());
        }

        if(exerciseSearch.getFieldName().toString().equalsIgnoreCase(Type.primaryMuscles.toString())){
            return  this.exerciseRepository.findByPrimaryMuscleContainingIgnoreCase(exerciseSearch.getValue());
        }

        if(exerciseSearch.getFieldName().toString().equalsIgnoreCase(Type.secondaryMuscles.toString())){
            return  this.exerciseRepository.findBySecondaryMuscleContainingIgnoreCase(exerciseSearch.getValue());
        }

        if(exerciseSearch.getFieldName().toString().equalsIgnoreCase(Type.Equipment.toString())){
            return  this.exerciseRepository.findByEquipmentContainingIgnoreCase(exerciseSearch.getValue());
        }
        return  new ArrayList<>();
    }
}

