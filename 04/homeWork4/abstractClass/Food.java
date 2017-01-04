package com.company.levelUP.homeWork.homeWork4.abstractClass;


public abstract class Food extends Product {
    private float weight;
    private String label;


    public Food(int quantity, String productName, float price, float weight) {
        super(quantity, productName, price);
        this.weight=weight;
        this.label=productName;
    }

    @Override
    void getInfo() {
        System.out.println(label + " (" + weight + "gr) ");
        super.getInfo();
    }
}
