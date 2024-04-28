package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class GameBoardTest {

    @Test
    public void testConstrutor1() {
        long seed = 125;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        assertThrows(IllegalArgumentException.class, () -> {
            new GameBoard(snake, -1, 100,FoodType.CIRCLE,1,1,new Ponto(1,1),false,false,new Random(seed));
        });
    }

    @Test
    public void testConstrutor2() {
        long seed = 125;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        assertThrows(IllegalArgumentException.class, () -> {
            new GameBoard(snake, 100, -1,FoodType.CIRCLE,1,1,new Ponto(1,1),false,false,new Random(seed));
        });
    }


    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 125;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,1,1,new Ponto(1,1),false,false,new Random(seed));
        FoodCircle foodCircle = new FoodCircle(new Circunferencia(new Ponto(7,4), 0.5));
        gameBoard.setFood(foodCircle);
        snake.move(Direction.RIGHT);
        assertTrue(gameBoard.foodContainedInSnake());
        assertEquals(4,gameBoard.getSnake().getBody().size());
        snake.move(Direction.UP);
        assertFalse(gameBoard.foodContainedInSnake());
    }

    @Test
    public void generateObstacleTest() throws CloneNotSupportedException {
        long seed = 124;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,1,1,new Ponto(1,1),false,false,new Random(seed));  
        assertNotNull(gameBoard.getListOfObstacles());
        assertTrue(gameBoard.getListOfObstacles().size() > 0);
        assertFalse(gameBoard.snakeIntersectsObstacle());
        assertFalse(gameBoard.obstacleContainedInSnake());
        assertFalse(gameBoard.foodIntersectObstacle());
    }

    @Test
    public void generateFoodTest() throws CloneNotSupportedException{
        long seed = 123;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,1,1,new Ponto(1,1),false,false,new Random(seed));
        assertNotNull(gameBoard.getFood());
        assertFalse(gameBoard.foodContainedInSnake());
        assertFalse(gameBoard.foodIntersectObstacle());
    }

    @Test
    public void removeFoodTest() throws CloneNotSupportedException {
        long seed = 121;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,1,1,new Ponto(1,1),false,false,new Random(seed));
        assertNotNull(gameBoard.getFood());
        gameBoard.removeFood();
        assertNull(gameBoard.getFood());
    }

    @Test
    public void snakeLeftBoardTest() throws CloneNotSupportedException{
        long seed = 121;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,1,1,new Ponto(1,1),false,false,new Random(seed));
        assertFalse(gameBoard.snakeLeftBoard());
        String input2 = "-1 0 -1 2 1 2 1 0";
        LinkedList<Quadrado> listaQuadrados2 = new LinkedList<>();
        listaQuadrados2.add(new Quadrado(input2));
        gameBoard.setSnake(new Snake(listaQuadrados2, true));
        assertTrue(gameBoard.snakeLeftBoard());
    }

    @Test
    public void obstacleContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 120;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,1,1,new Ponto(1,1),false,false,new Random(seed));
        assertFalse(gameBoard.obstacleContainedInSnake());
        Obstacle obstacle = new Obstacle(new Quadrado("8 5 8 4 7 4 7 5"), new Ponto(1,1) ,false,false);
        gameBoard.getListOfObstacles().add(obstacle);
        assertTrue(gameBoard.obstacleContainedInSnake());
    }

    @Test
    public void snakeIntersectsObstacleTest() throws CloneNotSupportedException {
        long seed = 119;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,1,1,new Ponto(1,1),false,false,new Random(seed));
        assertFalse(gameBoard.snakeIntersectsObstacle());
        Obstacle obstacle = new Obstacle(new Quadrado("5 6 5 4 7 4 7 6"),new Ponto(1,1),false ,false);
        gameBoard.getListOfObstacles().add(obstacle);
        assertTrue(gameBoard.snakeIntersectsObstacle());
    }
}
