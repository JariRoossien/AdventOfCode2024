package nl.dizmizzer.aoc.days;

import java.util.*;

public class Day11 extends Day {

    List<Long> stones = new ArrayList<>();

    public long getLength(long num) {
        long length = 1;
        while (num / 10 != 0) {
            length++;
            num = num / 10;
        }
        return length;
    }

    @Override
    public long solveOne() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < stones.size(); j++) {
                if (stones.get(j) == 0) {
                    stones.set(j, 1L);
                }
                else if (getLength(stones.get(j)) % 2 == 0) {
                    long temp = stones.get(j);
                    long left = (int) (temp / (Math.pow(10, getLength(temp) / 2)));
                    long right = (int) (temp % (Math.pow(10, getLength(temp) / 2)));
                    stones.set(j, left);
                    stones.add(j + 1, right);
                    j+=1;
                } else {
                    stones.set(j, stones.get(j) * 2024);
                }
            }
        }

        return stones.size();
    }

    Map<Long, Long> stoneCount = new HashMap<>();

    @Override
    public long solveTwo() {
        for (int i = 0; i < 75; i++) {
            Map<Long, Long> newMap = new HashMap<>();
            for (long stone : stoneCount.keySet()) {
                if (stone == 0) {
                    newMap.put(1L, newMap.getOrDefault(1L, 0L) + stoneCount.get(stone));
                }
                else if (getLength(stone) % 2 == 0) {
                    long temp = stone;
                    long left = (int) (temp / (Math.pow(10, getLength(temp) / 2)));
                    long right = (int) (temp % (Math.pow(10, getLength(temp) / 2)));
                    newMap.put(left, newMap.getOrDefault(left, 0L) + stoneCount.get(stone));
                    newMap.put(right, newMap.getOrDefault(right, 0L) + stoneCount.get(stone));
                } else {
                    long res = stone * 2024;
                    newMap.put(res, newMap.getOrDefault(res, 0L) + stoneCount.get(stone));
                }
            }
            stoneCount = newMap;
        }
        return stoneCount.values().stream().reduce(0L, Long::sum);

    }

    @Override
    public void setup() {
        super.setup();
        Arrays.stream(input.get(0).split(" ")).map(Long::parseLong).forEach(stones::add);
        Arrays.stream(input.get(0).split(" ")).map(Long::parseLong).forEach(a -> {
            stoneCount.put(a, stoneCount.getOrDefault(a, 0L) + 1);
        });

    }
}
