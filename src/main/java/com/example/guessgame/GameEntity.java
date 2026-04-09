package com.example.guessgame;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class GameEntity {

    @Id
    private String id;

    private int secretNumber;
    private int attempts;
    private boolean finished;

    // eampty constructor for JPA
    public GameEntity() {}

    public GameEntity(String id, int secretNumber) {
        this.id = id;
        this.secretNumber = secretNumber;
        this.attempts = 0;
        this.finished = false;
    }

    public String getId() { return id; }

    public int getSecretNumber() { return secretNumber; }

    public int getAttempts() { return attempts; }
    public void setAttempts(int attempts) { this.attempts = attempts; }

    public boolean isFinished() { return finished; }
    public void setFinished(boolean finished) { this.finished = finished; }
}