package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;

/**
 * Classe que representa um obstáculo no jogo da cobra
 * Responsabilidade: Gerenciar a forma geométrica do obstáculo e sua dinâmica
 * @version 1.0 22/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 */
public class Obstacle {

    private Poligono poligono;
    private ObstacleMovement obstacleMovement;
    private Ponto<? extends Number> rotacionPoint;
    private int obstacleAngle;

    /** 
     * Construtor para criar um obstáculo com características especificadas
     * @param poligono Figura geométrica do obstáculo
     * @param rotacionPoint Ponto de rotação para transformações geométricas
     * @param isDynamic Indica se o obstáculo possui movimento dinâmico
     * @param obstacleAngle ângulo do obstáculo
     */
    public Obstacle(Poligono poligono, Ponto<? extends Number> rotacionPoint, int obstacleAngle ,boolean isDynamic) {
        this.poligono = poligono;
        if(isDynamic)
            this.obstacleMovement = new DynamicMovement();
        else
            this.obstacleMovement = new StaticMovement();
        if(rotacionPoint == null)
            this.rotacionPoint = poligono.getCentroide();
        else
            this.rotacionPoint = rotacionPoint;
        this.obstacleAngle = obstacleAngle;
    }

    /** Verifica se o obstáculo intersecta a cabeça da cobra
     * @param snake Cobra do jogo
     * @return true se houver intersecção, false caso contrário
     */
    public boolean obstacleIntersect(Snake snake) {
        return snake.getHead().interseta(this.poligono);
    }

    /** Verifica se a cabeça da cobra está contida dentro do polígono do obstáculo
     * @param snake Cobra do jogo
     * @return true se a cabeça está contida, false caso contrário
     */
    public boolean obstacleContained(Snake snake) {
        return this.poligono.contida(snake.getHead());
    }

    /** Verifica se um ponto específico intersecta o obstáculo
     * @param point Ponto para verificar a intersecção
     * @return true se o ponto intersecta o obstáculo, false caso contrário
     */
    public boolean obstacleIntersect(Ponto<? extends Number> point) {
        double x = point.getX().doubleValue();
        double y = point.getY().doubleValue();
        return (x >= this.poligono.getMinX() && x <= this.poligono.getMaxX() &&
            y >= this.poligono.getMinY() && y <= this.poligono.getMaxY());
    }

    /** 
     * Rotaciona o obstáculo em torno do ponto de rotação
     */
    public void rotateObstacle() {
        this.obstacleMovement.rotateObstacle(this.poligono,this.rotacionPoint, this.obstacleAngle);
    }

    /**
     * Obtém o polígono que representa o obstáculo
     * @return o polígono
     */
    public Poligono getPoligono() {
        return poligono;
    }

    /**
     * Atualiza o polígono que representa o obstáculos
     * @param poligono o novo polígono
     */
    public void setPoligono(Poligono poligono) {
        this.poligono = poligono;
    }

    @Override
    public String toString() {
        return "Obstáculo: " + poligono.toString();
    }

    /**
     * Obtém o tipo de movimento do obstáculo
     * @return o tipo de movimento do obstáculo
     */
    public ObstacleMovement getObstacleMovement() {
        return obstacleMovement;
    }

    /**
     * Atualiza o tipo de movimento do obstáculo
     * @param obstacleMovement o novo tipo de movimento do obstáculo
     */
    public void setObstacleMovement(ObstacleMovement obstacleMovement) {
        this.obstacleMovement = obstacleMovement;
    }

    /**
     * Obtém o ponto de rotação do obstáculo
     * @return o ponto de rotação do obstáculo
     */
    public Ponto<? extends Number> getRotacionPoint() {
        return rotacionPoint;
    }

    /**
     * Atualiza o ponto de rotação do obstáculo
     * @param rotacionPoint o novo ponto de rotação do obstáculo
     */
    public void setRotacionPoint(Ponto<? extends Number> rotacionPoint) {
        this.rotacionPoint = rotacionPoint;
    }

    /**
     * Obtém o ângulo do obstáculo
     * @return o ângulo do obstáculo
     */
    public int getObstacleAngle() {
        return obstacleAngle;
    }

    /**
     * Atualiza o ângulo do obstáculo
     * @param obstacleAngle o novo ângulo do obstáculo
     */
    public void setObstacleAngle(int obstacleAngle) {
        this.obstacleAngle = obstacleAngle;
    }
}
