package nl.dizmizzer.aoc.days;

import java.util.*;

public class Day01 extends Day {

    PriorityQueue<Integer> l = new PriorityQueue<>();
    PriorityQueue<Integer> r = new PriorityQueue<>();

    @Override
    public long solveOne() {
        int difference = 0;
        while (!l.isEmpty() && !r.isEmpty()) {
            difference += Math.abs(l.poll() - r.poll());
        }
        return difference;
    }

    Map<Integer, Integer> countingMap = new HashMap<>();
    List<Integer> leftList = new ArrayList<>();

    @Override
    public long solveTwo() {
        long sum = 0;
        for (int i : leftList) {
            sum += (long) i * countingMap.getOrDefault(i, 0);
        }
        return sum;
    }

    @Override
    public void setup() {
        super.setup();
        for (String s : input) {
            if (s.isEmpty()) continue;
            String[] in = s.trim().split(" {3}");

            int leftKey = Integer.parseInt(in[0]);
            int rightKey = Integer.parseInt(in[1]);

            // Priority queue for part 1
            l.add(leftKey);
            r.add(rightKey);

            // Part 2
            leftList.add(leftKey);
            countingMap.put(rightKey, countingMap.getOrDefault(rightKey, 0) + 1);

        }
    }

}
