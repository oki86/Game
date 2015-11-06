package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Admin on 30.10.2015.
 */
public class MyForm  extends JFrame {


    private static ArrayList<MyButton> buttons = new ArrayList<MyButton>();
    private static JLabel label = new JLabel(" ");

    GameLogic logic;


    public MyForm() {


        final JPanel panel = new JPanel(new GridLayout(3, 3));

        logic = new GameLogic(buttons);
        for (int i = 0; i < 9; i++) {
            buttons.add(new MyButton(i, logic));
            panel.add(buttons.get(i));
        }


        setLocationRelativeTo(null);
        setResizable(false);


        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        //JMenu configMenu = new JMenu("Config");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
       // menuBar.add(configMenu);
        menuBar.add(helpMenu);


        JMenuItem stat = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "\n" + logic.player1.getName() + " выиграл: " + logic.player1.getCountWin() +
                        "\n" + logic.computer1.getName() + " выиграл: " + logic.computer1.getCountWin() +
                        "\nНичьих: " + logic.getNoneWins());
            }
        });
        JMenuItem open = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.startGame();
            }
        });

        JMenuItem close = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        stat.setText("Statistic");
        open.setText("Start");
        close.setText("Close");


        helpMenu.add(stat);
        fileMenu.add(open);
        fileMenu.add(close);
        setJMenuBar(menuBar);

        this.add(panel, BorderLayout.CENTER);
        this.add(label, BorderLayout.SOUTH);


        this.setTitle("TicTacToe");
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }


    public static String getLabelText() {
        return label.getText();
    }

    public static void setLabelText(String label) {
        MyForm.label.setText(label);
    }


}