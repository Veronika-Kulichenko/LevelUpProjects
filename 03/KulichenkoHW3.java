
    @Test
    public void shouldCheckThatHeadWillFitThroughTheWindow() throws Exception {
        int a = 5;
        int b = 7;
        int d = 3;
        boolean actualSize = task1(a, b, d);
        assertTrue(actualSize);
    }


    @Test
    public void shouldCheckThatHeadWillNotFitThroughTheWindow() throws Exception {
        int a = 5;
        int b = 7;
        int d = 5;
        boolean actualSize = task1(a, b, d);
        assertFalse(actualSize);
    }

    @Test
    public void shouldCheckThatOnlyOneIsEven() throws Exception {
        int a = 6;
        int b = 6;
        boolean actualValue = task2A(a, b);
        boolean expectedValue = false;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldCheckThatAtLeastOneOfTheNumbersIsPositive() throws Exception {
        int a = -6;
        int b = -6;
        boolean actualValue = task2B(a, b);
        boolean expectedValue = false;
    }

    @Test
    public void testTask3() throws Exception {
        int side = 2;
        int actualPerimeter = task3(side);
        int expectedPerimeter = side * 4;
        assertEquals(expectedPerimeter, actualPerimeter);
    }

    @Test
    public void testTask4A() throws Exception {
        int d = 3;
        double delta = 0.001;
        double actualCircumference = task4A(d);
        double expectedCircumference = 2 * Math.PI * d;
        assertEquals(expectedCircumference, actualCircumference, delta);
    }

    @Test
    public void testTask4B() throws Exception {
        int a = 3;
        double delta = 0.001;
        float actualCircumference = task4B(a);
        float expectedCircumference = 2 * (float) Math.PI * (a ^ 2);
        assertEquals(expectedCircumference, actualCircumference, delta);
    }

    @Test
    public void testTask5() throws Exception {
        int[] actualArr = task5(27);
        int[] expectedArr = {2, 7, 9, 14};
        assertArrayEquals(expectedArr, actualArr);
    }


    @Test
    public void testTask6() throws Exception {
        int dayNumber = 5;
        String actualDay = task6(dayNumber);
        String expectedDay = "Friday";
        assertEquals(expectedDay, actualDay);
    }

    @Test
    public void testTask7() throws Exception {
        String[] actualArr = task7();
        String expectedWeight = 10 + " lb = " + 10 * 0.453f + " kg";
        assertEquals(expectedWeight, actualArr[actualArr.length - 1]);
    }

    @Test
    public void testTask8() throws Exception {
//        Scanner scanner = new Scanner(System.in);
        float rate = 25.3f;
        String[] actualArr = task8(rate);
        String expectedArr = 20 + " UAH = " + 20 * rate + " $";
        assertEquals(expectedArr, actualArr[actualArr.length - 1]);
    }

    @Test
    public void testTask9() throws Exception {
        int[] actualArr = task9();
        int expectedAmoeba = 2 * 2;
        assertEquals(expectedAmoeba, actualArr[1]);
    }


    @Test
    public void testTask10() throws Exception {
        int monthNumber = 5;
        String actualSeason = task10(monthNumber);
        String expectedSeason = "Spring";
        assertEquals(expectedSeason, actualSeason);
    }

    @Test
    public void testTask12() throws Exception {
        Integer res = 123;

        Integer actualNumber = task12(res);
        Integer expectedNumber = 321;
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void testTask13() throws Exception {
        int[] arr = {13, 11, 15, 13, 14, 15, 16, 17, 34, 19, 56, 21, 22, 53, 24, 88, 8, 5, 15, 20};
        int actualMin = task13(arr);
        int expectedMin = 5;
        assertEquals(expectedMin, actualMin);
    }

    @Test
    public void testSelectionSort() throws Exception {
        int[] arr = {3, 2, 5, 7, 1, 4, 3};
        selectionSort(arr);
        int[] actualArr = arr;
        int[] expectedArr = {1, 2, 3, 3, 4, 5, 7};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void testBubbleSort() throws Exception {
        int[] arr = {3, 2, 5, 7, 1, 4, 3};
        bubbleSort(arr);
        int[] expectedArr = {1, 2, 3, 3, 4, 5, 7};
        assertArrayEquals(expectedArr, arr);
    }

    @Test
    public void shouldConvertNumberToWord() throws Exception {
        String actualWord = convertLessThanOneThousand(6);
        String expectedWord = " six";
        assertEquals(expectedWord, actualWord);
    }
