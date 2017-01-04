package com.company.homeWork16;

import java.util.concurrent.Semaphore;

public class Service {
    //Каса занята - true, каса свободна - false
    private static final boolean[] CASH_PLACES = new boolean[2];

    //Устанавливаем флаг "true", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private static final Semaphore SEMAPHORE = new Semaphore(2, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Customer(i)).start();
            Thread.sleep(400);
        }
    }

    public static class Customer implements Runnable {
        private int customerNumber;

        public Customer(int customerNumber) {
            this.customerNumber = customerNumber;
        }

        @Override
        public void run() {
            System.out.printf("Клиент №%d стал в очередь.\n", customerNumber);
            try {
                SEMAPHORE.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int cashNumber = -1;

            //Ищем свободную кассу и подходим к ней

                for (int i = 0; i < 2; i++)
                    if (!CASH_PLACES[i]) {      //Если касса свободна
                        CASH_PLACES[i] = true;  //занимаем ее
                        cashNumber = i;         //Наличие свободного места, гарантирует семафор
                        System.out.printf("Клиент №%d обслуживается на кассе %d.\n", customerNumber, i+1);
                        break;
                    }


            try {
                Thread.sleep(5000);       //Клиент обслуживается
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


                CASH_PLACES[cashNumber] = false;//Освобождаем место на кассе


            SEMAPHORE.release();
            System.out.printf("Клиента №%d обслужили. Он отошел от кассы.\n", customerNumber);
        }
    }

}
