package com.company.levelUP.homeWork.homeWork4.abstractClass;


import java.util.Scanner;

public class AbstractDemoMC {
    public static void main(String[] args) {
        Drink smallCola = new SmallCola(1);
        smallCola.getInfo();
        Drink mediumCola = new MediumCola(3);
        mediumCola.getInfo();
        Drink bigCola = new BigCola(2);
        bigCola.getInfo();

        Product smallChips = new SmallChips(4);
        smallChips.getInfo();
        Product mediumChips = new MediumChips(2);
        mediumChips.getInfo();
        Product bigChips = new BigChips();
        bigChips.getInfo();
    }
}
