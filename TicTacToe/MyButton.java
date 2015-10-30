package Java.Programs.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 30.10.2015.
 */
class MyButton extends JButton
{


    private int value;
    public int getValue() {
        return value;
    }
    public MyButton(int name , final GameLogic gameLogic){
        this.value = name;
        this.setPreferredSize(new Dimension(100, 100));
        this.setFont(new Font("Arial", Font.BOLD, 36));
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getText().equals("")){

                    if (gameLogic.getCurrentGamer().equals(gameLogic.player1.getName())){
                        setText(gameLogic.player1.getSymbol());
                        gameLogic.checkWin();
                        gameLogic.setLabel(("Ходит игрок: " + gameLogic.computer1.getName()));
                        if (gameLogic.isStatus()==true) gameLogic.process();

                    }}
                else MyForm.setLabelText("Это поле уже занято, выберите другое");

            }
        });

    }






}