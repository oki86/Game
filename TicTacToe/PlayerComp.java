package TicTacToe;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by az on 03.11.15.
 */
public class PlayerComp extends Player
{

    ArrayList<Integer[]> probabilityArrays ;

    public PlayerComp(String name, String murk)
    {
        super(name, murk);


    }


    public void ai(){
        int mySerious = -1;
        int enemySerious = -1;

        ArrayList<Integer[]> TarEnemy = new ArrayList<>();
        ArrayList<Integer[]> TarMy = new ArrayList<>();


        if (Logic.currentStep < 1){

            int dd = (int)(Math.random() * Logic.rows) * (int)(Math.random() * Logic.columns);
            Logic.buttons.get(dd ).setText(Logic.comp.getMurk());
            //
            Logic.buttons.get(dd).setBackground(Color.red);

            return;
        }


        int size = Logic.probabilityArrays.size();


        for (int z = 0; z < size; z++)
        {

            Integer[] a = Logic.probabilityArrays.get(z);
            int count = 0;
            String X;
            int tempMySerious = 0;
            int tempEnemySerious = 0;
            String tempTarget = null;



            for (int i = 0; i < Logic.getLine(); i++){


                X = Logic.buttons.get(a[i]).getText();

                if (X.equals("") && tempTarget == null) tempTarget = a[i].toString();

                if ( X.equals(Logic.comp.getMurk()) )
                {
                    tempMySerious++;
                    tempEnemySerious = tempEnemySerious - Logic.getLine();
                }

                if ( X.equals(Logic.human.getMurk()) ){
                    tempEnemySerious++;
                    tempMySerious = tempMySerious - Logic.getLine();
                }



            }






            if (tempEnemySerious > enemySerious){
                TarEnemy = new ArrayList<>();
                TarEnemy.add(Logic.probabilityArrays.get(z));
                enemySerious = tempEnemySerious;
            }else if (tempEnemySerious == enemySerious){
                TarEnemy.add(Logic.probabilityArrays.get(z));
            }

            if (tempMySerious > mySerious){
                TarMy = new ArrayList<>();
                TarMy.add(Logic.probabilityArrays.get(z));
                mySerious = tempMySerious;
            }else if (tempMySerious == mySerious){
                TarMy.add(Logic.probabilityArrays.get(z));
            }





        }





        if (mySerious == (Logic.getLine() - 1) ){
            int scan = scan(TarMy);
            Logic.buttons.get(scan).setText(Logic.comp.getMurk());
            //
            Logic.buttons.get(scan).setBackground(Color.red);
        }else if (enemySerious == (Logic.getLine() - 1)){
            int scan = scan(TarEnemy);
            Logic.buttons.get(scan).setText(Logic.comp.getMurk());
            //
            Logic.buttons.get(scan).setBackground(Color.red);
        }else if (mySerious <= 0 && enemySerious <= 0){
            for (Button b : Logic.buttons){
                if (b.getText().equals(""))
                {
                    b.setText(Logic.comp.getMurk());
                    //
                    b.setBackground(Color.red);
                    return;
                }
            }
        }else if (mySerious >= enemySerious){
            int scan = scan(TarMy);
            Logic.buttons.get(scan).setText(Logic.comp.getMurk());
            //
            Logic.buttons.get(scan).setBackground(Color.red);
        }else {
            int scan = scan(TarEnemy);
            Logic.buttons.get(scan).setText(Logic.comp.getMurk());
            //
            Logic.buttons.get(scan).setBackground(Color.red);
        }





    }


    public int scan(ArrayList<Integer[]> arrayList){


        HashMap<Integer ,Integer> hashMap = new HashMap<>();




        for (Integer[] e : arrayList){
            for (int k : e){

                if (hashMap.containsKey(k)){

                    int t = hashMap.get(k);
                    hashMap.put(k,++t);
                }else {


                    hashMap.put(k, 1);
                }
            }


        }


        int i = 0;
        int max_value= 0;
        for (Map.Entry<Integer, Integer>uu : hashMap.entrySet()){
            if (i < uu.getValue() && Logic.buttons.get(uu.getKey()).getText().equals("")){
                i = uu.getValue();
                max_value = uu.getKey();
            }
        }

        return max_value;




    }






}
