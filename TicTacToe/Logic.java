package TicTacToe;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by az on 04.11.15.
 */
public final class Logic{
    static private ArrayList<Button> buttons;
    static private int rows;
    static private int columns;
    static private int noneWins;
    static private int line;
    static private String currentPlayer = null;
    static private ArrayList<Integer[]> probabilityArrays ;
    static private PlayerComp comp;
    static private PlayerHuman human;
    static private boolean gameEnable = true;
    static private int currentStep;

    static public int getLine()
    {
        return line;
    }

    public Logic(){


    }


public static void createNew(ArrayList<Button> buttons1 , int rows1, int columns1 , int line1, String playerName){
    comp = new PlayerComp("Злой Комп","O" );
    human = new PlayerHuman(playerName , "X");
    buttons = buttons1;
    rows = rows1;
    columns = columns1;
    line = line1;
    noneWins = 0;
    gameStart();
}


    public static void addProbability(){
        probabilityArrays = new ArrayList<>();


        for (Button b : buttons){


            if (b.getXl()- line >= -1 && b.getYl() + line <= rows){

                Integer[] dd = new Integer[line];
                for (int i = 0; i < line; i++)
                {
                    dd[i] = (b.getXl() - i) + (b.getYl()+i) * columns;
                }
                probabilityArrays.add(dd);
            }


            if (b.getXl()+ line <= columns && b.getYl()+ line <= rows){

                Integer[] dd = new Integer[line];
                for (int i = 0; i < line; i++)
                {
                    dd[i] = b.getXl()+ i + columns * (b.getYl() +i);

                }
                probabilityArrays.add(dd);
            }


            if (b.getYl() + line <= rows){
                Integer[] dd = new Integer[line];
                for (int i = 0; i < line; i++)
                {
                    dd[i] = (b.getYl() + i) * columns + b.getXl();
                }
                probabilityArrays.add(dd);
            }


            if ( b.getXl() + line <= columns){
                Integer[] dd = new Integer[line];
                for (int i = 0; i < line; i++)
                {
                    dd[i] = b.getYl() * columns + b.getXl() + i;
                }
                probabilityArrays.add(dd);
            }


        }

    }


    public static void gameEnd(){
        Logic.gameEnable = false;
    }

    public static void gameStart(){
        addProbability();
        gameEnable = true;
        currentStep = 0;


        for (Button b : buttons){
            b.setText("");
            b.setBackground(new ColorUIResource(238,238,238));
            b.depaint();

        }

        if ((int)(Math.random()*2)==1)
            currentPlayer = human.getName();
        else  {
            currentPlayer = comp.getName();
            process();}








    }


    public static void process(){
        if (currentPlayer.equals(comp.getName())){
            comp.ai();
        }
        checkWin();
    }


    public  static int cleave(Integer[] array){
        int x1 = buttons.get(array[0]).getXl();
        int y1 = buttons.get(array[0]).getYl();
        int x2 = buttons.get(array[Logic.line - 1]).getXl();
        int y2 = buttons.get(array[Logic.line - 1]).getYl();





        if (x1 ==x2 && y1 < y2) return 1;
        else if (x1 <x2 && y1 == y2) return 2;
        else if (x1<x2 && y1 < y2) return 3;
        else  return 4;

    }




    public static void checkWin(){



        for ( Integer[] a : probabilityArrays){

            for (Integer d : a) System.out.print(d + ";");
            System.out.println();
            int countHum = 0;
            int countComp = 0;
            String X;
            for (int i = 0; i < line; i++)
            {
                X = buttons.get(a[i]).getText();
                if (X.equals(human.getMurk())) countHum ++;
                if (X.equals(comp.getMurk()) ) countComp ++;

            }

            if (countHum == line || countComp == line){


                int n = cleave(a);

                for (Button button : buttons){
                    for (int i = 0; i < a.length; i++) {
                        if ( Integer.parseInt(button.getName())== a[i]){
                            button.paint(n);
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, currentPlayer + " выиграл!!!","Win!!",1);
                if (comp.getName().equals(currentPlayer))comp.setCountWins(comp.getCountWins() + 1);
                else human.setCountWins(human.getCountWins() + 1);
                gameEnd();
                                return;
            }   else if (currentStep == rows * columns){
                JOptionPane.showMessageDialog(null, "Ничья" , "", 1);
                noneWins++;
                gameEnd();
                return;
            }
        }


        currentStep++;

       if (currentPlayer.equals(comp.getName()) || currentPlayer == null) currentPlayer = human.getName();
      else
       {
           currentPlayer = comp.getName();
           process();

       }



    }

    public static boolean isGameEnable() {
        return gameEnable;
    }

    public static void setNoneWins(int noneWins) {
        Logic.noneWins = noneWins;
    }

    public static void setRows(int rows) {
        Logic.rows = rows;
    }

    public static void setLine(int line) {
        Logic.line = line;
    }

    public static void setColumns(int columns) {
        Logic.columns = columns;
    }

    public static PlayerHuman getHuman() {
        return human;
    }

    public static ArrayList<Integer[]> getProbabilityArrays() {
        return probabilityArrays;
    }

    public static int getCurrentStep() {
        return currentStep;
    }

    public static PlayerComp getComp() {
        return comp;
    }

    public static ArrayList<Button> getButtons() {
        return buttons;
    }

    public static int getRows() {
        return rows;
    }

    public static int getColumns() {
        return columns;
    }

    public static int getNoneWins() {
        return noneWins;
    }
}
