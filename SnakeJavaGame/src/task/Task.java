package task;

import panel.Frame;
import panel.Paint;
import entities.Snake;
import entities.Food;

import static task.Task.TypeTask.GAME;

public class Task extends Thread
{
    /* Private variables */
    private static final int REFRESH_RATE = 15;
    private Frame frame;
    private Paint paint;
    private Food food;
    private Snake snake;
    private TypeTask currentTask;
    /* Public variables */
    public enum TypeTask{START, GAME, END};

    public Task(Paint paint, Snake snake, Food food, Frame frame)
    {
        /* Get the objects */
        this.paint = paint;
        this.snake = snake;
        this.food = food;
        this.frame = frame;
        /* Set initial task */
        currentTask = TypeTask.START;
        /* Generate position for the food */
        this.food.generatePosition(this.snake.pos, this.snake.length);
        /* Set Snake position */
        this.paint.setSnakePosition(snake.pos);
        /* Set Food position */
        this.paint.setFoodPosition(food.xPos, food.yPos);
        /* Set snake length */
        paint.setSnakeLength(snake.length);

    }

    @Override
    public void run()
    {
        while(true)
        {
            switch(currentTask) {
                case START:
                    processStartTask();
                    break;
                case GAME:
                    processGameTask();
                    break;
                case END:
                    processEndTask();
            }
            /* Sleep */
            taskSleep(1000/REFRESH_RATE);
        }
    }

    private void processStartTask()
    {
        /* Wait for the Start Button to be pressed */
        if(frame.currentFrame == Frame.FrameType.GAME)
        {
            frame.configureGameFrame();
            paint.setWindows(Paint.TypeWindow.GAME);
            currentTask = GAME;
        }
    }

    private void processGameTask()
    {
        /* Set direction */
        snake.setDirection(frame.snakeDir);
        /* Check snake collision */
        checkSnakeCollision();
        /* Check whether food was eaten */
        checkFoodEaten();
        /* Update snake slots */
        snake.updateSlots();
        /* Set Snake position */
        paint.setSnakePosition(snake.pos);
        /* Set Food position */
        paint.setFoodPosition(food.xPos, food.yPos);
        /* Repaint */
        paint.repaint();
    }

    private void processEndTask()
    {
    }

    private void taskSleep(int time)
    {
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkFoodEaten()
    {
        /* Snake Position */
        int snakeXPos = snake.pos[0][0];
        int snakeYPos = snake.pos[0][1];

        /* Food Position */
        int foodXPos = food.xPos;
        int foodYPos = food.yPos;

        /* Check if the position is the same */
        if((snakeXPos == foodXPos) && (snakeYPos == foodYPos))
        {
            /* Increase snake length */
            snake.length++;
            /* Send snake length */
            paint.setSnakeLength(snake.length);
            /* Generate new food position */
            food.generatePosition(snake.pos, snake.length);
        }
    }

    private void checkSnakeCollision()
    {
        /* Snake Position */
        int snakeXPos = snake.pos[0][0];
        int snakeYPos = snake.pos[0][1];

        for(int posIndex = 3; posIndex < snake.length; posIndex++)
        {
            /* Check if the position is the same */
            if((snakeXPos == snake.pos[posIndex][0]) && (snakeYPos == snake.pos[posIndex][1]))
            {
                /* Increase snake length */
                System.out.println("GAME OVER");
                System.exit(0);
            }
        }
    }
}
