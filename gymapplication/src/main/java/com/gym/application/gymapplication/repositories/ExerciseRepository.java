package com.gym.application.gymapplication.repositories;
import com.gym.application.gymapplication.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {


    List<Exercise> findByNameContainingIgnoreCase(String value);

    List<Exercise> findByCategoryContainingIgnoreCase(String value);

    List<Exercise> findByBodySectionContainingIgnoreCase(String value);

    List<Exercise> findByPrimaryMuscleContainingIgnoreCase(String value);

    List<Exercise> findBySecondaryMuscleContainingIgnoreCase(String value);



}

