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
        //0. Translate all unclaer words.
        //0.1 Understand problem https://en.wikipedia.org/wiki/Gear_train
        //1. Quick and dirty for 2 gears
        //2. Solution for 3 gears
        //3. Try finding patterns
        //4. Solution for 4 and maybe 5 gears
        //5. Try finding patterns
        //6. Recursion ?? Looks like a good case for it

        int [] negativeResult = {-1, -1};
        int [] result;
        boolean isPegsSizeEven = pegs.length%2 == 0;

        if(pegs.length < 2){
            return negativeResult;
        }

        int number = 2*(calculate(pegs));

        if(!isPegsSizeEven) {
            number *= -1;
        }


        // peg should not be less the one , first one should be twice of the last one so no less than 2.
        if (number <= 2){
            return negativeResult;
        }

        boolean checkWhetherAllRadiusesAreBiggerThanOne = check(pegs ,number, isPegsSizeEven);

        if(!checkWhetherAllRadiusesAreBiggerThanOne){
            return negativeResult;
        }

        if(isPegsSizeEven){
            //return new int[]{number/3, 1};
            if(number%3 == 0){
                return new int[]{number/3, 1};
            }
            return new int[]{number, 3};
        } else {
            return new int[]{number, 1};
        }
        //return negativeResult;

    }

    private static boolean check(int[] pegs, int number, boolean isPegsSizeEven) {
        int radius = number;
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

    private static int calculate(int[] pegs) {
        int result = 0;
        boolean signSubstract = true;
        for(int a=pegs.length-1; a>=1; a--){
            if(signSubstract){
                result += (pegs[a] - pegs[a-1]);
                signSubstract =false;
            } else {
                result -= (pegs[a] - pegs[a-1]);
                signSubstract =true;
            }

        }
        return result;
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
