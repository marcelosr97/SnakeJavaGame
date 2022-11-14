import entities.Snake;
import entities.Food;
import panel.Paint;
import panel.Frame;
import task.Task;

public class Main
{
    public static void main(String[] args) {
        /* Create frame object */
        Frame frame = new Frame();
        /* Create snake object */
        Snake snake = new Snake();
        /* Create food object */
        Food food = new Food();
        /* Create paint object */
        Paint paint = new Paint();
        /* Configure frame */
        frame.configureFrame(paint);
        /* Create task object */
        Task task = new Task(paint, snake, food, frame);
        /* Start task */
        task.start();

    }
}
