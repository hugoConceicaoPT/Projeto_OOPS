package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import GameLayer.SnakeLayer.Direction;
import GameLayer.SnakeLayer.Quadrado;
import GameLayer.SnakeLayer.Snake;

public class SnakeTest {
    @Test
    public void increaseSizeTest() {
        String input1 = "1 1 1 3 3 3 3 1";
        String input2 = "3 3 3 1 5 1 5 3";
        String input3 = "5 3 5 1 7 1 7 3";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        quadrado.add(new Quadrado(input2));
        quadrado.add(new Quadrado(input3));
        Snake snake = new Snake(quadrado);
        snake.increaseSize();
        assertEquals(4,snake.getListaQuadrados().size());
        snake.increaseSize();
        assertEquals(5, snake.getListaQuadrados().size());
        snake.increaseSize();
        snake.increaseSize();
        snake.increaseSize();
        assertEquals(8, snake.getListaQuadrados().size());     
    }

    @Test   
    public void collidedWithHerselfTest(){
        String input1 = "1 1 1 3 3 3 3 1";
        String input2 = "3 3 3 1 5 1 5 3";
        String input3 = "5 3 5 1 7 1 7 3";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        quadrado.add(new Quadrado(input2));
        quadrado.add(new Quadrado(input3));
        Snake snake = new Snake(quadrado);
        assertTrue(snake.collidedWithHerself());
        assertFalse(snake.collidedWithHerself());
    }

    @Test
    public void moveTest() {
        String input1 = "6 4 6 2 8 2 8 4";
        String input2 = "4 4 6 4 6 2 4 2";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        quadrado.add(new Quadrado(input2));
        Snake snake = new Snake(quadrado);
        Direction direction = Direction.UP;
        snake.move(direction);
        assertEquals(Direction.UP, snake.toString());
        snake.move(Direction.DOWN);
        assertEquals(Direction.DOWN, snake.getDirection());
    }
}
