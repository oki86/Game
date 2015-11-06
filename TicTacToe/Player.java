package TicTacToe;

/**
 * Created by az on 03.11.15.
 */
public class Player
{

    private String name;
    private String murk;
    private int countWins = 0;


    public Player(String name, String murk){

        if (name==null || name.equals("")) this.name = "Player";
        else this.name = name;
        this.murk = murk;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMurk()
    {
        return murk;
    }

    public void setMurk(String murk)
    {
        this.murk = murk;
    }

    public int getCountWins()
    {
        return countWins;
    }

    public void setCountWins(int countWins)
    {
        this.countWins = countWins;
    }
}
