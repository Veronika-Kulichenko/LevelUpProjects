package com.company.levelUP.homeWork.homeWork4.light;


public class Flashlight extends Lamp {


    public Flashlight(boolean isDay, int quantity) {
        super(isDay, "Flashlight", isDay ? "is not lightning" : "is lightning", quantity);

    }
}
