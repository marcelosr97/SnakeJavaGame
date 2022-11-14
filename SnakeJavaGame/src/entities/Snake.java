package entities;

/* Imports */
import panel.Frame;
import java.awt.event.KeyEvent;

public class Snake
{
    /* Maximum snake length */
    private static final int MAX_SNAKE_LENGTH = 1024;
    /* Minimum snake length */
    private static final int MIN_SNAKE_LENGTH = 3;
    /* Public variables */
    public final int SNAKE_WIDTH = 32;
    public final int SNAKE_HEIGHT = 32;
    public int length = MIN_SNAKE_LENGTH;
    public int[][] pos = new int[MAX_SNAKE_LENGTH][2];
    public int direction = KeyEvent.VK_UP;
    /* Private objects */
    private Frame frame;
    public Snake(Frame frame)
    {
        this.frame = frame;
        /* Initialize first slot position */
        pos[0][0] = this.frame.FRAME_OFFSET+128;
        pos[0][1] = this.frame.FRAME_OFFSET+128;
        /* Initialize second slot position */
        pos[1][0] = this.frame.FRAME_OFFSET+144;
        pos[1][1] = this.frame.FRAME_OFFSET+128;
        /* Initialize second slot position */
        pos[2][0] = this.frame.FRAME_OFFSET+160;
        pos[2][1] = this.frame.FRAME_OFFSET+128;
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
        if(this.pos[0][0] >= this.frame.MAX_X + this.frame.FRAME_OFFSET)
        {
            this.pos[0][0] = this.frame.FRAME_OFFSET;
        }
        else if (this.pos[0][0] < this.frame.FRAME_OFFSET)
        {
            this.pos[0][0] = this.frame.MAX_X + this.frame.FRAME_OFFSET - SNAKE_HEIGHT;
        }

        /* Check for Y boundaries */
        if(this.pos[0][1] >= this.frame.MAX_Y + this.frame.FRAME_OFFSET)
        {
            this.pos[0][1] = this.frame.FRAME_OFFSET;
        }
        else if (this.pos[0][1] < this.frame.FRAME_OFFSET)
        {
            this.pos[0][1] = this.frame.MAX_Y + this.frame.FRAME_OFFSET - SNAKE_HEIGHT;
        }
    }

}
