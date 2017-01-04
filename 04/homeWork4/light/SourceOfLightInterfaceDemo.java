package com.company.levelUP.homeWork.homeWork4.light;


public class SourceOfLightInterfaceDemo {
    public static void main(String[] args) {
        Sun sun = new Sun(true);
        sun.getReaction();
        sun.getLight();

        Fire fire = new Fire(false);
        fire.getReaction();
        fire.getLight();

       Flashlight flash = new Flashlight(true, 3);
        flash.getReaction();
        flash.getLight();
        flash.getQuality();

      TableLamp lamp = new TableLamp(false,1);
        lamp.getReaction();
        lamp.getLight();
        lamp.getQuality();
    }
}
