package com.gym.application.gymapplication.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;


@Entity()
public class Exercise {

    @Id
    private String Id;

    private String category;
    private String bodySection;
    private String equipment;
    private String name;
    private String primaryMuscle;
    private String secondaryMuscle;
    private int exerciseId;
    private String exerciseType;
    private Date createdOn = new Date();
    @Column(length = 500)
    private String videoLink;
    @Column(length = 500)
    private String pictureLink;

    // Constructor
    public Exercise(String category, String bodySection, String equipment, String name, String primaryMuscle,
                    String secondaryMuscle, int exerciseId, String exerciseType, Date createdOn, String videoLink,
                    String pictureLink) {
        this.category = category;
        this.bodySection = bodySection;
        this.equipment = equipment;
        this.name = name;
        this.primaryMuscle = primaryMuscle;
        this.secondaryMuscle = secondaryMuscle;
        this.exerciseId = exerciseId;
        this.exerciseType = exerciseType;
        this.createdOn = createdOn;
        this.videoLink = videoLink;
        this.pictureLink = pictureLink;
    }

    public Exercise(){}

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
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryMuscle() {
        return primaryMuscle;
    }

    public void setPrimaryMuscle(String primaryMuscle) {
        this.primaryMuscle = primaryMuscle;
    }

    public String getSecondaryMuscle() {
        return secondaryMuscle;
    }

    public void setSecondaryMuscle(String secondaryMuscle) {
        this.secondaryMuscle = secondaryMuscle;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
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
                ", equipment='" + equipment + '\'' +
                ", name='" + name + '\'' +
                ", primaryMuscle='" + primaryMuscle + '\'' +
                ", secondaryMuscle='" + secondaryMuscle + '\'' +
                ", exerciseId=" + exerciseId +
                ", exerciseType='" + exerciseType + '\'' +
                ", createdOn=" + createdOn +
                ", videoLink='" + videoLink + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                '}';
    }
}

