package com.company;

public class Main {

    public static void main(String[] args) {
        //testTask1();
        printTask1(solution(new int[]{4, 30, 50}));
        printTask1(solution(new int[]{4, 17, 50}));
        printTask1(solution(new int[]{1, 51}));
        printTask1(solution(new int[]{1, 31}));
        printTask1(solution(new int[]{1, 31, 51, 71}));
        printTask1(solution(new int[]{1, 31, 51, 71, 91}));
        printTask1(solution(new int[]{4, 9, 17, 31, 40}));
    }

    public static int[] solution(int[] pegs) {
        // Your code here

        int [] negativeResult = {-1, -1};
        boolean isPegsSizeEven = pegs.length%2 == 0;

        if(pegs.length < 2){
            return negativeResult;
        }

        // Iterativly
        //int radiusOfFirst = 2*(findRadiusOfFirstGear(pegs));
        //Recursively
        int radiusOfFirst = 2*(findRadiusOfFirstGearRecursively(pegs, pegs.length-1, true));

        //Change sign due to formula for odd length of array
        // -r0=2(pegpos2 - pegpos1 - pegpos1 + pegpos0)
        if(!isPegsSizeEven) {
            radiusOfFirst *= -1;
        }

        if(radiusOfFirst < 2) {
            return negativeResult;
        }

        //if(!areAllRadiusesAreBiggerThanOne(pegs ,radiusOfFirst, isPegsSizeEven)){
        if(!areAllRadiusesAreBiggerThanOneRecursively(pegs ,radiusOfFirst, 0, isPegsSizeEven)){
            return negativeResult;
        }

        if(isPegsSizeEven){
            if(radiusOfFirst%3 == 0){
                return new int[]{radiusOfFirst/3, 1};
            }
            return new int[]{radiusOfFirst, 3};
        }
        return new int[]{radiusOfFirst, 1};

    }

    private static int findRadiusOfFirstGear(int[] pegs) {
        int result = 0;
        boolean signSubstract = true;
        for(int a=pegs.length-1; a>=1; a--){
            int distance = (pegs[a] - pegs[a-1]);
            if(signSubstract){
                result += distance;
                signSubstract =false;
            } else {
                result -= distance;
                signSubstract =true;
            }

        }
        return result;
    }

    private static int findRadiusOfFirstGearRecursively(int[] pegs,
                                                        int index,
                                                        boolean changeSign) {
        if(index <= 0){
            return 0;
        }
        int distance = pegs[index] - pegs[index-1];
        if(changeSign) {
            return distance - findRadiusOfFirstGearRecursively(pegs, index-1, true);
        }
        return distance + findRadiusOfFirstGearRecursively(pegs, index-1, false);
    }

    private static boolean areAllRadiusesAreBiggerThanOneRecursively(int[] pegs,
                                                                 int radius,
                                                                 int index,
                                                                 boolean isPegsSizeEven) {
        if(index >= pegs.length-2){
            return true;
        }
        if(isPegsSizeEven) {radius = radius/3;}
        radius = pegs[index+1] - pegs[index] - radius;
        if(radius < 1) { return false; }
        return areAllRadiusesAreBiggerThanOneRecursively(pegs, radius, index+1, false);
    }


    private static boolean areAllRadiusesAreBiggerThanOne(int[] pegs,
                                                          int radiusOfFirst,
                                                          boolean isPegsSizeEven) {
        int radius = radiusOfFirst;
        if(isPegsSizeEven) {radius = radius/3;}
        for(int i = 0; i<=pegs.length-2; i++){
            int newRadius = pegs[i+1] - pegs[i] - radius;
            if(newRadius < 1){
                return false;
            }
            radius = newRadius;
        }
        return true;
    }

    private static void get(int[] a){
        System.out.println(a[0] + "," + a[1]);
    }

    private static void printTask1(int[] a){
        System.out.println(a[0] + "," + a[1]);
    }

    private static void testTask1() {
        int[] x = {14, 27, 1, 4, 2, 50, 3, 1};
        int[] y = {2, 4, -4, 3, 1, 1, 14, 27, 50};
        System.out.println(solution1(x, y));
        int[] a = {13, 5, 6, 2, 5};
        int[] b = {5, 2, 5, 13};
        System.out.println(solution1(a, b));
    }

    public static int solution1(int[] x, int[] y) {
        // Your code here
        //1. Simple solution
        //2. Possible improvement is sort and then a binary search
        int [] smaller = x;
        int [] bigger = y;

        if(x.length > y.length){
            smaller = y;
            bigger = x;
        }


        if(x.length > y.length){
            smaller =y;
            bigger = x;
        }
        boolean found;
        for(int i=0; i< bigger.length; i++){
            found = false;
            for(int a=0; a< smaller.length; a++){
                if(bigger[i]== smaller[a]){
                    found = true;
                    break;
                }
            }
            if(!found){
                return bigger[i];
            }
        }
        return 0;
    }
}
