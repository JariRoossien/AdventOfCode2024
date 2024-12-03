package nl.dizmizzer.aoc.days;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {

    String longString = "";
    Pattern pattern = Pattern.compile("(mul\\([0-9]+,[0-9]+\\))");

    @Override
    public long solveOne() {
        Matcher m = pattern.matcher(longString);
        long result = 0;
        while (m.find()) {

            String input = m.group();
            String filtered = input.replace("mul(", "").replace(")", "");
            String[] split = filtered.split(",");
            long left = Long.parseLong(split[0]);
            long right = Long.parseLong(split[1]);
            result += left * right;
        }
        return result;
    }

    @Override
    public long solveTwo() {
        boolean enabled = true;
        long result = 0;
        for (int i = 0; i < longString.length(); i++) {
            if (longString.substring(i).startsWith("don't()")) {
                enabled = false;
            }
            if (longString.substring(i).startsWith("do()")) {
                enabled = true;
            }

            if (enabled && longString.substring(i).startsWith("mul(")) {
                Matcher m = pattern.matcher(longString.substring(i, Math.min(i + 15, longString.length())));
                if (!m.find()) continue;

                String input = m.group();
                String filtered = input.replace("mul(", "").replace(")", "");
                String[] split = filtered.split(",");
                long left = Long.parseLong(split[0]);
                long right = Long.parseLong(split[1]);
                result += left * right;
            }
        }
        return result;
    }

    @Override
    public void setup() {
        super.setup();

        input.forEach(line -> {
            longString = longString + line;
        });
        longString = longString.toLowerCase();
    }
}
