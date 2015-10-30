package Java.Programs.TicTacToe;

/**
 * Created by Admin on 30.10.2015.
 */
public class CompPlayer extends Player
{
    public CompPlayer(String name, String symbol)
    {
        super(name, symbol);
    }


    public  int ai( HumanPlayer player , GameLogic gameLogic){

        if (gameLogic.getCount() <= 1) return (int)(Math.random()*gameLogic.getButtons().size() );


        String markup = getSymbol();
        String enemyMarkup = player.getSymbol();
        int serious;
        int myMaxSerious = -1;
        int enemySerious;
        int enemyMaxSerious = -1;
        int myMainTarget = -1;
        int enemyMainTarget = -1;
        int emptyPlace = -1;
        String temp;
        int[][] ss = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};




        for (int i = 0; i < ss.length; i++) {

            serious = 0;
            enemySerious = 0;
            emptyPlace = -1;

            for (int j = 0; j < ss[i].length; j++) {
                temp = gameLogic.getButtons().get(ss[i][j]).getText();

                if (temp.equals("")) emptyPlace = gameLogic.getButtons().get(ss[i][j]).getValue();
                else if (temp.equals(enemyMarkup) ){
                    enemySerious++;
                }
                else if ( !temp.equals("") && temp.equals(markup) ){
                    serious++;
                }

            }

            if (serious==2 && emptyPlace > -1) return emptyPlace;

            if (serious > myMaxSerious && emptyPlace > -1){
                myMaxSerious = serious;
                myMainTarget = emptyPlace;
            }

            if (enemySerious > enemyMaxSerious && emptyPlace > -1){
                enemyMaxSerious = enemySerious;
                enemyMainTarget = emptyPlace;
            }


        }




        if (enemyMaxSerious == 2 || enemyMaxSerious > myMaxSerious ) return enemyMainTarget;
        else return myMainTarget;


    }








}