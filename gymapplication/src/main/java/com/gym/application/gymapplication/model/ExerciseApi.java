package com.gym.application.gymapplication.model;

import java.util.Date;

public class ExerciseApi {

    private String Id;
    private String category;
    private String bodySection;
    private String[] equipments;
    private String name;
    private String[] primaryMuscles;
    private String[] secondaryMuscles;
    private Date createdOn;
    private String videoLink;
    private String pictureLink;

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

    public String[] getEquipments() {
        return equipments;
    }

    public void setEquipments(String[] equipments) {
        this.equipments = equipments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPrimaryMuscles() {
        return primaryMuscles;
    }

    public void setPrimaryMuscles(String[] primaryMuscles) {
        this.primaryMuscles = primaryMuscles;
    }

    public String[] getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles(String[] secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
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
}
