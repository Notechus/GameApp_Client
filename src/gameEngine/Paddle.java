package gameEngine;

import graphicEngine.VertexArrayObject;
import input.KeyboardInput;
import math.Vector3f;
import networking.Input;
import static org.lwjgl.glfw.GLFW.*;

/**
 *
 * @author Notechus
 */
public class Paddle extends GameObject {

    private VertexArrayObject vao;

    private Vector3f position;

    public float WIDTH = 0.05f;
    public float HEIGHT = 0.25f;

    //client-side
    private Input input;

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

    public Paddle() {
        this.setCount(indices.length);
        this.position = new Vector3f();
        vao = new VertexArrayObject(this.vertices, this.indices);
        this.setVaoID(vao.getVaoID());
        this.input = new Input();
    }

    public boolean checkBounds() {
        //ball rebounds from walls
        if (position.getY() <= -1.0f) {
            System.out.println("Paddle below game window!");
            return true;
        }
        if (position.getY() + HEIGHT >= 1.0f) {
            System.out.println("Paddle above game window!");
            return true;
        }
        if (position.getX() + WIDTH >= 1.0f) {
            System.out.println("Paddle too far right of game window!");
            return true;
        }
        if (position.getX() <= -1.0f) {
            System.out.println("Paddle too far left of game window!");
            return true;
        }

        return false;
    }

    public void update() {

        if (KeyboardInput.isKeyDown(GLFW_KEY_W) && position.getY() <= 0.735f) {
            try {
                //if (checkBounds()) {
                position.setY(position.getY() + 0.01f);
                input.setUp(true);
                //}
            } finally {
                input.setUp(false);
            }
        }

        if (KeyboardInput.isKeyDown(GLFW_KEY_S) && position.getY() >= -0.99f) {
            try {
                //if (checkBounds()) {
                position.setY(position.getY() - 0.01f);
                input.setDown(true);
                //}
            } finally {
                input.setDown(false);
            }
        }

        if (KeyboardInput.isKeyDown(GLFW_KEY_D) && position.getX() <= 0.94f) {
            try {
                //if (checkBounds()) {
                position.setX(position.getX() + 0.01f);
                input.setRight(true);
                // }
            } finally {
                input.setRight(false);
            }
        }

        if (KeyboardInput.isKeyDown(GLFW_KEY_A) && position.getX() >= -0.99f) {
            try {
                //if (checkBounds()) {
                position.setX(position.getX() - 0.01f);
                input.setLeft(true);
                // }
            } finally {
                input.setLeft(false);
            }
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}
