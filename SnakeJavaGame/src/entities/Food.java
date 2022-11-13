package entities;

import panel.Frame;

import java.util.Random;

public class Food
{
    private static final int UPPER_BOUND = 16;
    private static final int MAX_POSITION = 32;
    private Random rand = new Random();
    public int xPos;
    public int yPos;
    private Frame frame;
    private Snake snake;

    public Food(Frame frame, Snake snake)
    {
        this.snake = snake;
        this.frame = frame;
        generatePosition();
    }

    public void generatePosition()
    {
        /* Create flag to check if the position is valid */
        boolean posValid = false;

        do
        {
            /* Generate random position */
            xPos = this.frame.FRAME_OFFSET + rand.nextInt(UPPER_BOUND)*MAX_POSITION;
            yPos = this.frame.FRAME_OFFSET + rand.nextInt(UPPER_BOUND)*MAX_POSITION;
            /* Check is new position is valid */
            posValid = checkPositionValid(xPos, yPos);
        } while(posValid != true);


    }

    private boolean checkPositionValid(int x, int y)
    {
        boolean valid = true;
        /* Loop to check if the new position is equal to one slot */
        for(int posIndex = 0; posIndex < this.snake.length; posIndex++)
        {
            if((this.snake.pos[posIndex][0] == x) && (this.snake.pos[posIndex][1] == y))
            {
                /* Position not valid */
                valid = false;
                break;
            }
        }
        return valid;
    }
}
