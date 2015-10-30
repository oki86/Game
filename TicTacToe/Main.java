package Java.Programs.TicTacToe;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 30.10.2015.
 */
public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MyForm();
                frame.setVisible(true);
            }
        });
    }

}
