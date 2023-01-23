package sweeper;

public enum box
{
    NUM0,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    MINE,
    OPENED,
    CLOSED,
    FLAG,
    BOMBED,
    NOMINE;

    public Object image;
    box nextNumberBox()
    {
        return box.values()[this.ordinal()+1];
    }

    int getNum()
    {
        return this.ordinal();
    }
}
