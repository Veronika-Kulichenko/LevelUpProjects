package com.levelup.concurrent.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by Alexandr Shegeda on 11.07.16.
 */
public class RelaySemaphore {
    private static final String TEAM_1 = "Shadow rider";
    private static final String TEAM_2 = "The Mad Max";

    private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss SSS");

    public static void main(String[] args) throws InterruptedException {
        double distance = 3;
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);

        RiderWithSemaphore rider1 = new RiderWithSemaphore(10, distance, TEAM_1, semaphore1, 1);
        RiderWithSemaphore rider2 = new RiderWithSemaphore(16, distance, TEAM_2, semaphore2, 2);
        RiderWithSemaphore rider3 = new RiderWithSemaphore(20, distance, TEAM_1, semaphore1, 3);
        RiderWithSemaphore rider4 = new RiderWithSemaphore(12, distance, TEAM_2, semaphore2, 4);
        RiderWithSemaphore rider5 = new RiderWithSemaphore(13, distance, TEAM_1, semaphore1, 5);
        RiderWithSemaphore rider6 = new RiderWithSemaphore(15, distance, TEAM_2, semaphore2, 6);
        RiderWithSemaphore rider7 = new RiderWithSemaphore(20, distance,TEAM_1, semaphore1, 7);
        RiderWithSemaphore rider8 = new RiderWithSemaphore(11, distance, TEAM_2, semaphore2, 8);

        positionReadyStart();

        rider1.start();
        rider2.start();
        rider3.start();
        rider4.start();
        rider5.start();
        rider6.start();
        rider7.start();
        rider8.start();
    }


    private static void positionReadyStart() {
        try {
            System.out.println(formatter.format(new Date()) + " - Riders on your marks!");
            Thread.sleep(1500);
            System.out.println(formatter.format(new Date()) + " - Attention!");
            Thread.sleep(1500);
            System.out.println(formatter.format(new Date()) + " - START!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
