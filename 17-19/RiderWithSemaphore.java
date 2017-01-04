package com.levelup.concurrent.countdownlatch;

import com.levelup.concurrent.rider.Rider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by Alexandr Shegeda on 11.07.16.
 */
public class RiderWithSemaphore extends Rider {

    private String team;
    private Semaphore semaphore;
    private int riderNumber;
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss SSS");


    public RiderWithSemaphore(double speed, double distance, String team, Semaphore semaphore, int riderNumber) {
        super(speed, distance);
        this.semaphore = semaphore;
        this.riderNumber = riderNumber;
        this.team = "Team-" + team;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(formatter.format(new Date()) + getRiderName() + " " + " from " + team + " started round");


        super.run();

        System.out.println(getRiderName() + " " + " from " + team + " finished round");
        semaphore.release();


    }
}
