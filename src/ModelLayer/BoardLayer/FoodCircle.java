package ModelLayer.BoardLayer;


import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;

public class FoodCircle extends Food {

    private Circunferencia circunferencia;

    public FoodCircle(Circunferencia circunferencia) {
        super();
        this.circunferencia = circunferencia;
    }

    @Override
    public boolean foodContainedInSnakeHead(Snake snake){
        if(circunferencia.contidaNoPoligono(snake.getHead()))
            return true;
        return false;
    }

    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle){
        if (circunferencia.interseta(obstacle.getPoligono())) {
            return true;
        }     
        return false;
    }

    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        if(circunferencia.contidaNoPoligono(obstacle.getPoligono()) || obstacle.getPoligono().contidaNaCircunferencia(circunferencia))
            return true;
        return false;
    }

    @Override
    public boolean foodIntersectSnake(Snake snake) {
        for(int i = 0; i < snake.getBody().size(); i++) {
            if(circunferencia.interseta(snake.getBody().get(i)))
                return true;
        }
        return false;
    }

    @Override
    public int getMaxX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() + this.circunferencia.getRaio());
    }

    @Override
    public int getMinX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() - this.circunferencia.getRaio());
    }

    @Override
    public int getMaxY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() + this.circunferencia.getRaio());
    }

    @Override
    public int getMinY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() - this.circunferencia.getRaio());
    }
    

    public Circunferencia getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(Circunferencia circunferencia) {
        this.circunferencia = circunferencia;
    }

    @Override
    public Ponto<? extends Number> getCentroide() {
        return this.circunferencia.getCentro();
    }

    @Override
    public String toString() {
        return "Comida com: " + circunferencia.toString();
    } 
}
