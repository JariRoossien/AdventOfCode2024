package nl.dizmizzer.aoc.days;

import java.util.*;

public class Day05 extends Day {

    Map<Integer, Set<Integer>> prevs = new HashMap<>();
    ArrayList<ArrayList<Integer>> jobs = new ArrayList<>();

    @Override
    public long solveOne() {
        List<List<Integer>> correct = new ArrayList<>();
        outer:
        for (List<Integer> job : jobs) {
            for (int i = 0; i < job.size(); i++) {
                for (int j = i - 1; j >= 0; j--) {
                    int toCheck = job.get(i);
                    int prev = job.get(j);
                    if (!prevs.containsKey(prev)) continue;
                    if (prevs.get(prev).contains(toCheck)) continue outer;
                }
            }
            correct.add(job);
        }
        long sum = 0;
        for (List<Integer> job : correct) {
            sum += job.get(job.size() / 2);
        }
        return sum;
    }

    public List<Integer> swapUntilCorrect(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                int toCheck = list.get(i);
                int prev = list.get(j);
                if (!prevs.containsKey(prev)) continue;
                if (prevs.get(prev).contains(toCheck)) {
                    list.set(j, toCheck);
                    list.set(i, prev);
                    return swapUntilCorrect(list);
                }
            }
        }
        return list;
    }
    @Override
    public long solveTwo() {
        List<List<Integer>> incorrect = new ArrayList<>();
        outer:
        for (ArrayList<Integer> job : jobs) {
            for (int i = 0; i < job.size(); i++) {
                for (int j = i - 1; j >= 0; j--) {
                    int toCheck = job.get(i);
                    int prev = job.get(j);
                    if (!prevs.containsKey(prev)) continue;
                    if (prevs.get(prev).contains(toCheck)) {
                        incorrect.add(job);
                        swapUntilCorrect(job);
                        continue outer;
                    }
                }
            }
        }
        long sum = 0;
        for (List<Integer> job : incorrect) {
            sum += job.get(job.size() / 2);
        }
        return sum;

    }

    @Override
    public void setup() {
        super.setup();

        boolean isRule = true;
        for (String s : input) {
            if (s.isEmpty()) {
                isRule = false;
                continue;
            }

            if (isRule) {
                String[] spl = s.split("\\|");
                int before = Integer.parseInt(spl[0]);
                int after = Integer.parseInt(spl[1]);
                if (!prevs.containsKey(after)) {
                    prevs.put(after, new HashSet<>());
                }
                prevs.get(after).add(before);

            } else {
                jobs.add(new ArrayList<>(Arrays.stream(s.split(",")).map(Integer::parseInt).toList()));
            }
        }
    }


}
