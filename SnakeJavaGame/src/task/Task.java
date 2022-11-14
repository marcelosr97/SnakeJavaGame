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
        this.currentTask = TypeTask.START;
        this.paint = paint;
        this.snake = snake;
        this.food = food;
        this.frame = frame;
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
        if(this.frame.currentFrame == Frame.FrameType.GAME)
        {
            this.frame.configureGameFrame();
            this.paint.setWindows(Paint.TypeWindow.GAME);
            this.currentTask = GAME;
        }
    }

    private void processGameTask()
    {
        /* Set direction */
        this.snake.setDirection(this.frame.snakeDir);
        /* Check snake collision */
        checkSnakeCollision();
        /* Check whether food was eaten */
        checkFoodEaten();
        /* Update snake slots */
        this.snake.updateSlots();
        /* Repaint */
        this.paint.repaint();
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
