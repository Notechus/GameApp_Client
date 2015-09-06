package gameEngine;

import graphicEngine.VertexArrayObject;
import input.MouseInput;
import math.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

/**
 *
 * @author Notechus
 */
public class Ball extends GameObject {

    public float WIDTH = 0.05f;
    public float HEIGHT = 0.05f;

    private VertexArrayObject vao;

    private Vector3f position;

    private Vector3f movement;

    private float[] vertices = {
        0.0f, HEIGHT, 0f,
        0.0f, 0.0f, 0f,
        WIDTH, 0.0f, 0f,
        WIDTH, HEIGHT, 0f
    };

    private byte[] indices = new byte[]{
        0, 1, 2,
        2, 3, 0
    };

    public Ball() {
        this.setCount(indices.length);
        this.position = new Vector3f();
        vao = new VertexArrayObject(this.vertices, this.indices);
        this.setVaoID(vao.getVaoID());

        //this.movement = new Vector3f(0.02f, 0.015f, 0.0f);
        this.movement = new Vector3f(0.011f, 0.011f, 0.0f);
    }

    public VertexArrayObject getVao() {
        return vao;
    }

    public void setVao(VertexArrayObject vao) {
        this.vao = vao;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getMovement() {
        return movement;
    }

    public void setMovement(Vector3f movement) {
        this.movement = movement;
    }

    public boolean checkBounds() {
        //ball rebounds from walls
        if (position.getY() <= -1.0f) {
            System.out.println("Below game window!");
            position.setY(-0.99f);
            movement.setY(movement.getY() * (-1.0f));
            return true;
        }
        if (position.getY() + HEIGHT >= 1.0f) {
            System.out.println("Above game window!");
            position.setY(0.99f - HEIGHT);
            movement.setY(movement.getY() * (-1.0f));
            return true;
        }
        if (position.getX() + WIDTH >= 1.0f) {
            System.out.println("Too far right of game window!");
            position.setX(0.99f - WIDTH);
            movement.setX(movement.getX() * (-1.0f));
            return true;
        }
        if (position.getX() <= -1.0f) {
            System.out.println("Too far left of game window!");
            position.setX(-0.99f);
            movement.setX(movement.getX() * (-1.0f));
            return true;
        }

        return false;
    }

    public void update() {
        if (!checkBounds()) {
            this.position.translate(movement);
        }
    }

}
