package ViewLayer;

import java.awt.*;
import javax.swing.*;

import ControllerLayer.ML;
import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface gráfica para o jogo, utilizando a biblioteca Swing.
 * Responsabilidade: Exibir o tabuleiro do jogo e o placar, além de capturar os eventos do teclado.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class GraphicalUI extends JFrame implements UI {
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;
    private static int currentState;
    private Scene currentScene;
    private final int windowWidth;
    private final int windowHeight;
    private ML mouseListener;
    private JLabel statusLabel;
    private TextArea playText;

    /**
     * Construtor que inicializa a interface gráfica.
     * Pode ser configurado posteriormente para definir a aparência do jogo.
     * @param rasterizationGraphicStrategy A estratégia de rasterização gráfica a ser usada.
     */
    public GraphicalUI(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        this.windowWidth = this.rasterizationGraphicStrategy.getGameBoard().getWidthBoard() + 10 ;
        this.windowHeight = this.rasterizationGraphicStrategy.getGameBoard().getHeightBoard() + 60;
        currentState = 0;
        this.statusLabel = new JLabel("",SwingConstants.CENTER);
        this.statusLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        this.statusLabel.setForeground(Color.white);
        this.statusLabel.setOpaque(true);
        this.statusLabel.setBackground(new Color (20, 90, 50));
        this.statusLabel.setVisible(true);
        this.playText = new TextArea("Clique ENTER para começar a jogar");
        this.playText.setFont(new Font("MV Boli", Font.PLAIN, 15));
        this.playText.setForeground(Color.white);
        this.playText.setVisible(false);
        add(this.statusLabel,BorderLayout.NORTH);
        setTitle("SnakeOOPS");
        setPreferredSize(new Dimension(this.windowWidth, this.windowHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método para exibir o tabuleiro do jogo e o placar.
     * @param gameBoard O tabuleiro do jogo.
     * @param score A pontuação atual.
     */
    @Override
    public void display(GameBoard gameBoard, Score score) {
        if (currentState == 0) {
            if (SnakeGame.isIsRunning())
                dispose();
            currentScene = MenuScene.getInstance(this.windowWidth, this.windowHeight, this.rasterizationGraphicStrategy);
            Image dbImage = createImage(getWidth(), getHeight());
            Graphics dbg = dbImage.getGraphics();
            this.draw(dbg);
            getGraphics().drawImage(dbImage, 0, 0, this);
            this.mouseListener = (ML) getMouseListeners()[0];
            currentScene.update(this.mouseListener);
        } else if(currentState == 1) {
            if(SnakeGame.isIsRunning())
                this.playText.setVisible(false);
            this.playText.setVisible(true);
            currentScene = GameScene.getInstance(this.rasterizationGraphicStrategy);
            add(currentScene.getPanel(), BorderLayout.CENTER);
            int angle = 0;
            switch (gameBoard.getSnake().getCurrentDirection()) {
                case UP:
                    angle = 90;
                    break;
                case DOWN:
                    angle = 270;
                    break;
                case LEFT:
                    angle = 180;
                    break;
                case RIGHT:
                    angle = 0;
                    break;
                default:
                    break;
            }
            this.statusLabel.setText("DIR H: " + angle + " | " + "Pontos: " + score.getPoints());
            revalidate();
            repaint();
        }
        else {
            currentScene = GameOverScene.getInstance(this.windowWidth, this.windowHeight);
            JPanel gameOverPanel = currentScene.getPanel();
            this.getContentPane().removeAll();
            this.add(gameOverPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    /**
     * Obtém a estratégia de rasterização textual.
     * @return A estratégia de rasterização textual (sempre null).
     */
    @Override
    public RasterizationTextualStrategy getTextualRasterizationStrategy() {
        return null;
    }

    /**
     * Obtém a estratégia de rasterização gráfica.
     * @return A estratégia de rasterização gráfica atual.
     */
    @Override
    public RasterizationGraphicStrategy getGraphicRasterizationStrategy() {
        return this.rasterizationGraphicStrategy;
    }

    /**
     * Adiciona um listener para eventos de mouse.
     * @param l O listener de mouse a ser adicionado.
     */
    @Override
    public void addMouseListener(ML l) {
        this.mouseListener = l;
        super.addMouseListener(l);
    }

    /**
     * Adiciona um listener para eventos de movimento de mouse.
     * @param mouseListener O listener de movimento de mouse a ser adicionado.
     */
    @Override
    public void addMouseMotionListener(ML mouseListener) {
        this.mouseListener = mouseListener;
        super.addMouseMotionListener(mouseListener);
    }

    /**
     * Método para desenhar a cena atual.
     * @param g O contexto gráfico a ser usado para desenhar.
     */
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        currentScene.draw(g2);
    }

    /**
     * Fecha a janela do jogo.
     * Pode ser melhorado para limpar recursos ou exibir uma mensagem final antes de sair.
     */
    public void close() {
        currentState = 3;
        dispose();
    }

    /**
     * Obtém a estratégia de rasterização gráfica atual.
     * @return A estratégia de rasterização gráfica.
     */
    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    /**
     * Define uma nova estratégia de rasterização gráfica.
     * @param rasterizationGraphicStrategy A nova estratégia de rasterização gráfica.
     */
    public void setRasterizationStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    /**
     * Define uma nova estratégia de rasterização gráfica.
     * @param rasterizationGraphicStrategy A nova estratégia de rasterização gráfica.
     */
    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    /**
     * Obtém o estado atual do jogo.
     * @return O estado atual do jogo.
     */
    public static int getCurrentState() {
        return currentState;
    }

    /**
     * Define o estado atual do jogo.
     * @param currentState O novo estado do jogo.
     */
    public static void setCurrentState(int currentState) {
        GraphicalUI.currentState = currentState;
    }

    /**
     * Obtém a cena atual.
     * @return A cena atual.
     */
    public Scene getCurrentScene() {
        return currentScene;
    }

    /**
     * Define a cena atual.
     * @param currentScene A nova cena a ser definida.
     */
    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Obtém a largura da janela.
     * @return A largura da janela.
     */
    public int getWindowWidth() {
        return windowWidth;
    }

    /**
     * Obtém a altura da janela.
     * @return A altura da janela.
     */
    public int getWindowHeight() {
        return windowHeight;
    }

    /**
     * Obtém o listener de mouse.
     * @return O listener de mouse.
     */
    public ML getMouseListener() {
        return mouseListener;
    }

    /**
     * Define um novo listener de mouse.
     * @param mouseListener O novo listener de mouse a ser definido.
     */
    public void setMouseListener(ML mouseListener) {
        this.mouseListener = mouseListener;
    }

    /**
     * Obtém o JLabel que exibe o status atual.
     * @return O JLabel que exibe o status atual.
     */
    public JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * Define um novo JLabel para exibir a status.
     * @param statusLabel O novo JLabel para exibir a status.
     */
    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }
}
