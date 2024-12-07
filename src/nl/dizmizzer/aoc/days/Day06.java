package nl.dizmizzer.aoc.days;

import java.util.*;

public class Day06 extends Day {

    char[][] map;
    int[] startingPosition = new int[2];

    @Override
    public long solveOne() {
        int[] curr = startingPosition;
        int direction = 0;
        int count = 0;
        while (curr[0] >= 0 && curr[0] < map.length && curr[1] >= 0 && curr[1] < map[0].length) {
            int dY = curr[0];
            int dX = curr[1];
            switch (direction) {
                case 0:
                    dY -= 1;
                    break;
                case 1:
                    dX += 1;
                    break;
                case 2:
                    dY += 1;
                    break;
                case 3:
                    dX -= 1;
                    break;
            }
            if (dY >= 0 && dY < map.length && dX >= 0 && dX < map[0].length) {
                if (map[dY][dX] == '#') {
                    direction = (direction + 1) % 4;
                } else {
                    if (map[curr[0]][curr[1]] != 'X') {
                        count++;
                    }
                    map[curr[0]][curr[1]] = 'X';
                    curr[0] = dY;
                    curr[1] = dX;
                }
            } else {
                break;
            }

        }
        return count + 1;
    }

    public boolean loops() {
        Map<String, Set<Character>> hasVisited = new HashMap<>();
        int[] curr = startingPosition.clone();
        int direction = 0;
        while (curr[0] >= 0 && curr[0] < map.length && curr[1] >= 0 && curr[1] < map[0].length) {
            int dY = curr[0];
            int dX = curr[1];
            switch (direction) {
                case 0:
                    dY -= 1;
                    break;
                case 1:
                    dX += 1;
                    break;
                case 2:
                    dY += 1;
                    break;
                case 3:
                    dX -= 1;
                    break;
            }
            if (dY >= 0 && dY < map.length && dX >= 0 && dX < map[0].length) {
                if (map[dY][dX] == '#') {
                    direction = (direction + 1) % 4;
                    continue;
                } else {
                    String coord = curr[0] + "," + curr[1];
                    Character currDir = switch (direction) {
                        case 0 -> 'A';
                        case 1 -> 'B';
                        case 2 -> 'C';
                        case 3 -> 'D';
                        default -> 'E';
                    };

                    if (hasVisited.getOrDefault(coord, new HashSet<>()).contains(currDir)) {
                        return true;
                    }

                    if (!hasVisited.containsKey(coord)) {
                        hasVisited.put(coord, new HashSet<>());
                    }

                    hasVisited.get(coord).add(currDir);
                }
            }

            curr[0] = dY;
            curr[1] = dX;

        }

        return false;

    }
    @Override
    public long solveTwo() {
        setup();
        int count = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] != '.') continue;
                map[y][x] = '#';
                if (y == startingPosition[0] && x == startingPosition[1] - 1) {
                    loops();
                }
                if (loops()) {
                    count++;
                }
                map[y][x] = '.';
            }
        }
        return count;
    }

    @Override
    public void setup() {
        super.setup();
        map = new char[input.size()][];
        int counter = 0;
        for (String s : input) {
            if (s.contains("^")) {
                startingPosition[0] = counter;
                startingPosition[1] = s.indexOf('^');
            }
            map[counter++] = s.toCharArray();
        }
    }
}
