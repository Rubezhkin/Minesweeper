import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Stream;

import sweeper.Coord;
import sweeper.Ranges;
import sweeper.box;
import sweeper.Game;

public class Minesweeper extends JFrame
{
    private Game game;

    private JPanel panel;
    private JLabel label;
    private  final int COLS = 9;
    private  final int ROWS = 9;
    private final int MINES = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args)
    {
        new Minesweeper();
    }

    private Minesweeper()
    {
        game = new Game(COLS, ROWS, MINES);
        game.start();
        setImages();
        initPanel();
        initFrame();
        initLabel();
    }

    private void initLabel()
    {
        label= new JLabel("Welcome!");
        add (label,BorderLayout.SOUTH);
    }
    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
             {
                 super.paintComponent(g);
                 for(Coord coord: Ranges.getAllCoords())
                 {
                     g.drawImage((Image)game.getBox(coord).image,
                             coord.x*IMAGE_SIZE,
                             coord.y*IMAGE_SIZE,
                             this);
                 }
             }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX()/IMAGE_SIZE;
                int y = e.getY()/IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if(e.getButton() == MouseEvent.BUTTON1)
                    game.pressLButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3)
                    game.pressRButton(coord);
                if(e.getButton()==MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x*IMAGE_SIZE,
                Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
    }

    private String getMessage()
    {
        switch (game.getState())
        {
            case PLAYED -> {return "Remain " + Integer.toString(game.MinesRemain()) + " mines" ;}
            case BOMBED -> {return "Game over!";}
            case WINNER -> {return "You win!";}
            default -> {return "";}
        }
    }

    private void initFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("mine"));
    }

    private void setImages()
    {
        for(box b: box.values())
            b.image = getImage(b.name());
    }

    private Image getImage(String name)
    {
        String filename = "img/" + name.toLowerCase()+".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
