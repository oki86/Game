package TicTacToe;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by az on 04.11.15.
 */
public final class Logic{





    static ArrayList<Button> buttons;
    static int rows;
    static int columns;
    static int noneWins;
    static int line;
    static String currentPlayer = null;
    static ArrayList<Integer[]> probabilityArrays ;
    static PlayerComp comp;
    static PlayerHuman human;
    static boolean gameEnable = true;
    static int currentStep;

    static public int getLine()
    {
        return line;
    }

    public Logic(ArrayList<Button> buttons , int rows, int columns , int line, String playerName ){
        comp = new PlayerComp("Злой Комп","O" );
        human = new PlayerHuman(playerName , "X");
        this.buttons = buttons;
        this.rows = rows;
        this.columns = columns;
        this.line = line;
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


            if (b.getXl()+ line <= rows && b.getYl()+ line <= columns){
                Integer[] dd = new Integer[line];
                for (int i = 0; i < line; i++)
                {
                    dd[i] = (b.getXl() + i) * columns + b.getYl() + i;
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
//        for (Button b : buttons){
//            b.setEnabled(false);
//        }
        Logic.gameEnable = false;

    }

    public static void gameStart(){


        gameEnable = true;
        currentStep = 0;
        addProbability();

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


}
