package com.company.levelUP.homeWork.homeWork4.light;


public class Lamp extends ArtificialLight {
    int quantity;

    public Lamp(boolean isDay, String sourceName, String action, int quantity) {
        super(isDay, sourceName, action);
        this.quantity = quantity;
    }

    void getQuality(){
      System.out.println("Number of light sources: " + quantity);
  }
}
