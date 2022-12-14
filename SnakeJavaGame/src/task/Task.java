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
    public enum TypeTask
    {START, GAME, END}

    ;

    public Task(Paint paint, Snake snake, Food food, Frame frame)
    {
        /* Get the objects */
        this.paint = paint;
        this.snake = snake;
        this.food = food;
        this.frame = frame;
        /* Set initial task */
        currentTask = TypeTask.START;
        /* Generate position for food */
        this.food.generatePosition(this.snake);
        /* Update positions in paint */
        this.paint.updatePositions(this.snake, this.food);
    }

    @Override
    public void run()
    {
        while (true)
        {
            switch (currentTask)
            {
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
            taskSleep(1000 / REFRESH_RATE);
        }
    }

    private void processStartTask()
    {
        /* Wait for the Start Button to be pressed */
        if (frame.currentFrame == Frame.FrameType.GAME)
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
        snake.checkSnakeCollision();
        if(snake.checkFoodEaten(food))
        {
            food.generatePosition(snake);
        }
        snake.updateSlots();
        paint.updatePositions(snake, food);
        paint.repaint();
    }

    private void processEndTask()
    {
    }

    private void taskSleep(int time)
    {
        try
        {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
