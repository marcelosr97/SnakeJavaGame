package entities;

/* Imports */
import panel.Frame;
import java.awt.event.KeyEvent;

public class Snake
{
    /* Public constants */
    public static final int SNAKE_WIDTH = 32;
    public static final int SNAKE_HEIGHT = 32;
    /* Private constants */
    private static final int MAX_SNAKE_LENGTH = 1024;
    private static final int MIN_SNAKE_LENGTH = 3;
    /* Public variables */
    public int length = MIN_SNAKE_LENGTH;
    public int[][] pos = new int[MAX_SNAKE_LENGTH][2];
    public int direction = KeyEvent.VK_UP;
    public Snake()
    {
        /* Initialize first slot position */
        pos[0][0] = Frame.FRAME_OFFSET+128;
        pos[0][1] = Frame.FRAME_OFFSET+128;
        /* Initialize second slot position */
        pos[1][0] = Frame.FRAME_OFFSET+144;
        pos[1][1] = Frame.FRAME_OFFSET+128;
        /* Initialize second slot position */
        pos[2][0] = Frame.FRAME_OFFSET+160;
        pos[2][1] = Frame.FRAME_OFFSET+128;
    }

    public void setDirection(int direction)
    {
       this.direction = direction;
    }

    public void updateSlots()
    {
        /* Update slots backwards */
        updateSlotsBackwards();

        /* Update first slot */
        updateFirstSlot();

        /* Check boundaries */
        checkBoundaries();
    }

    private void updateSlotsBackwards()
    {
        for(int posIndex = length - 1; posIndex > 0; posIndex--)
        {
            pos[posIndex][0] = pos[posIndex-1][0];
            pos[posIndex][1] = pos[posIndex-1][1];
        }
    }

    private void updateFirstSlot()
    {
        switch (this.direction) {
            case KeyEvent.VK_UP:
                this.pos[0][1] -= SNAKE_HEIGHT;
                break;
            case KeyEvent.VK_RIGHT:
                this.pos[0][0] += SNAKE_WIDTH;
                break;
            case KeyEvent.VK_LEFT:
                this.pos[0][0] -= SNAKE_WIDTH;
                break;
            case KeyEvent.VK_DOWN:
                this.pos[0][1] += SNAKE_HEIGHT;
                break;
            default:
                /* Do nothing */
        }
    }

    private void checkBoundaries()
    {
        /* Check for X boundaries */
        if(this.pos[0][0] >= Frame.MAX_X + Frame.FRAME_OFFSET)
        {
            this.pos[0][0] = Frame.FRAME_OFFSET;
        }
        else if (this.pos[0][0] < Frame.FRAME_OFFSET)
        {
            this.pos[0][0] = Frame.MAX_X + Frame.FRAME_OFFSET - SNAKE_HEIGHT;
        }

        /* Check for Y boundaries */
        if(this.pos[0][1] >= Frame.MAX_Y + Frame.FRAME_OFFSET)
        {
            this.pos[0][1] = Frame.FRAME_OFFSET;
        }
        else if (this.pos[0][1] < Frame.FRAME_OFFSET)
        {
            this.pos[0][1] = Frame.MAX_Y + Frame.FRAME_OFFSET - SNAKE_HEIGHT;
        }
    }

    public void checkSnakeCollision()
    {
        /* Snake Position */
        int snakeXPos = this.pos[0][0];
        int snakeYPos = this.pos[0][1];

        for(int posIndex = 3; posIndex < this.length; posIndex++)
        {
            /* Check if the position is the same */
            if((snakeXPos == this.pos[posIndex][0]) && (snakeYPos == this.pos[posIndex][1]))
            {
                System.out.println("GAME OVER");
                System.exit(0);
            }
        }
    }

    public boolean checkFoodEaten(Food food)
    {
        boolean ret = false;
        /* Snake Position */
        int snakeXPos = this.pos[0][0];
        int snakeYPos = this.pos[0][1];

        /* Food Position */
        int foodXPos = food.xPos;
        int foodYPos = food.yPos;

        /* Check if the position is the same */
        if ((snakeXPos == foodXPos) && (snakeYPos == foodYPos))
        {
            /* Increase snake length */
            this.length++;
            ret = true;
        }
        return ret;
    }
}
