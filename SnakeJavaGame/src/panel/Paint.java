package panel;

import entities.Food;
import entities.Snake;

import javax.swing.JComponent;
import java.awt.*;

public class Paint extends JComponent
{
    private Frame frame;
    private Snake snake;
    private Food food;
    public Paint(Snake snake, Food food, Frame frame)
    {
        this.frame = frame;
        this.food = food;
        this.snake = snake;
    }

    public void paintComponent(Graphics g)
    {
        /* Draw border */
        /* Set color white */
        g.setColor(Color.WHITE);
        /* Draw border */
        g.drawRect(this.frame.FRAME_OFFSET,this.frame.FRAME_OFFSET,this.frame.MAX_X, this.frame.MAX_X);
        /* Set color green */
        g.setColor(Color.GREEN);
        /* Print snake */
        for(int posIndex = 0; posIndex < this.snake.length; posIndex++)
        {
            g.fillRect(this.snake.pos[posIndex][0], this.snake.pos[posIndex][1], this.snake.SNAKE_WIDTH,
                    this.snake.SNAKE_HEIGHT);
        }
        /* Set color red */
        g.setColor(Color.RED);
        /* Print food */
        g.fillRect(this.food.xPos, this.food.yPos, 16, 16);
    }
}
