package Java.Programs.TicTacToe;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Admin on 30.10.2015.
 */
public class GameLogic
{

    private ArrayList<MyButton> buttons;
    private int count = 0;
    private String currentGamer ;

    public ArrayList<MyButton> getButtons()
    {
        return buttons;
    }

    public int getNoneWins()
    {
        return noneWins;
    }

    private int noneWins = 0;

    public boolean isStatus()
    {
        return status;
    }

    private boolean status = true;



    private String label = "";
    public void setLabel(String label)
    {
        this.label = label;
    }
    public String getLabel()
    {
        return label;
    }
    public int getCount()
    {
        return count;
    }

    HumanPlayer player1 = new HumanPlayer("Alex" , "X");
    CompPlayer  computer1 = new CompPlayer("Злой Компьютер", "O");




    public GameLogic(ArrayList<MyButton> buttons){
        this.buttons = buttons;
        setCurrentGamer(player1.getName());


    }


    public String getCurrentGamer()
    {
        return currentGamer;
    }

    public void setCurrentGamer(String currentGamer)
    {
        this.currentGamer = currentGamer;
    }

    public void checkWin(){
        count++;

        if (    buttons.get(1).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(7).getText() &&  !buttons.get(1).getText().equals("")||
                buttons.get(3).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(5).getText() &&  !buttons.get(3).getText().equals("")||
                buttons.get(0).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(8).getText() &&  !buttons.get(0).getText().equals("")||
                buttons.get(6).getText() == buttons.get(4).getText() && buttons.get(4).getText() == buttons.get(2).getText() &&  !buttons.get(6).getText().equals("")||
                buttons.get(0).getText() == buttons.get(3).getText() && buttons.get(3).getText() == buttons.get(6).getText() &&  !buttons.get(0).getText().equals("")||
                buttons.get(6).getText() == buttons.get(7).getText() && buttons.get(7).getText() == buttons.get(8).getText() &&  !buttons.get(6).getText().equals("")||
                buttons.get(0).getText() == buttons.get(1).getText() && buttons.get(1).getText() == buttons.get(2).getText() &&  !buttons.get(0).getText().equals("")||
                buttons.get(2).getText() == buttons.get(5).getText() && buttons.get(5).getText() == buttons.get(8).getText() &&  !buttons.get(2).getText().equals("")){
            Finish(getCurrentGamer());
        }else if (count == 9 &&  status == true) {Finish(null);}
        else changePlayer();
    }


    public void changePlayer(){
        if (getCurrentGamer().equals(computer1.getName())) setCurrentGamer(player1.getName());
        else setCurrentGamer(computer1.getName());
    }



    public void Finish(String st){
        String text;
        status = false;
        if (st == null){
            noneWins++;
            text = "Ничья =) \n Хотите начать еще одну игру?";
        }else if (st.equals(player1.getName())) {
            player1.setCountWin(player1.getCountWin() + 1);
            text = "Выиграл: " + player1.getName() + " \n Хотите начать еще одну игру?";
        }
        else {
            computer1.setCountWin(computer1.getCountWin() + 1);
            text = "Выиграл: " + computer1.getName() + " \n Хотите начать еще одну игру?";
        }

        int reply = JOptionPane.showConfirmDialog(null, text, "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            startGame();

        }else{
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setEnabled(false);
            }
        }


    }



    public void startGame(){

        //Случайный игрок начинает
        if ((int)(Math.random()*2 + 1)==1) setCurrentGamer(player1.getName());
        else setCurrentGamer(computer1.getName());


        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setEnabled(true);
            buttons.get(i).setText("");
        }
        count = 0;
        status = true;
        process();

    }


    public void process(){

        if (getCurrentGamer().equals(computer1.getName()) &&  status == true){

            int n = computer1.ai(player1, this);

            buttons.get(n).setText(computer1.getSymbol());
            checkWin();

            MyForm.setLabelText("Ходит игрок: " + player1.getName());

        }





    }













}
