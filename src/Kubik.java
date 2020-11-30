import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Kubik {

    public static Integer[] throwKubik(Integer size){
        Random rn = new Random();
        Integer[] ku = new Integer[size];
        for(int i=0;i<size;i++){
            ku[i] = rn.nextInt(6)+1;
        }
        return ku;
    }


    public static void change(Integer[] aa){
        Random rn = new Random();
        Turns.showArr(aa);
        for(int i=0;i <aa.length;i++){
            Turns.turn[aa[i] - 1] = rn.nextInt(6)+1;
        }
        Arrays.sort(Turns.turn);
    }

    public static Integer[] createKubik(int a,int b, int c, int d, int e){
        Integer[] i = new Integer[5];
        i[0] = a;
        i[1] = b;
        i[2] = c;
        i[3] = d;
        i[4] = e;
        return i;
    }
}
