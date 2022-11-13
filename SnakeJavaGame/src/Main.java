import entities.Snake;
import entities.Food;
import panel.KeyListenerImpl;
import panel.Paint;
import panel.Frame;
import task.Task;

import java.awt.*;

public class Main
{
    public static void main(String[] args) {
        /* Create frame object */
        Frame frame = new Frame();
        /* Create snake object */
        Snake snake = new Snake(frame);
        /* Create food object */
        Food food = new Food(frame, snake);
        /* Create paint object */
        Paint paint = new Paint(snake, food, frame);
        /* Key listener */
        KeyListenerImpl keyListener = new KeyListenerImpl(paint, snake);
        /* Configure frame */
        frame.configureFrame(paint, keyListener);
        /* Create task object */
        Task task = new Task(paint, snake, food);
        /* Start task */
        task.start();

    }
}
