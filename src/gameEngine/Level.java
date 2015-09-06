package gameEngine;

import graphicEngine.ShaderManager;
import networking.Input;

/**
 *
 * @author Notechus
 */
public class Level {

    private Paddle player1;
    private Paddle player2;

    private Ball ball1;

    private ShaderManager shaderManager;

    public Level() {
        shaderManager = new ShaderManager();
        shaderManager.loadAll();
        this.ball1 = new Ball();
        this.player1 = new Paddle();
        this.player2 = new Paddle();

        //set player pos
        player1.getPosition().setX(-0.5f);
        player1.getPosition().setY(0.0f);

        player2.getPosition().setX(0.5f);
        player2.getPosition().setY(0.0f);
        //set ball pos
        ball1.getPosition().setX(0.0f);
        ball1.getPosition().setY(0.0f);
    }

    public void checkCollision(Paddle paddle) {
        if (paddle.getPosition().getX() < ball1.getPosition().getX() + ball1.WIDTH
                && paddle.getPosition().getX() + paddle.WIDTH > ball1.getPosition().getX()
                && paddle.getPosition().getY() < ball1.getPosition().getY() + ball1.HEIGHT
                && paddle.getPosition().getY() + paddle.HEIGHT > ball1.getPosition().getY()) {
            System.out.println("Collision detected!");
            ball1.getMovement().setX(ball1.getMovement().getX() * (-1.0f));
        }
    }

    public void update() {
        checkCollision(player1);
        checkCollision(player2);
        ball1.update();
        player1.update();
        player2.update();
    }

    public void draw() {
        shaderManager.getShader1().start();
        shaderManager.getShader1().setUniform3f("pos", ball1.getPosition());
        ball1.draw();
        shaderManager.getShader1().stop();

        shaderManager.getShader1().start();
        shaderManager.getShader1().setUniform3f("pos", player1.getPosition());
        player1.draw();
        shaderManager.getShader1().stop();

        shaderManager.getShader1().start();
        shaderManager.getShader1().setUniform3f("pos", player2.getPosition());
        player2.draw();
        shaderManager.getShader1().stop();
    }

}
