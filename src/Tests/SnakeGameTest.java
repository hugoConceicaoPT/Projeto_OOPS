package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;

import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

/** Classe que representa uma classe teste para testar as funcionalidades da classe SnakeGame
    Responsabilidade: Testar as funcionalidades da classe SnakeGame
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class SnakeGameTest {
    @Test
    public void moveSnakeTest() throws CloneNotSupportedException {
        long seed = 0;
        List<Integer> angle = new ArrayList<>();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        angle.add(0);
        angle.add(0);
        SnakeGame snakeGame = new SnakeGame("Player",20, 10, 2, true, "completa", 1, "quadrados", 5, 2, rotacionPoint, angle,true, "textual", seed);
        System.out.println(snakeGame.getSnake().toString());
        snakeGame.moveSnake(Direction.UP);
        snakeGame.getRasterizationStrategy().updateSnakeCells();
        assertEquals("Cabeça: [(6.0,8.0), (8.0,8.0), (8.0,10.0), (6.0,10.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[8][6].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[8][7].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[9][6].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[9][7].getCellType());
        snakeGame.moveSnake(Direction.RIGHT);
        snakeGame.getRasterizationStrategy().updateSnakeCells();
        assertEquals("Cabeça: [(8.0,10.0), (8.0,8.0), (10.0,8.0), (10.0,10.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[8][8].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[8][9].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[9][8].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getRasterizationStrategy().getBoard()[9][9].getCellType());
    }

    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 1;
        List<Integer> angle = new ArrayList<>();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        SnakeGame game = new SnakeGame("Player",100, 100, 10, true, "contorno", 5, "quadrados", 100, 5, rotacionPoint, angle ,true, "textual", seed);
        System.out.println(game.getSnake().toString());
        FoodSquare foodSquare = new FoodSquare(new Quadrado("16 30 22 30 22 36 16 36"));
        game.getGameBoard().setFood(foodSquare);
        game.foodContainedInSnakeHead(); 
        assertEquals(100, game.getScore().getPoints());
        assertEquals(2, game.getSnake().getBody().size());
    }

    @Test
    public void snakeCollidedTest(){
        long seed = 1;
        List<Integer> angle = new ArrayList<>();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        SnakeGame game = new SnakeGame("Player",100, 100, 10, true, "contorno", 5, "quadrados", 100, 5, rotacionPoint, angle ,true, "textual", seed);
        assertEquals(game.getObstaclesQuantity(), 5);
        assertFalse(game.snakeCollided());
    }

}
