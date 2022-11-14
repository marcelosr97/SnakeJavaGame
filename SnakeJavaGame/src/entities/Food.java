package entities;

import panel.Frame;

import java.util.Random;

public class Food
{
    /* Private constants */
    private static final int UPPER_BOUND = 16;
    private static final int MAX_POSITION = 32;
    /* Public constants */
    public static final int FOOD_WIDTH = 32;
    public static final int FOOD_HEIGHT = 32;
    /* Public variables */
    public int xPos;
    public int yPos;
    /* Private variables */
    private Random rand = new Random();

    public void generatePosition(int[][] snakePos, int snakeLength)
    {
        /* Create flag to check if the position is valid */
        boolean posValid = false;
        do
        {
            /* Generate random position */
            xPos = Frame.FRAME_OFFSET + rand.nextInt(UPPER_BOUND)*MAX_POSITION;
            yPos = Frame.FRAME_OFFSET + rand.nextInt(UPPER_BOUND)*MAX_POSITION;
            /* Check is new position is valid */
            posValid = checkPositionValid(xPos, yPos, snakePos, snakeLength);
        } while(posValid != true);


    }

    private boolean checkPositionValid(int xPos, int yPos, int[][] snakePos, int snakeLength)
    {
        boolean valid = true;
        /* Loop to check if the new position is equal to one slot */
        for(int posIndex = 0; posIndex < snakeLength; posIndex++)
        {
            if((snakePos[posIndex][0] == xPos) && (snakePos[posIndex][1] == yPos))
            {
                /* Position not valid */
                valid = false;
                break;
            }
        }
        return valid;
    }
}
