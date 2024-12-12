package nl.dizmizzer.aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 extends Day {

    List<Equation> equations = new ArrayList<>();

    @Override
    public long solveOne() {
        long sum = 0;
        for (Equation equation : equations) {
            if (canSolve(equation, 1, equation.numbers.get(0))) {
                sum += equation.sum;

            }
        }
        return sum;
    }

    public boolean canSolve(Equation equation, int offset, long result) {
        if (offset == equation.numbers.size()) {
            return result == equation.sum;
        }
        else if (result > equation.sum) {
            return false;
        }
        if (canSolve(equation, offset + 1, result + equation.numbers.get(offset))) {
            return true;
        }

        return canSolve(equation, offset + 1, result * equation.numbers.get(offset));
    }

    @Override
    public long solveTwo() {
        long sum = 0;
        for (Equation equation : equations) {
            if (canSolveTwo(equation, 1, equation.numbers.get(0))) {
                sum += equation.sum;

            }
        }
        return sum;
    }

    public long mergeNumbers(long left, long right) {
        long temp = right;
        while (temp != 0) {
            left = left * 10;
            temp /= 10;
        }
        return left + right;
    }

    public boolean canSolveTwo(Equation equation, int offset, long result) {
        if (offset == equation.numbers.size()) {
            return result == equation.sum;
        }
        else if (result >= equation.sum) {
            return false;
        }
        if (canSolveTwo(equation, offset + 1, result + equation.numbers.get(offset))) {
            return true;
        }
        if (canSolveTwo(equation, offset + 1, mergeNumbers(result, equation.numbers.get(offset)))) {
            return true;
        }

        return canSolveTwo(equation, offset + 1, result * equation.numbers.get(offset));
    }

    @Override
    public void setup() {
        super.setup();
        for (String s : input) {
            String[] spl = s.split(": ");
            long sum = Long.parseLong(spl[0]);
            List<Long> numbers = Arrays.stream(spl[1].trim().split(" ")).map(Long::parseLong).toList();
            equations.add(new Equation(sum, numbers));
        }
    }


    public static class Equation {
        long sum;
        List<Long> numbers;

        public Equation(long sum, List<Long> numbers) {
            this.sum = sum;
            this.numbers = numbers;
        }


    }
}
