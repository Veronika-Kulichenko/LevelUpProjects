package com.company.levelUP.homeWork.homeWork4.light;


public abstract class Light implements SourceOfLight{

    private boolean isDay;
    private String sourceName;
    private String action;

    public Light(boolean isDay, String sourceName, String action) {
        this.isDay = isDay;
        this.sourceName = sourceName;
        this.action = action;
    }

    @Override
    public void getLight() {
        System.out.println(sourceName + " " + action);
    }

    @Override
    public void getReaction() {
        System.out.println(isDay ? "It's day now" : "It's night at this time");
    }
}
