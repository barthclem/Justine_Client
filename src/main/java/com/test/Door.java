package com.test;

/**
 * Created by aanu.oyeyemi on 1/6/17.
 * Project name -> GamePlayer
 */
public class Door {

    private long door;

    public Door(long door) {
        this.door = door;
    }

    public long getDoor() {
        return door;
    }

    public void setDoor(long door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "Door{" +
                "door=" + door +
                '}';
    }
}
