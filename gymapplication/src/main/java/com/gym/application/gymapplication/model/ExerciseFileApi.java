package com.gym.application.gymapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

public class ExerciseFileApi {


    private String Id = UUID.randomUUID().toString();

    private String category;
    private String bodySection;
    private String equipments;
    private String name;
    @JsonProperty("primaryMuscles")
    private String primaryMuscles;
    @JsonProperty("secondaryMuscles")
    private String secondaryMuscles;

    private String exerciseType;
    private Date createdOn = new Date();
    private String videoLink;
    private String pictureLink;

    // Constructor
    public ExerciseFileApi(String category, String bodySection, String equipment, String name, String primaryMuscle,
                    String secondaryMuscle, String exerciseType, Date createdOn, String videoLink,
                    String pictureLink) {
        this.category = category;
        this.bodySection = bodySection;
        this.equipments = equipment;
        this.name = name;
        this.primaryMuscles = primaryMuscle;
        this.secondaryMuscles = secondaryMuscle;
        this.exerciseType = exerciseType;
        this.createdOn = createdOn;
        this.videoLink = videoLink;
        this.pictureLink = pictureLink;
    }

    public ExerciseFileApi
            (){}

    // Getter and Setter methods

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBodySection() {
        return bodySection;
    }

    public void setBodySection(String bodySection) {
        this.bodySection = bodySection;
    }

    public String getEquipment() {
        return equipments;
    }

    public void setEquipment(String equipment) {
        this.equipments = equipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryMuscle() {
        return primaryMuscles;
    }

    public void setPrimaryMuscle(String primaryMuscle) {
        this.primaryMuscles = primaryMuscle;
    }

    public String getSecondaryMuscle() {
        return secondaryMuscles;
    }

    public void setSecondaryMuscle(String secondaryMuscle) {
        this.secondaryMuscles = secondaryMuscle;
    }





    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "Id='" + Id + '\'' +
                ", category='" + category + '\'' +
                ", bodySection='" + bodySection + '\'' +
                ", equipment='" + equipments + '\'' +
                ", name='" + name + '\'' +
                ", primaryMuscles='" + primaryMuscles + '\'' +
                ", secondaryMuscles='" + secondaryMuscles + '\'' +
                ", exerciseType='" + exerciseType + '\'' +
                ", createdOn=" + createdOn +
                ", videoLink='" + videoLink + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                '}';
    }

}
