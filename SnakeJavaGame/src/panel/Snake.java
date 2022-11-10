package panel;

/* Imports */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Snake extends JComponent{
    /* Maximum snake length */
    private static final int MAX_SNAKE_LENGTH = 1024;
    /* Minimum snake length */
    private static final int MIN_SNAKE_LENGTH = 3;
    int snakeLength = MIN_SNAKE_LENGTH;
    int[][] snakePos = new int[MAX_SNAKE_LENGTH][2];
    int direction = KeyEvent.VK_UP;

    public Snake()
    {
        /* Initialize first slot position */
        snakePos[0][0] = 512;
        snakePos[0][1] = 512;
        /* Initialize second slot position */
        snakePos[1][0] = 544;
        snakePos[1][1] = 512;
        /* Initialize second slot position */
        snakePos[2][0] = 576;
        snakePos[2][1] = 512;
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        for(int posIndex = 0; posIndex < snakeLength; posIndex++)
        {
            g.fillRect(this.snakePos[posIndex][0], this.snakePos[posIndex][1], 32, 32);
        }
    }

    public void getDirection (KeyEvent e)
    {
        if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_RIGHT)
            || (e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_DOWN))
        {
            /* Check whether the following movement is transverse */
            if((this.direction != e.getKeyCode()+2) && (this.direction != e.getKeyCode()-2))
            {
                this.direction = e.getKeyCode();
            }
        }
        else
        {
            /* Do nothing */
        }
    }

    public void updateSlots()
    {
        /* Update slots backwards */
        for(int posIndex = snakeLength-1; posIndex > 0; posIndex--)
        {
            snakePos[posIndex][0] = snakePos[posIndex-1][0];
            snakePos[posIndex][1] = snakePos[posIndex-1][1];
        }
        /* Update first slot */
        switch (this.direction) {
            case KeyEvent.VK_UP:
                this.snakePos[0][1] -= 32;
                break;
            case KeyEvent.VK_RIGHT:
                this.snakePos[0][0] += 32;
                break;
            case KeyEvent.VK_LEFT:
                this.snakePos[0][0] -= 32;
                break;
            case KeyEvent.VK_DOWN:
                this.snakePos[0][1] += 32;
                break;
            default:
                /* Do nothing */
        }
        if(this.snakeLength <= 10) this.snakeLength++;
    }
}
