package panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerImpl implements KeyListener {
    private Snake snake;
    public KeyListenerImpl(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.snake.moveSnake(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}