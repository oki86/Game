package TicTacToe;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;


/**
 * Created by az on 30.10.15.
 */
public class Main
{

    public static void main(String[] args)
    {


        final String playerName = JOptionPane.showInputDialog(null, "Введите ваше имя: "  ,"" , 1);


        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run(){

                MmFrame frame = new MmFrame(20, 25 , playerName);
                frame.setLocationRelativeTo(new JDialog());
                frame.setVisible(true);
            }
        });
    }

}



class MmFrame extends JFrame{

    private ArrayList<Button> arrayButton = new ArrayList<>();
    private int  line = 5;
    private JFrame frameConfMenu = null;
    private JFrame frameChangeName = null;
    private GridLayout gridLayout = new GridLayout();
    private JPanel panel;
    private String playerName = null;



    public void ConfMenu(){

        final MmFrame currenFrame ;


        if (frameConfMenu == null) {
            frameConfMenu = new JFrame();
            frameConfMenu.setResizable(false);
            JPanel southPanel = new JPanel();
            JPanel centerPanel = new JPanel();
            southPanel.setLayout(new GridLayout(1,2));
            centerPanel.setLayout(new GridLayout(3,2));

            currenFrame = this;
            currenFrame.setEnabled(false);

            JButton buttonOk = new JButton("Ок");
            JButton buttonCancel = new JButton("Отмена");
            final JTextField width = new JTextField(gridLayout.getColumns() + "");
            final JTextField height = new JTextField(gridLayout.getRows() + "");
            final JTextField countLine = new JTextField(line + "");


            buttonCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currenFrame.setEnabled(true);
                    frameConfMenu.setVisible(false);


                }
            });

            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int w = Integer.parseInt(height.getText());
                    int h = Integer.parseInt(width.getText());
                    int l = Integer.parseInt(countLine.getText());
                    if (w < l || h < l){
                        JOptionPane.showMessageDialog(null,"Количетсво строк для зачеркивания \n меньше чем размерность игрового поля","Неверные параметры", JOptionPane.WARNING_MESSAGE);
                    }else if ( w < 3 || h < 3 || w >= 150 || h >= 150 ){
                        JOptionPane.showMessageDialog(null,"Высота и длинна таблицы должны быть больше 3 и не превышать 150 \n","Неверные параметры", JOptionPane.WARNING_MESSAGE);
                    }else {
                        gridLayout = new GridLayout(w,h);
                        line = l;
                        currenFrame.remove(panel);
                        panel = new JPanel();
                        panel.setLayout(gridLayout);
                        arrayButton = new ArrayList<>();
                        currenFrame.add(panel);

                        for (int i = 0 , k = 0; i < w; i++){
                            for (int j = 0; j < h; j++ , k++){
                                arrayButton.add(new Button(j , i , "" + k ) );
                                panel.add(arrayButton.get(k));
                            }
                        }

                        pack();
                        currenFrame.repaint();

//                        new Logic(arrayButton , w, h , l , playerName);
                        Logic.setRows(w);
                        Logic.setColumns(h);
                        Logic.setLine(l);
                        currenFrame.setEnabled(true);
                        frameConfMenu.setVisible(false);
                        Logic.createNew(arrayButton , gridLayout.getRows(), gridLayout.getColumns() , line , playerName);
                    }
                }
            });




            southPanel.add(buttonOk);
            southPanel.add(buttonCancel);


            centerPanel.add(new JLabel("Длинна:",0));
            centerPanel.add(width);
            centerPanel.add(new JLabel("Высота:", 0));
            centerPanel.add(height);
            centerPanel.add(new JLabel("Выиграшных линий:", 0));
            centerPanel.add(countLine);
            // frame.setP


            frameConfMenu.add(southPanel , BorderLayout.SOUTH);
            frameConfMenu.add(centerPanel , BorderLayout.CENTER);
            frameConfMenu.pack();
            frameConfMenu.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    currenFrame.setEnabled(true);
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
            frameConfMenu.setLocationRelativeTo(currenFrame);
            frameConfMenu.setTitle("Изменение игрового поля");

        }

        frameConfMenu.setVisible(true);

    }


    public MmFrame(int y, int x , String playerName){


        JMenuBar jMenuBar = new JMenuBar();
        this.playerName = playerName;
        JMenu menuFile = new JMenu("Файл");
        JMenu menuConfig = new JMenu("Настройки");
        JMenu menuInfo = new JMenu("Информация");

        JMenuItem itemNewGame = new JMenuItem("Новая Игра");
        JMenuItem itemQuit = new JMenuItem("Выход");
        JMenuItem itemStatistic = new JMenuItem("Статистика");
        JMenuItem itemInstruction = new JMenuItem("Инструкция");
        JMenuItem itemAbout = new JMenuItem("О программе");
        JMenuItem itemCreateNewField = new JMenuItem("Изменение игрового поля");
        JMenuItem itemChangeName = new JMenuItem("Изменение имени");
        JMenuItem itemStatisticReset = new JMenuItem("Сброс статистики");

        itemNewGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Logic.gameStart();
            }
        });
        itemStatisticReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int d =JOptionPane.showConfirmDialog(null,"Вы действительно хотите обнулить статистику?","",0);
                if (d==0){
                    Logic.getComp().setCountWins(0);
                    Logic.getHuman().setCountWins(0);
                    Logic.setNoneWins(0);
                }
            }
        });
        itemQuit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        itemStatistic.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {JOptionPane.showMessageDialog(null,"Игрок " + Logic.getHuman().getName() + " выиграл: " + Logic.getHuman().getCountWins() + " раз.\n" +
                    "Компьютер " + Logic.getComp().getName() + " выиграл: " + Logic.getComp().getCountWins() + " раз. \n\n" +
                    "В ничью сиграно: " + Logic.getNoneWins() + " раз."

                    ,"Статистика",JOptionPane.INFORMATION_MESSAGE,null); }
        });
        itemAbout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {JOptionPane.showMessageDialog(null,"С рекомендациями по доработке AI \n обращайтесь на okki_dokki@mail.ru","О программе",1,null); }
        });
        itemCreateNewField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfMenu();
            }
        });
        itemInstruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Для того что бы одержать победу вам необходимо \n " +
                        "заполнить  подряд 5 квадратов по вертикали или \n " +
                        "горизонтали или диагонали. Но будьте бдительны!\n " +
                        "Вам противостоит имкуственный интелект.","Инструкция",JOptionPane.INFORMATION_MESSAGE,null);
            }
        });
        itemChangeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (frameChangeName == null){

                    frameChangeName = new JFrame();
                    JPanel panel1 = new JPanel(new GridLayout(2,2));

                    final JTextField jTextField = new JTextField("");
                    JButton buttonOk = new JButton("Ок");
                    JButton buttonCancel = new JButton("Cancel");
                    final JRadioButtonMenuItem radioUser = new JRadioButtonMenuItem("Игрока");
                    radioUser.setSelected(true);
                    final JRadioButtonMenuItem radioComp = new JRadioButtonMenuItem("Компьютер");


                    JPanel panel2North = new JPanel(new GridLayout(2,1));
                    panel2North.add(radioUser);
                    panel2North.add(radioComp);

                    ButtonGroup group = new ButtonGroup();
                    group.add(radioUser);
                    group.add(radioComp);

                    frameChangeName.add(panel2North , BorderLayout.NORTH);




                    buttonCancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frameChangeName.setVisible(false);
                        }
                    });

                    buttonOk.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!jTextField.getText().equals("")){
                                if (radioUser.isSelected())Logic.getHuman().setName(jTextField.getText());
                                else Logic.getComp().setName(jTextField.getText());
                            }else JOptionPane.showMessageDialog(null,"Имя небыло изменено, так как вы его не указали!","Изменение имени",JOptionPane.ERROR_MESSAGE);

                            frameChangeName.setVisible(false);

                        }
                    });

                    panel1.add(new JLabel("Введите имя:"));
                    panel1.add(jTextField);
                    panel1.add(buttonOk);
                    panel1.add(buttonCancel);

                    frameChangeName.add(panel1,BorderLayout.CENTER);
                    frameChangeName.pack();
                    frameChangeName.setLocationRelativeTo(new JDialog());
                    frameChangeName.setVisible(true);
                }

                frameChangeName.setVisible(true);

            }
        });


        menuFile.add(itemNewGame);
        menuFile.addSeparator();
        menuFile.add(itemStatisticReset);
        menuFile.add(itemQuit);
        menuInfo.add(itemStatistic);
        menuInfo.add(itemInstruction);
        menuInfo.addSeparator();
        menuInfo.add(itemAbout);
        menuConfig.add(itemCreateNewField);
        menuConfig.add(itemChangeName);

        jMenuBar.add(menuFile);
        jMenuBar.add(menuConfig);
        jMenuBar.add(menuInfo);


        gridLayout = new GridLayout(x , y);
        panel = new JPanel(gridLayout);

        add(jMenuBar, BorderLayout.NORTH);
        add(panel , BorderLayout.CENTER);


        for (int i = 0 , k = 0; i < gridLayout.getRows(); i++)
        {
            for (int j = 0; j < gridLayout.getColumns(); j++ , k++)
            {
                arrayButton.add(new Button(j , i , "" + k ) );
                panel.add(arrayButton.get(k));
            }
        }

        Logic.createNew(arrayButton , gridLayout.getRows(), gridLayout.getColumns() , line , playerName);
        pack();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}


