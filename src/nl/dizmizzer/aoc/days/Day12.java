package nl.dizmizzer.aoc.days;

import java.util.HashMap;
import java.util.Map;

public class Day12 extends Day {


    int[][] uniqueGrid;
    Map<Integer, Integer> area;

    @Override
    public long solveOne() {
        area = new HashMap<>();
        Map<Integer, Integer> perimeter = new HashMap<>();
        for (int y = 0; y < uniqueGrid.length; y++) {
            for (int x = 0; x < uniqueGrid[0].length; x++) {
                int toCheck = uniqueGrid[y][x];

                // Just calculate area by adding it.
                area.put(toCheck, area.getOrDefault(toCheck, 0) + 1);
                // If it has items above, check
                if (y > 0) {
                    if (uniqueGrid[y - 1][x] != toCheck) {
                        perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                    }
                } else {
                    perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                }

                // If it has items left, check
                if (x > 0) {
                    if (uniqueGrid[y][x - 1] != toCheck) {
                        perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                    }
                } else {
                    perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                }

                // If it has items down, check
                if (y< uniqueGrid.length - 1) {
                    if (uniqueGrid[y + 1][x] != toCheck) {
                        perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                    }
                } else {
                    perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                }

                // If it has items right, check
                if (x < uniqueGrid[0].length - 1) {
                    if (uniqueGrid[y][x + 1] != toCheck) {
                        perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                    }
                } else {
                    perimeter.put(toCheck, perimeter.getOrDefault(toCheck, 0) + 1);
                }
            }
        }
        long sum = 0;
        for (Integer key : area.keySet()) {
            sum += ((long) area.get(key) * perimeter.get(key));
        }
        return sum;
    }

    boolean hasRoof(int y, int x) {
        if (y == 0) return true;
        return uniqueGrid[y][x] != uniqueGrid[y - 1][x];
    }

    boolean hasFloor(int y, int x) {
        if (y == uniqueGrid.length - 1) return true;
        return uniqueGrid[y][x] != uniqueGrid[y + 1][x];
    }

    boolean hasLeftWall(int y, int x) {
        if (x == 0) return true;
        return uniqueGrid[y][x - 1] != uniqueGrid[y][x];
    }
    boolean hasRightWall(int y, int x) {
        if (x == uniqueGrid[0].length - 1) return true;
        return uniqueGrid[y][x + 1] != uniqueGrid[y][x];
    }

    @Override
    public long solveTwo() {
        Map<Integer, Integer> sides = new HashMap<>();

        for (int y = 0; y < uniqueGrid.length; y++) {
            for (int x = 0; x < uniqueGrid[0].length; x++) {
                int toCheck = uniqueGrid[y][x];
                if (hasRoof(y, x) && hasLeftWall(y, x)) {
                    sides.put(toCheck, sides.getOrDefault(toCheck, 0) + 2);
                }
                if (hasLeftWall(y, x) && hasFloor(y, x)) {
                    sides.put(toCheck, sides.getOrDefault(toCheck, 0) + 1);
                }
                if (hasRoof(y, x) && hasRightWall(y, x)) {
                    sides.put(toCheck, sides.getOrDefault(toCheck, 0) + 1);
                }

                if (y > 0 && uniqueGrid[y -1][x] == toCheck && hasLeftWall(y, x) && !hasLeftWall(y - 1, x)) {
                    sides.put(toCheck, sides.getOrDefault(toCheck, 0) + 1);
                }
                if (y > 0 && uniqueGrid[y -1][x] == toCheck && hasRightWall(y, x) && !hasRightWall(y - 1, x)) {
                    sides.put(toCheck, sides.getOrDefault(toCheck, 0) + 1);
                }

                if (x > 0 && uniqueGrid[y][x - 1] == toCheck && hasRoof(y, x) && !hasRoof(y, x - 1)) {
                    sides.put(toCheck, sides.getOrDefault(toCheck, 0) + 1);
                }
                if (x > 0 && uniqueGrid[y][x - 1] == toCheck && hasFloor(y, x) && !hasFloor(y, x - 1)) {
                    sides.put(toCheck, sides.getOrDefault(toCheck, 0) + 1);
                }



            }
        }
        long sum = 0;
        for (Integer key : area.keySet()) {
            sum += ((long) area.get(key) * sides.get(key));
        }
        return sum;

    }

    public void dfs(int y, int x, int input, char[][] grid, boolean[][] visited) {
        char toBfsFor = grid[y][x];
        uniqueGrid[y][x] = input;
        visited[y][x] = true;
        int[][] directions = {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
        };

        for (int[] direction : directions) {
            int dy = y + direction[0];
            int dx = x + direction[1];
            if (dy < 0 || dy >= grid.length || dx < 0 || dx >= grid[0].length) { continue; }
            if (visited[dy][dx]) continue;
            if (grid[dy][dx] != toBfsFor) { continue; }
            dfs(dy, dx, input, grid, visited);
        }
    }
    @Override
    public void setup() {
        super.setup();
        boolean[][] visited = new boolean[input.size()][input.get(0).length()];
        uniqueGrid = new int[visited.length][visited[0].length];
        int count = 0;
        char[][] grid = input.stream().map(String::toCharArray).toArray(char[][]::new);
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                if (!visited[y][x]) {
                    dfs(y, x, count++, grid, visited);
                }
            }
        }


    }
}
