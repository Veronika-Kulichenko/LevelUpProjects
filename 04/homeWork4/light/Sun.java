package com.company.levelUP.homeWork.homeWork4.light;


public class Sun extends NaturalLight {

    public Sun(boolean isDay) {
        super(isDay, "Sun", isDay ? "is shining" : "is not shining");
    }
}
