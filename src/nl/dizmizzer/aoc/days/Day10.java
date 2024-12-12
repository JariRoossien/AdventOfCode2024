package nl.dizmizzer.aoc.days;

public class Day10 extends Day {

    int[][] pos;

    public int dfsCounter(int[][] grid, int y, int x, int curr, boolean[][] visited) {
        if (visited[y][x]) return 0;
        visited[y][x] = true;
        if (curr == 9) return 1;
        int[][] directions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        int sum = 0;
        for (int[] direction : directions) {
            int dx = x + direction[0];
            int dy = y + direction[1];

            if (dy >= 0 && dy < grid.length && dx >= 0 && dx < grid[0].length) {

                if (grid[dy][dx] == curr + 1) sum += dfsCounter(grid, dy, dx, curr + 1, visited);
            }
        }
        return sum;
    }

    @Override
    public long solveOne() {
        long sum = 0;
        for (int y = 0; y < pos.length; y++) {
            for (int x = 0; x < pos[y].length; x++) {
                if (pos[y][x] == 0) {
                    boolean[][] visited = new boolean[pos.length][pos[y].length];
                    int paths = dfsCounter(pos, y, x, 0, visited);
                    sum += paths;
                }
            }
        }
        return sum;
    }

    public int dfsCounter(int[][] grid, int y, int x, int curr) {
//        if (visited[y][x]) return 0;
//        visited[y][x] = true;
        if (curr == 9) return 1;
        int[][] directions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        int sum = 0;
        for (int[] direction : directions) {
            int dx = x + direction[0];
            int dy = y + direction[1];

            if (dy >= 0 && dy < grid.length && dx >= 0 && dx < grid[0].length) {

                if (grid[dy][dx] == curr + 1) sum += dfsCounter(grid, dy, dx, curr + 1);
            }
        }
        return sum;
    }

    @Override
    public long solveTwo() {
        long sum = 0;
        for (int y = 0; y < pos.length; y++) {
            for (int x = 0; x < pos[y].length; x++) {
                if (pos[y][x] == 0) {
                    int paths = dfsCounter(pos, y, x, 0);
                    sum += paths;
                }
            }
        }
        return sum;

    }

    @Override
    public void setup() {
        super.setup();
        int y = 0;
        pos = new int[input.size()][];
        for (String s : input) {
            int x = 0;
            pos[y] = new int[s.length()];
            for (char c : s.toCharArray()) {
                pos[y][x++] = c - '0';
            }
            y++;
        }
    }
}
