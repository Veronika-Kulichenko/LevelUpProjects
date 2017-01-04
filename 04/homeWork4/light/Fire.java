package com.company.levelUP.homeWork.homeWork4.light;

public class Fire extends NaturalLight {

    public Fire(boolean isDay) {
        super(isDay, "Fire", isDay ? "is not burning" : "is burning");
    }
}
