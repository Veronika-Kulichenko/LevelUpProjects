

    public static boolean task1(int a, int b, int d) {
        if (d <= (a - 2) && d <= (b - 2)) {
            System.out.println("Head will fit");
            return true;
        } else System.out.println("Head will not fit");
        return false;
    }

    public static boolean task2A(int a, int b) {
        return ((a % 2 == 0) ^ (b % 2 == 0));
            System.out.println("Condition is true");
            return true;
        } else System.out.println("Condition is not true");
        return false;
    }

    public static boolean task2B(int a, int b) {
        if (a > 0 | b > 0) {
            System.out.println("Condition is true");
            return true;
        } else System.out.println("Condition is not true");
        return false;
    }

    public static int task3(int a) {
        System.out.println("The perimeter of the square is equal to " + 4 * a);
        return a * 4;
    }

    public static double task4A(int d) {
        System.out.println("The circumference is equal to " + 2 * Math.PI * d);
        return 2 * Math.PI * d;
    }

    public static float task4B(int a) {
        float result = 2 * (float) Math.PI * (a ^ 2);
        System.out.println("Area of a circle is equal to " + result);
        return result;
    }

    public static int[] task5(int a) {
        int[] arr = new int[4];
        System.out.println("Число десятков = " + (arr[0] = a / 10));
        System.out.println("Число единиц = " + (arr[1] = a % 10));
        System.out.println("Сумма цифр = " + (arr[2] = (a / 10 + a % 10)));
        System.out.println("Произведение цифр = " + (arr[3] = (a / 10) * (a % 10)));
        return arr;
    }

    public static String task6(int dayNumber) {
        switch (dayNumber) {
            case 1:
                System.out.println("Monday");
                return "Monday";
            case 2:
                System.out.println("Tuesday");
                return "Tuesday";
            case 3:
                System.out.println("Wednesday");
                return "Wednesday";
            case 4:
                System.out.println("Thursday");
                return "Thursday";
            case 5:
                System.out.println("Friday");
                return "Friday";
            case 6:
                System.out.println("Saturday");
                return "Saturday";
            case 7:
                System.out.println("Sunday");
                return "Sunday";
            default:
                System.out.println("It is not a weekday");
                return "It is not a weekday";
        }
    }

    public static String[] task7() {
        float conversion = 0.453f;
        String[] arr = new String[10];
        for (int i = 1; i <= 10; i++) {
            System.out.println(arr[i - 1] = (i + " lb = " + i * conversion + " kg"));
        }
        return arr;
    }

    public static String[] task8(float rate) {
        String[] arr = new String[20];
        for (int i = 1; i <= 20; i++) {
            System.out.println(arr[i - 1] = i + " UAH = " + i * rate + " $");
        }
        return arr;
    }

    public static int[] task9() {
        int[] arr = new int[8];
        int countAmoeba = 1;
        int index = 0;
        for (int i = 1; i <= 30 && index < arr.length; i++) {
            if (i % 3 == 0) {
                countAmoeba = countAmoeba * 2;
                System.out.println(arr[index] = countAmoeba);
                index += 1;
            }
        }
        return arr;
    }

    public static String task10(int monthNumber) {
        switch (monthNumber) {
            case 1:
            case 2:
            case 12:
                System.out.println("Winter");
                return "Winter";
            case 3:
            case 4:
            case 5:
                System.out.println("Spring");
                return "Spring";
            case 6:
            case 7:
            case 8:
                System.out.println("Summer");
                return "Summer";
            case 9:
            case 10:
            case 11:
                System.out.println("Autumn");
                return "Autumn";
            default:
                System.out.println("It is not a month");
                return "It is not a month";
        }
    }

    public static int task12(int n) {
        int res;
        String t = n + "";
        StringBuffer str = new StringBuffer(t);
        str.reverse();
        t = str.toString();
        res = Integer.valueOf(t);
        System.out.println(res);
        return res;
    }

    public static int task13(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < 20; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println(min);
        return min;
    }
