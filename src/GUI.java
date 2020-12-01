import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;

public class GUI extends JFrame {
    private JButton buttonNewGame = new JButton("New Game");
    private JLabel label1 = new JLabel("computer card:");
    private JLabel label2 = new JLabel("");
    private JLabel label3 = new JLabel("player card:");
    private JLabel label4 = new JLabel("");
    private JLabel label5 = new JLabel("dice:");
    private JLabel label6 = new JLabel("");
    private JLabel label7 = new JLabel("write place of changing");
    private JTextField input = new JTextField("", 5);
    private JButton button = new JButton("Make step");
    private JLabel label8 = new JLabel("select place in card");
    private JTextField input2 = new JTextField("", 5);
    private JButton button2 = new JButton("Set");
    private JLabel label9 = new JLabel("");
    private JLabel label10 = new JLabel("");
    private JButton button3 = new JButton("Roll");

    public GUI() {
        super("Game");
        this.setBounds(100, 100, 500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(16, 1, 0, 0));
        this.getContentPane().add(buttonNewGame);
        buttonNewGame.addActionListener(new NewGame());
        container.add(label1);
        container.add(label2);
        container.add(label3);
        container.add(label4);
        container.add(label5);
        container.add(label6);
        container.add(button3);
        container.add(label7);
        button3.addActionListener(new get());
        container.add(input);
        container.add(button);
        button.addActionListener(new NewButtonEventListener());
        container.add(label8);
        container.add(input2);
        container.add(button2);
        button2.addActionListener(new set());
        container.add(label9);
        container.add(label10);
    }

    class NewGame implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            newGame();
        }
    }

    public void newGame(){
        Turns.computer = new Integer[13];
        Turns.player = new Integer[13];
        showBothCardsInGui();
        label6.setText("");
    }


    public void showBothCardsInGui(){
        String playerCard = "";
        //System.out.print("Player card: ");
        for (Integer integer : Turns.player) {
            if(integer != null) {
                //System.out.print(integer + ", ");
                playerCard += integer.toString() + ", ";
            }else {
                playerCard +=  "  , ";
               //System.out.print("  , ");
            }
        }
        //System.out.println();
        String computerCard = "";
        //System.out.print("Computer card: ");
        for (Integer integer : Turns.computer) {
            if (integer != null) {
                //System.out.print(integer + ", ");
                computerCard +=integer + ", ";
            } else {
                computerCard += "  , ";
                //System.out.print("  , ");
            }
        }

        label2.setText(computerCard);
        label4.setText(playerCard);
        //System.out.println();
    }

    class NewButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            playerturnGui();
        }
    }

    class get implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            dice();
        }
    }

    public void dice(){
        Turns.turn = Kubik.throwKubik(5);
        Arrays.sort(Turns.turn);
        //System.out.print("you get numbers:");
        Turns.printTurns();
        label6.setText(getTurns());

        Integer[] aa = Turns.priseThatYouCanTake(Turns.player, Turns.turn);
        label9.setText("Your card of Chance");
        label10.setText(arrayToStr(aa));
    }



    int chanseToturn = 1;

    public void playerturnGui() {
        if (chanseToturn<=3){
            String ans = input.getText();
        String[] answer = ans.split(" ");
        Integer[] integers = new Integer[answer.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = Integer.parseInt(answer[i]);
            //System.out.print(integers[i]+ " ");
        }
        Kubik.change(integers);
        //System.out.print("you get numbers:");
        label6.setText(getTurns());
        Turns.printTurns();
        chanseToturn++;
        //System.out.println();
        System.out.println(chanseToturn);

        Integer[] aa = Turns.priseThatYouCanTake(Turns.player, Turns.turn);
        label9.setText("Your card of Chance");
        label10.setText(arrayToStr(aa));
    }
    }

    public String arrayToStr(Integer[] aa){
        String ans = "";
        for(int i=0;i<aa.length;i++){
            ans+=aa[i]+ " ,";
        }
        return ans;
    }


    Integer num = 0;
    class set implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            if(num<13) {
                Integer[] aa = Turns.priseThatYouCanTake(Turns.player, Turns.turn);
                //Turns.showArr(aa);
                showBothCardsInGui();
                setCorrectPriseGui(Turns.player, aa);
                chanseToturn = 1;

                LogicOfComputerturn.calculateBest(1);
                Turns.printTurns();
                label6.setText(getTurns());


                showBothCardsInGui();
            num++;
            }else{
                countTableGui();
            }
        }
    }

    public void countTableGui(){
        Integer computerSum = 0;
        Integer playerSum = 0;
        for(int i=0;i<13;i++){
            if (Turns.computer[i] == null){
                Turns.computer[i] = 0;
            }
        }

        for(int i=0;i<6;i++){
            computerSum += Turns.computer[i];
            playerSum += Turns.player[i];
        }
        if(computerSum > 63){
            computerSum +=35;
        }
        if(playerSum > 63){
            playerSum+=35;
        }
        for(int i=6;i<13;i++){
            computerSum += Turns.computer[i];
            playerSum += Turns.player[i];
        }
        label9.setText("Player points: "+ playerSum + " Computer points: "+computerSum);
        if(computerSum > playerSum){
            label10.setText("Computer win");
        }else{
            label10.setText("Player win");
        }
    }

    public void setCorrectPriseGui(Integer[] a, Integer[] num){
        //System.out.println("Ok, now turn your place in check:");
        Integer q = Integer.parseInt(input2.getText());
        a[q-1] = num[q-1];
        showBothCardsInGui();
    }


    public String getTurns(){
        String ans = "";
        for (Integer integer : Turns.turn) {
            ans+=integer + ", ";
        }
        return ans;
    }

}
