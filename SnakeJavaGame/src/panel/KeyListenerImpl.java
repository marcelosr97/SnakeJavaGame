package panel;

import entities.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerImpl implements KeyListener {
    private Snake snake;
    public KeyListenerImpl(Paint paint, Snake snake)
    {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        this.snake.setDirection(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}