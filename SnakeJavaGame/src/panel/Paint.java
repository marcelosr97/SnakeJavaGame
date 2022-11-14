package panel;

import entities.Food;
import entities.Snake;

import javax.swing.JComponent;
import java.awt.*;

public class Paint extends JComponent
{
    /* Private */
    private Frame frame;
    private Snake snake;
    private Food food;
    private TypeWindow currentWindow;
    private int snakeLength;
    private int[][] snakePos;
    private int xFoodPos;
    private int yFoodPos;

    /* Public */
    public enum TypeWindow {START, GAME, END};
    public Paint()
    {
        this.currentWindow = TypeWindow.START;
    }

    public void paintComponent(Graphics g)
    {
        switch(currentWindow)
        {
            case START:
                printStartWindow(g);
                break;
            case GAME:
                printGameWindow(g);
                break;
            case END:
                printEndWindow(g);
        }
    }

    public void setWindows(TypeWindow typeWindow)
    {
        currentWindow = typeWindow;
    }

    public void setSnakeLength(int snakeLength)
    {
        this.snakeLength = snakeLength;
    }
    public void setSnakePosition(int[][] snakePos)
    {
        this.snakePos = snakePos;
    }

    public void setFoodPosition(int xFoodPos, int yFoodPos)
    {
        this.xFoodPos = xFoodPos;
        this.yFoodPos = yFoodPos;
    }
    private void printEndWindow(Graphics g)
    {

    }

    private void printGameWindow(Graphics g)
    {
        /* Draw border */
        /* Set color white */
        g.setColor(Color.WHITE);
        /* Draw border */
        g.drawRect(Frame.FRAME_OFFSET,Frame.FRAME_OFFSET,Frame.MAX_X, Frame.MAX_Y);
        /* Set color green */
        g.setColor(Color.GREEN);
        /* Print snake */
        for(int posIndex = 0; posIndex < snakeLength; posIndex++)
        {
            g.fillRect(snakePos[posIndex][0], snakePos[posIndex][1], Snake.SNAKE_WIDTH, Snake.SNAKE_HEIGHT);
        }
        /* Set color red */
        g.setColor(Color.RED);
        /* Print food */
        g.fillRect(xFoodPos, yFoodPos, Food.FOOD_WIDTH, Food.FOOD_HEIGHT);
    }

    private void printStartWindow(Graphics g)
    {

    }
}
