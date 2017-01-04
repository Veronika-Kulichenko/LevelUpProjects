package com.company.levelUP.homeWork.homeWork4.light;


public class TableLamp extends Lamp {


    public TableLamp(boolean isDay, int quantity) {
        super(isDay, "Table lamp", isDay ? "is not lightning" : "is lightning", quantity);
    }
}
