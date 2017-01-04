    public void Task1() {
        String name;
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        String stars = "";
        for(int i=0; i<name.length();i++){stars+="*";
        }
        System.out.println(stars + name + stars);


    }

*********************************************************

    public void Task2() {
        Scanner scanner = new Scanner(System.in);
        String FutballClubName = scanner.nextLine();
       char[] n = FutballClubName.toCharArray();
        for(int i = 0; i < FutballClubName.length(); i++){
            System.out.println(FutballClubName.charAt(i));
        }

    }

**********************************************************

    public void Task3() {
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.println("Print your name");
            name = scanner.nextLine();
        }while (name.isEmpty());
        System.out.println("Hello, my dear friend, " + name );


    }

***********************************************************

    public void Task4() {
       int sum = 0;
        int[] arr = new int[50];
        for (int i =0; i<50; i++){
            if ((i%2)==0){
                sum+=i;

            }
        }  System.out.println(sum);
    }

************************************************************

    public void Task6() {
        double interest = 0;
        double account = 1000;
        double account1 = 1000;
        double account2 = 1000;
        for (int i =1; i<9; i++) {
            interest = interest + (account * 0.02);

        }
        System.out.println("Amount of accrued interest " + interest);

        for (int i = 1; i<=6; i++) {
            account = account + (account*0.02);

        }
        System.out.println("Deposit amount in six months " + account);

        for (int i = 1; i<=24; i++) {
            account1 = account1 + (account1*0.02);
        }
        System.out.println("Deposit amount in 2 years " + account1);

        for (int i = 1; i<=60; i++) {
            account2 = account2 + (account2*0.02);
        }
        System.out.println("Deposit amount in 5 years " + account2);
    }


*************************************************************

    public void Task 7() {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String secondName;
        do {
            System.out.println("Print your name");
            firstName = scanner.nextLine();
        } while (firstName.isEmpty());
        do {
            System.out.println("Print your Secondname");
            secondName = scanner.nextLine();
        } while (secondName.isEmpty());
        System.out.println("You are " + firstName + " " + secondName);
    }

*****************************************************************

    public void Task8() {
        byte suit = 3;
        switch (suit) {
            case 1:
                System.out.println("Пики");
                break;
            case 2:
                System.out.println("Трефы");
                break;
            case 3:
                System.out.println("Бубны");
                break;
            case 4:
                System.out.println("Червы");
                break;
            default:
                System.out.println("Нет такой масти");
                break;
        }
    }

*****************************************************************

    public void Task9() {
        int a = 5;
        int b = 7;
        int c = 6;
        int d = 8;
        if (a > b) {
            int lengthenvelope = a; int widthenvelope = b;
        }  int lengthenvelope = b; int widthenvelope = a;
        if (c>d) {
            int lengthcard = c; int widthcard = d;
        } int lengthcard = d; int withcard = c;
        if ((lengthcard <= lengthenvelope - 2) & (withcard <=widthenvelope - 2 )) {
            System.out.println("Card will fit in the envelope");
        } else System.out.println("Card won't fit in the envelope");
    }

*****************************************************************

    public void Task10() {
        int max;
        int a=3;
        int b = 4;
        if(a>b){
            System.out.println("The maximum number is " + a);
        }
        System.out.println("The maximum number is " + b);
    }