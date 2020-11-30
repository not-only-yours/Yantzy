import javax.swing.*;
import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

class LogicOfComputerturn {

    static class Node {
        Integer[] kubik = new Integer[5];
        float value;
        ArrayList<Node> nodes = new ArrayList<>();
    }

    ;


    static Node newNode(Integer[] kubik) {
        Node temp = new Node();
        temp.kubik = kubik;
        temp.value = setCorrectPriseOfNode(kubik);
        return temp;
    }

    static Integer setCorrectPriseOfNode(Integer[] kubik) {
        Integer[] aa = Turns.priseThatYouCanTake(Turns.computer, kubik);
        //System.out.println(Collections.max(Arrays.asList(aa)));
//        else
        return Collections.max(Arrays.asList(aa));
    }


    static float expectimax(Node node, boolean is_max) {

        if (node.nodes.isEmpty()) {
            return node.value;
        }


        if (is_max) {
            float max = 0;
            for (int i = 0; i < node.nodes.size(); i++) {
                if (expectimax(node.nodes.get(i), false) > max)
                    max = expectimax(node.nodes.get(i), false);
            }
            return max;
        } else {
            float sum = 0;
            for (int i = 0; i < node.nodes.size(); i++)
                sum += expectimax(node.nodes.get(i), false);
            return sum / node.nodes.size();
        }
    }

    // Driver code
    public static void calculateBest(int num) {
        Turns.turn = Kubik.throwKubik(5);
        Node root = newNode(Turns.turn);
        Turns.showArr(Turns.turn);
        if(num>8)
            //treeOfRolling(root);
            easyRolling(root);
        else
            easyRolling(root);
            makeTurn(root,expectimax(root, true));
    }


    public static void easyRolling(Node root){
        int numofRolls = 1;
        //System.out.println(root.nodes.size());
        addChoise(root, Turns.turn);
        //TODO create right way to make best score;
//        for (int i = 0; i < root.nodes.size(); i++) {
//            //System.out.println(i);
//            addChoise(root.nodes.get(i),root.nodes.get(i).kubik);
//        }
    }


    public static void makeTurn(Node root, float a){
        Integer[] aa =new Integer[13];
        for(int i=root.nodes.size()-1; i>=0;i--){
            if(root.nodes.get(i).value == a) {
                Integer three = 0;
                do {
                    Integer[] buf = root.nodes.get(i).kubik;
                    Kubik.change(whatShouldChange(root.kubik, buf));
                    Turns.showArr(root.kubik);
                    aa = Turns.priseThatYouCanTake(Turns.computer, root.kubik);
                    if (setCorrectPriseOfNode(root.kubik) == a) {
                        break;
                    }
                    three++;
                }while (three != 3);
                break;
            }
        }
        Turns.setCorrectComputerPrise(Turns.computer, aa);
    }


    public static Integer[] whatShouldChange(Integer[] a, Integer[] b){
        Integer[] first = a.clone();
        Integer[] second = b.clone();
        String ans = "";
        for (int j = 0; j < 5; j++)
            for (int k = 0; k < 5; k++)
                if (first[j].equals(second[k]) && first[j] != 0 && second[k] != 0) {
                    first[j] = 0;
                    second[k] = 0;
                }
        for(int i=0;i<5;i++){
            if(!first[i].equals(0)){
                ans+=(i+1)+" ";
            }
        }
        first = null;
        second = null;
        String[] answer= ans.split(" ");
        Integer[] integers = new Integer[answer.length];
        for (int i = 0; i < integers.length; i++){
            integers[i] = Integer.parseInt(answer[i]);
            //System.out.print(integers[i]+ " ");
        }
        return integers;
    }



    public static void treeOfRolling(Node root) {
        int numofRolls = 3;

        //Turns.showArr(Turns.turn);
            //root.nodes.add(newNode(buff));
            //System.out.println(i);
            //Turns.showArr(buff);
            addChoise(root, Turns.turn);
        for (int i = 0; i < root.nodes.size(); i++) {
            addChoise(root.nodes.get(i),root.nodes.get(i).kubik);
        }
        for (int i = 0; i < root.nodes.size(); i++) {
            for (int j = 0; j < root.nodes.size(); j++) {
                addChoise(root.nodes.get(i).nodes.get(j), root.nodes.get(i).nodes.get(j).kubik);
            }
        }
//        for (int i = 0; i < root.nodes.size(); i++) {
//            for (int j = 0; j < root.nodes.size(); j++) {
//                Turns.showArr(root.nodes.get(i).nodes.get(j).kubik);
//                System.out.println(root.nodes.get(i).nodes.get(j).value);
//            }
//        }
    }

    public static void addChoise(Node a, Integer[] b) {
        //System.out.println("aaaaa");
        //Turns.showArr(Turns.turn);
        for (int i = 0; i < 7; i++) {
            //System.out.println(i);
            for (int j = 1; j < 7; j++)
                for (int k = 1; k < 7; k++)
                    for (int l = 1; l < 7; l++)
                        for (int m = 1; m < 7; m++) {
//                            System.out.println(i+" "+ j+" "+ k+" "+ l+" "+ m);
//                            Turns.showArr(b);
//                            System.out.println(isAdded(Kubik.createKubik(i, j, k, l, m), b));
                            if (isAdded(Kubik.createKubik(i, j, k, l, m), b))
                                a.nodes.add(newNode(Kubik.createKubik(i, j, k, l, m)));
//                            System.out.println(isAdded(Kubik.createKubik(i, j, k, l, m), b));
                        }
        }
    }

    public static boolean isAdded(Integer[] a, Integer[] b) {
//        Turns.showArr(a);
//       Turns.showArr(b);
        Integer[] first = a.clone();
        Integer[] second = b.clone();
        int ans = 0;
        for (int j = 0; j < 5; j++)
            for (int k = 0; k < 5; k++)
                if (first[j].equals(second[k]) && first[j] != 0 && second[k] != 0) {
                    first[j] = 0;
                    second[k] = 0;
                    ans++;
                }
        first = null;
                second = null;
        return ans >= 2;
    }


    public static int getEmptyComputer() {
        int ans = 0;
        for (int i = 0; i < Turns.computer.length; i++) {
            try {
                Turns.computer[i].toString();
            } catch (NullPointerException e) {
                ans++;
            }
        }
        return ans;
    }
}