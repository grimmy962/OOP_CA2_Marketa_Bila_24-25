package org.example;

import java.util.Stack;

public class Question10 {

    private static final int WALL = 1;
    private static final int PATH = 0;
    private static final int VISITED = 2;
    private static final int EXIT = 3;

    private static final int[][] MAZE = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1},
            {3, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
    };

    public enum DIRECTION {
        NORTH(-1, 0),
        SOUTH(1, 0),
        EAST(0, 1),
        WEST(0, -1);

        private final int dx;
        private final int dy;

        DIRECTION(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting maze:");
        display(MAZE);

        System.out.println("\nSolving maze...");
        if (solve(3, 4)) {
            System.out.println("Maze solved:");
            display(MAZE);
        } else {
            System.out.println("No path to the exit found.");
        }
    }
    public static void display(int[][] image) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[0].length; y++) {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }
    }

    public static boolean solve(int x, int y) {
        Stack<Position> stack = new Stack<>();
        stack.push(new Position(x, y));

        while (!stack.isEmpty()) {
            Position current = stack.pop();

            if (MAZE[current.x][current.y] == EXIT) {
                return true;
            }

            // Mark current position as visited
            MAZE[current.x][current.y] = VISITED;

            // Explore all possible directions
            for (DIRECTION direction : DIRECTION.values()) {
                int newX = current.x + direction.getDx();
                int newY = current.y + direction.getDy();

                if (isValidMove(newX, newY)) {
                    stack.push(new Position(newX, newY));
                }
            }
        }

        return false;
    }

    private static boolean isValidMove(int x, int y) {
        return x >= 0 && x < MAZE.length && y >= 0 && y < MAZE[0].length &&
                (MAZE[x][y] == PATH || MAZE[x][y] == EXIT);
    }

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}