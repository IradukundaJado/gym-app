package com.gym.application.gymapplication.services;

import java.util.List;

import com.gym.application.gymapplication.entities.Exercise;
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
}

