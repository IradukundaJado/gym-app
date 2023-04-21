package com.gym.application.gymapplication.repositories;
import com.gym.application.gymapplication.entities.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, String> {

}

