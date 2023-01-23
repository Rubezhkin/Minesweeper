package sweeper;

class Mine
{
    private Matrix Map;
    private int totalmines;

    Mine (int totalmines)
    {
        this.totalmines= totalmines;
    }

    void start()
    {
        Map = new Matrix(box.NUM0);
        for(int i = 0; i < totalmines;i++)
            placeMine();
    }

    box get (Coord coord)
    {
        return Map.get(coord);
    }

    private void fixMinesCount()
    {
        int maxmines = Ranges.getSize().x * Ranges.getSize().y;
        if(totalmines >= maxmines)
            totalmines = maxmines-1;
    }

    private void  placeMine() {
        while (true) {
            Coord coord = Ranges.getRandCoord();
            if (box.MINE == Map.get(coord))
                continue;
            Map.set(coord, box.MINE);
            incNumArMines(coord);
            break;
        }
    }

    int getMines()
    {
        return totalmines;
    }

    private void incNumArMines(Coord coord)
    {
        for (Coord around : Ranges.getCoordsAroung(coord))
        {
            if(box.MINE!= Map.get(around))
                Map.set(around, Map.get(around).nextNumberBox());
        }
    }

}
