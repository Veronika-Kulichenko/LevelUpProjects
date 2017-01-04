package com.levelup.concurrent.rider;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexandr Shegeda on 08.07.16.
 */
public class Rider extends Thread {

    private double speed;       // km/h
    private double distance;    // km
    private String riderName;
    private static int riderId = 1;
    private long startRiding;

    private DecimalFormat df = new DecimalFormat("#.##");
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss SSS");

    public Rider(double speed, double distance ) {
        this.speed = speed;
        this.distance = distance;
        this.riderName = "Rider-" + riderId++;
        startRiding = System.currentTimeMillis();

    }


    public String getRiderName() {
        return riderName;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(formatter.format(new Date()) + " " + riderName + " started");
        double rideDistance = distance / speed;
        double rideTime = rideDistance * 10000;
        try {
            Thread.sleep((long) rideTime);
            Date finishDate = new Date();
            double totalRidingTime = (System.currentTimeMillis() - startRiding) / 266;
            System.out.println(formatter.format(finishDate) + " " + riderName +
                    " finished the distance in " + df.format(totalRidingTime) + " minutes");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
