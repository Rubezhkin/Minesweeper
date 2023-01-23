package sweeper;

import java.util.ArrayList;

class Flag
{
    private Matrix flagMap;
    private int totalFlags;
    private int countOfClsBoxes;

    void start()
    {
        flagMap = new Matrix(box.CLOSED);
        countOfClsBoxes = Ranges.getSize().x*Ranges.getSize().y;
        totalFlags=0;
    }

    box get (Coord coord)
    {
        return flagMap.get(coord);
    }
    void setOpenBox(Coord coord)
    {
        flagMap.set(coord, box.OPENED);
        countOfClsBoxes--;
    }

    void setOpenMine(Coord coord)
    {
        flagMap.set(coord, box.BOMBED);
    }

    void tooggleFlagBox(Coord coord)
    {
        switch (flagMap.get(coord))
        {
            case FLAG -> {setClBox(coord); break;}
            case CLOSED -> {setFlagBox(coord);break;}
        }
    }


    void setClBox(Coord coord)
    {
        flagMap.set(coord, box.CLOSED);
        totalFlags--;
    }
    void setFlagBox(Coord coord)
    {
        flagMap.set(coord, box.FLAG);
        totalFlags++;
    }

    int getCountOfClsBoxes()
    {
        return  countOfClsBoxes;
    }

    void setOpenClMine(Coord coord)
    {
        if(flagMap.get(coord) == box.CLOSED)
            flagMap.set(coord, box.OPENED);
    }

    void setNMineCls(Coord coord)
    {
        if(flagMap.get(coord) == box.FLAG)
            flagMap.set (coord, box.NOMINE);
    }

    int getFlagAr(Coord coord)
    {
        int count = 0;
        for(Coord arround: Ranges.getCoordsAroung(coord))
            if(flagMap.get(arround) ==box.FLAG)
                count++;
        return count;
    }

    int getTotalFlags()
    {
        return totalFlags;
    }
}
