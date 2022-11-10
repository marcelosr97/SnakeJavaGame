package panel;

import java.awt.event.KeyEvent;

public class UpdatePosition extends Thread
{
    private Snake snake;

    public UpdatePosition(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void run() {
        while(true)
        {
            switch (this.snake.direction) {
                case KeyEvent.VK_UP:
                    this.snake.y -= 32;
                    break;
                case KeyEvent.VK_RIGHT:
                    this.snake.x += 32;
                    break;
                case KeyEvent.VK_LEFT:
                    this.snake.x -= 32;
                    break;
                case KeyEvent.VK_DOWN:
                    this.snake.y += 32;
                    break;
                default:
                    /* Do nothing */
            }
            this.snake.repaint();
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
