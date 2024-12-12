package nl.dizmizzer.aoc.days;

import java.util.*;

public class Day08 extends Day {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    @Override
    public long solveOne() {
        Map<Character, List<Point>> prevs = new HashMap<Character, List<Point>>();
        Set<Point> towers = new HashSet<Point>();

        int yLimit = input.size();
        int xLimit = input.get(0).length();

        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) != '.') {
                    char c = input.get(y).charAt(x);
                    if (!prevs.containsKey(c)) {
                        prevs.put(c, new ArrayList<>());
                    }
                    List<Point> existing = prevs.get(c);
                    for (Point p : existing) {
                        int yDifference = y - p.y;
                        int xDifference = x - p.x;
                        int prevY = p.y - yDifference;
                        int prevX = p.x - xDifference;
                        int nextY = y + yDifference;
                        int nextX = x + xDifference;
                        if (prevY >= 0 && prevY < yLimit && prevX >= 0 && prevX < xLimit) {
                            towers.add(new Point(prevX, prevY));
                        }
                        if (nextY >= 0 && nextY < yLimit && nextX >= 0 && nextX < xLimit) {
                            towers.add(new Point(nextX, nextY));
                        }
                    }
                    prevs.get(c).add(new Point(x, y));
                }
            }
        }

        return towers.size();
    }

    @Override
    public long solveTwo() {
        Map<Character, List<Point>> prevs = new HashMap<Character, List<Point>>();
        Set<Point> towers = new HashSet<Point>();

        int yLimit = input.size();
        int xLimit = input.get(0).length();

        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) != '.') {
                    char c = input.get(y).charAt(x);
                    if (!prevs.containsKey(c)) {
                        prevs.put(c, new ArrayList<>());
                    }
                    List<Point> existing = prevs.get(c);
                    for (Point p : existing) {
                        towers.add(new Point(p.x, p.y));
                        towers.add(new Point(x, y));
                        int yDifference = y - p.y;
                        int xDifference = x - p.x;
                        int prevY = p.y - yDifference;
                        int prevX = p.x - xDifference;
                        int nextY = y + yDifference;
                        int nextX = x + xDifference;

                        while (prevY >= 0 && prevY < yLimit && prevX >= 0 && prevX < xLimit) {
                            towers.add(new Point(prevX, prevY));
                            prevY = prevY - yDifference;
                            prevX = prevX - xDifference;
                        }
                        while (nextY >= 0 && nextY < yLimit && nextX >= 0 && nextX < xLimit) {
                            towers.add(new Point(nextX, nextY));
                            nextY = nextY + yDifference;
                            nextX = nextX + xDifference;

                        }
                    }
                    prevs.get(c).add(new Point(x, y));
                }
            }
        }
        return towers.size();
    }


}
