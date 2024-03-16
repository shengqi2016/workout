package com.workout.model;

public class Record {

private double weight;
private double times;
private int durattion;
private double mood;
private double calorioesburned;


public Record() {
}


public Record(double weight, double times, int durattion, double mood, double calorioesburned) {
    this.weight = weight;
    this.times = times;
    this.durattion = durattion;
    this.mood = mood;
    this.calorioesburned = calorioesburned;
}

public double getWeight() {
    return weight;
}
public void setWeight(double weight) {
    this.weight = weight;
}
public double getTimes() {
    return times;
}
public void setTimes(double times) {
    this.times = times;
}
public int getDurattion() {
    return durattion;
}
public void setDurattion(int durattion) {
    this.durattion = durattion;
}
public double getMood() {
    return mood;
}
public void setMood(double mood) {
    this.mood = mood;
}
public double getCalorioesburned() {
    return calorioesburned;
}
public void setCalorioesburned(double calorioesburned) {
    this.calorioesburned = calorioesburned;
}

}