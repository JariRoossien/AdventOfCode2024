package nl.dizmizzer.aoc.days;

public class Day09 extends Day {

    int[] nums;
    String s;
    @Override
    public long solveOne() {

        return 0;
    }

    @Override
    public long solveTwo() {
        return 0;
    }

    @Override
    public void setup() {
        super.setup();
        int totalPos = 0;
        s = input.get(0);
        for (int i = 0; i < s.length(); i += 2) {
            totalPos += s.charAt(i) - '0';
        }

        nums = new int[(int) totalPos];
    }
}
