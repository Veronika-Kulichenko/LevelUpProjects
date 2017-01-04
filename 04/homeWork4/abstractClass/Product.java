package com.company.levelUP.homeWork.homeWork4.abstractClass;

import java.text.CollationElementIterator;

public abstract class Product extends McDonaldsCashbox {
    private String productName;
    private float price;
    private int quantity;
    

    public Product(int quantity, String productName, float price){
        this.productName=productName;
        this.price=price;
        this.quantity=quantity;
    }

    @Override
    void getInfo(){
        System.out.print("price\t" + price + "$\r\n x " + quantity);
        calculateTotalPrice();}

    @Override
    void calculateTotalPrice() {
        System.out.println("\t = " + price * quantity + "$");
    }

}
