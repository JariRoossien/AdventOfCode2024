package nl.dizmizzer.aoc.days;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends Day {

    List<List<Integer>> nums = new ArrayList<>();

    @Override
    public long solveOne() {

        int correct = 0;

        for (List<Integer> l : nums) {
            if (isSafe(l))
                correct++;
        }
        return correct;
    }

    public boolean isSafe(List<Integer> l) {
        boolean ascending = l.get(0) < l.get(1);
        int curr = l.get(0);
        for (int i = 1; i < l.size(); i++) {
            int diff = l.get(i) - curr;
            curr = l.get(i);
            if (ascending) {
                if (diff > 3 || diff < 1) return false;
            } else {
                if (diff < -3 || diff > -1) return false;
            }
        }
        return true;
    }

    @Override
    public long solveTwo() {
        int correct = 0;

        outer:
        for (List<Integer> l : nums) {

            for (int skip = -1; skip < l.size(); skip++) {
                if (skip == -1) {
                    if (isSafe(l)) {
                        correct++;
                        continue outer;
                    }
                } else {
                    int temp = l.get(skip);
                    l.remove(skip);
                    if (isSafe(l)) {
                        correct++;
                        continue outer;
                    } else {
                        l.add(skip, temp);
                    }
                }
            }
        }
        return correct;

    }

    @Override
    public void setup() {
        super.setup();
        for (String s : input) {
            ArrayList<Integer> list = new ArrayList<>();
            String[] num = s.split(" ");
            for (String number : num) {
                list.add(Integer.parseInt(number));
            }
            nums.add(list);
        }
    }
}
