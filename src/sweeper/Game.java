package sweeper;

public class Game
{
    private Mine mine;
    private Flag flag;

    public GameState getState() {
        return state;
    }

    private GameState state;
    public Game(int cols, int rows, int mines)
    {
        Ranges.setSize(new Coord(cols, rows));
        mine = new Mine(mines);
        flag = new Flag();
    }

    public void start()
    {
        mine.start();
        flag.start();
        state = GameState.PLAYED;
    }
    public box getBox(Coord coord)
    {
        if(flag.get(coord) == box.OPENED)
            return mine.get(coord);
        else
            return  flag.get(coord);
    }

    public int MinesRemain()
    {
        return mine.getMines()-flag.getTotalFlags();
    }

    public void pressLButton(Coord coord)
    {
        if(!GameOver())
        {
            openBox(coord);
            checkWin();
        }
    }



    private void checkWin()
    {
        if (state == GameState.PLAYED)
            if(flag.getCountOfClsBoxes() == mine.getMines())
                state = GameState.WINNER;
    }

    private void openMines(Coord mined)
    {
        state = GameState.BOMBED;
        flag.setOpenMine(mined);
        for (Coord coord:Ranges.getAllCoords())
            if(mine.get(coord) == box.MINE)
                flag.setOpenClMine(coord);
            else
                flag.setNMineCls(coord);
    }
    void OpenAr(Coord coord)
    {
        if(flag.getFlagAr(coord) == mine.get(coord).getNum())
            for (Coord ar: Ranges.getCoordsAroung(coord))
                if(flag.get(ar) == box.CLOSED)
                    openBox(ar);

    }
    private void openBox(Coord coord)
    {
        switch (flag.get(coord))
        {
            case OPENED -> {OpenAr(coord); return;}
            case FLAG -> {return;}
            case CLOSED ->
            {
                switch (mine.get(coord))
                {
                    case NUM0 -> {openEmpBoxes(coord);return;}
                    case MINE -> {openMines(coord); return;}
                    default -> {flag.setOpenBox(coord);return;}
                }
            }
        }
    }

    void openEmpBoxes(Coord coord)
    {
        flag.setOpenBox(coord);
        for (Coord around : Ranges.getCoordsAroung(coord))
                openBox(around);
    }

    public void pressRButton(Coord coord)
    {
        if(!GameOver())
            flag.tooggleFlagBox(coord);
    }

    boolean GameOver()
    {return state!=GameState.PLAYED;}
}
