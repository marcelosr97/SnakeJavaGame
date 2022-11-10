package panel;

import java.awt.event.KeyEvent;

public class UpdatePosition extends Thread
{
    private Snake snake;
    private int nextX = 0;
    private int nextY = 0;
    public UpdatePosition(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void run()
    {
        while(true)
        {
            /* Update snake slots */
            this.snake.updateSlots();
            /* Repaint snake */
            this.snake.repaint();
            /* Sleep 50 ms */
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
