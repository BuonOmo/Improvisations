package com.entities;

import com.controllers.ImprovisationController;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Improvisation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theme;
    @ManyToOne//(cascade = CascadeType.PERSIST)
    private Category category;
    private String playerNumber;
    private String type;
    private int duration; // in seconds

    public Improvisation(String theme, Category category, String playerNumber, String type, int duration) {
        this.theme = theme;
        this.category = category;
        this.playerNumber = playerNumber;
        this.type = type;
        this.duration = duration;
    }

    public Improvisation() {
    }

    public Long getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public String getNiceDuration() {
        return ImprovisationController.secondsToMmss(duration);
    }

    public Category getCategory() {
        return category;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setNiceDuration(String duration) {
        setDuration(ImprovisationController.mmssToSeconds(duration));
    }

    @Override
    public String toString() {
        return "Improvisation{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", category=" + category +
                ", playerNumber='" + playerNumber + '\'' +
                ", type='" + type + '\'' +
                ", duration=" + duration +
                '}';
    }
}
