package sweeper;

class Matrix
{
    private box [][] matrix;

    Matrix(box defaultBox)
    {
        matrix = new box[Ranges.getSize().x][Ranges.getSize().y];
        for(Coord coord: Ranges.getAllCoords())
            matrix[coord.x][coord.y] = defaultBox;
    }

    box get(Coord coord)
    {
        if(Ranges.inRange(coord))
            return matrix[coord.x][coord.y];
        return null;
    }

    void  set (Coord coord, box b)
    {
        matrix [coord.x][coord.y] = b;
    }
}
