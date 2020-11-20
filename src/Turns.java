import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Turns {
    static Integer[] computer = new Integer[13];
    static Integer[] player = new Integer[13];
    static Integer[] turn = new Integer[5];

    public static void playerturn(){
        turn = Kubik.throwKubik(5);
        Arrays.sort(turn);
        System.out.print("you get numbers:");
        printTurns();
        int chanseToturn = 1;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Which places do you want to rerol?");
            String ans = in.nextLine();
            if(ans.equals("skip")){
                break;
            }else {
                String[] answer= ans.split(" ");
                Integer[] integers = new Integer[answer.length];
                for (int i = 0; i < integers.length; i++){
                    integers[i] = Integer.parseInt(answer[i]);
                    //System.out.print(integers[i]+ " ");
                }
                Kubik.change(integers);
                System.out.print("you get numbers:");
                printTurns();
                chanseToturn++;
            }
            System.out.println();
        }while(chanseToturn<=3);

        Integer[] aa = priseThatYouCanTake(player, turn);
        showArr(aa);
        setCorrectPrise(player, aa);

    }

    public static void printTurns(){
        for (Integer integer : turn) {
            System.out.print(integer + ", ");
        }
        System.out.println();
    }


    public static void showBothCards(){
        System.out.print("Player card: ");
        for (Integer integer : player) {
            if(integer != null)
            System.out.print(integer + ", ");
            else
                System.out.print("  , ");
        }
        System.out.println();
        System.out.print("Computer card: ");
        for (Integer integer : computer) {
            if(integer != null)
                System.out.print(integer + ", ");
            else
                System.out.print("  , ");
        }
        System.out.println();
    }



    public static Integer sum(Integer[] a){
        Integer sum =0;
        for (Integer integer : a) {
            sum += integer;
        }
        //System.out.println(sum);
        return sum;
    }

    public static void showArr(Integer[] a){
        for (Integer integer : a) {
            System.out.print(integer + ", ");
        }
        System.out.println();
    }

    public static void Bothturns(){
        Integer a = 0;
        do{
            playerturn();

            a++;
        }while (a<13);
    }


    public static Integer[] priseThatYouCanTake(Integer[] a, Integer[] turn){
        Integer[] ans = new Integer[13];
        for(int i=0; i<a.length;i++) {
            Integer sum = 0;
            //System.out.println("i   " + i);
            if (i < 6) {
                for (Integer integer : turn) {
                    if (i + 1 == integer)
                        sum += integer;
                }
                //System.out.println("sum" + sum);
                ans[i] = sum;
            }
            //showArr(ans);
            if (i == 6) {
                if ((turn[0].equals(turn[1]) && turn[0].equals(turn[2])) || (turn[1].equals(turn[2]) && turn[1].equals(turn[3])) || (turn[2].equals(turn[3]) && turn[2].equals(turn[4]))) {
                    ans[i] = sum(turn);
                } else {
                    ans[i] = 0;
                }
            }
            if(i == 7){
                if ((turn[0].equals(turn[1]) && turn[0].equals(turn[2]) && turn[0].equals(turn[3])) || (turn[1].equals(turn[2]) && turn[1].equals(turn[3]) && turn[1].equals(turn[4]))) {
                    ans[i] = sum(turn);
                } else {
                    ans[i] = 0;
                }
            }
            if (i == 8) {
                if ((turn[0].equals(turn[1]) && turn[0].equals(turn[2]) && turn[3].equals(turn[4])) || (turn[0].equals(turn[1]) && turn[2].equals(turn[3]) && turn[3].equals(turn[4]))) {
                    ans[i] = 25;
                } else {
                    ans[i] = 0;
                }
            }
            if (i == 9) {
                if ((turn[0].equals(turn[1] - 1) && turn[0].equals(turn[2] - 2) && turn[0].equals(turn[3] - 3)) || (turn[1].equals(turn[2] - 1) && turn[1].equals(turn[3] - 2) && turn[1].equals(turn[4] - 3))) {
                    ans[i] = 30;
                } else {
                    ans[i] = 0;
                }
            }
            if (i == 10) {
                if (turn[0].equals(turn[1]-1) && turn[0].equals(turn[2] - 2) && turn[0].equals(turn[3] - 3) && turn[0].equals(turn[4] - 4)) {
                    ans[i] = 40;
                } else {
                    ans[i] = 0;
                }
            }
            if (i == 11)
                if (turn[0].equals(turn[1]) && turn[0].equals(turn[2]) && turn[0].equals(turn[3]) && turn[0].equals(turn[4])) {
                    ans[i] = 50;
                } else {
                    ans[i] = 0;
                }
            if (i == 12) {
                ans[i] = sum(turn);
            }
        }
        for(int i=0;i< ans.length; i++){
            if(a[i] != null){
                ans[i] = 0;
            }
        }
        //showArr(ans);
        return ans;
    }


    public static void setCorrectPrise(Integer[] a, Integer[] num){
        Scanner in = new Scanner(System.in);
        System.out.println("Ok, now turn your place in check:");
        Integer q = in.nextInt();
        a[q-1] = num[q-1];

        showBothCards();
    }


}

