package task;

import panel.Paint;
import entities.Snake;
import entities.Food;

public class Task extends Thread
{
    private static final int REFRESH_RATE = 15;
    private Paint paint;
    private Food food;
    private Snake snake;
    public Task(Paint paint, Snake snake, Food food)
    {
        this.paint = paint;
        this.snake = snake;
        this.food = food;
    }

    @Override
    public void run()
    {
        while(true)
        {
            /* Check snake collision */
            checkSnakeCollision();
            /* Check whether food was eaten */
            checkFoodEaten();
            /* Update snake slots */
            this.snake.updateSlots();
            /* Repaint */
            this.paint.repaint();
            /* Sleep */
            try {
                Thread.currentThread().sleep(1000/REFRESH_RATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void checkFoodEaten()
    {
        /* Snake Position */
        int snakeXPos = this.snake.pos[0][0];
        int snakeYPos = this.snake.pos[0][1];

        /* Food Position */
        int foodXPos = this.food.xPos;
        int foodYPos = this.food.yPos;

        /* Check if the position is the same */
        if((snakeXPos == foodXPos) && (snakeYPos == foodYPos))
        {
            /* Increase snake length */
            this.snake.length++;
            /* Generate new food position */
            this.food.generatePosition();
        }
    }

    private void checkSnakeCollision()
    {
        /* Snake Position */
        int snakeXPos = this.snake.pos[0][0];
        int snakeYPos = this.snake.pos[0][1];

        for(int posIndex = 3; posIndex < this.snake.length; posIndex++)
        {
            /* Check if the position is the same */
            if((snakeXPos == this.snake.pos[posIndex][0]) && (snakeYPos == snake.pos[posIndex][1]))
            {
                /* Increase snake length */
                System.out.println("GAME OVER");
                System.exit(0);
            }
        }
    }
}
