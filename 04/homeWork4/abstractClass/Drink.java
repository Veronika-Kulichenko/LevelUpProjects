package com.company.levelUP.homeWork.homeWork4.abstractClass;


public abstract class Drink extends Product {
    private float volume;
    private String label;

    public Drink(int quantity, String productName, float price, float volume) {
        super(quantity, productName, price);
        this.volume = volume;
        this.label = productName;
    }

    @Override
    void getInfo() {
        System.out.println(label + " (" + volume + "L) ");
        super.getInfo();
    }


}