class Button extends JButton implements ActionListener{
    private int x;
    private int y;
    private String name;
    private Line2D.Double lineThatWin = null;

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (lineThatWin != null){
            g2.setPaint(Color.blue);
            g2.setStroke(new BasicStroke(2));
            g2.draw(lineThatWin);
        }

    }

    public void paint(int x){
        repaint();
        if (x==1)
            lineThatWin =  new Line2D.Double( new Point(getWidth()/2,0), new Point(getWidth()/2 ,getHeight()) );
        else if (x==2)
            lineThatWin =  new Line2D.Double( new Point(0,getHeight()/2), new Point(getWidth() ,getHeight()/2) );
        else if (x==3)
            lineThatWin =  new Line2D.Double( new Point(0,0), new Point(getWidth() ,getHeight()) );
        else
            lineThatWin =  new Line2D.Double( new Point(0,getHeight()), new Point(getWidth() ,0) );
    }


    public void depaint(){
        lineThatWin = null;
    }



    public Button(int x, int y , String name ){
        this.x = x;
        this.y = y;
        this.name = name;
        setText("");

        addActionListener(this);
        this.setPreferredSize(new Dimension(45, 45));

    }

    public String getName(){
        return name;
    }

    public int getXl()
    {
        return x;
    }

    public int getYl()
    {
        return y;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (getText().equals("") && Logic.isGameEnable() == true){
            setBackground(Color.green);
            setText(Logic.getHuman().getMurk());
            Logic.process();
        }

    }
}






