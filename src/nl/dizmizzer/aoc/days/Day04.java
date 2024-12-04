package nl.dizmizzer.aoc.days;

public class Day04 extends Day {

    char[][] arr;

    public int countWords() {

        // rows
        int count = 0;

        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length - 3; x++) {
                if (arr[y][x] == 'X' && arr[y][x + 1] == 'M' && arr[y][x + 2] == 'A' && arr[y][x + 3] == 'S') { count++; }
                if (arr[y][x] == 'S' && arr[y][x + 1] == 'A' && arr[y][x + 2] == 'M' && arr[y][x + 3] == 'X') { count++; }
            }
        }
        for (int y = 0; y < arr.length - 3; y++) {
            for (int x = 0; x < arr[0].length; x++) {
                if (arr[y][x] == 'X' && arr[y + 1][x] == 'M' && arr[y + 2][x] == 'A' && arr[y + 3][x] == 'S') {
                    count++;
                }
                if (arr[y][x] == 'S' && arr[y + 1][x] == 'A' && arr[y + 2][x] == 'M' && arr[y + 3][x] == 'X') {
                    count++;
                }
            }
        }


        for (int y = 0; y < arr.length - 3; y++) {
            for (int x = 0; x < arr[0].length - 3; x++) {
                if (arr[y][x] == 'X' && arr[y + 1][x + 1] == 'M' && arr[y + 2][x + 2] == 'A' && arr[y + 3][x + 3] == 'S') { count++; }
                if (arr[y][x] == 'S' && arr[y + 1][x + 1] == 'A' && arr[y + 2][x + 2] == 'M' && arr[y + 3][x + 3] == 'X') { count++; }
                if (arr[y][x + 3] == 'X' && arr[y + 1][x + 2] == 'M' && arr[y + 2][x + 1] == 'A' && arr[y + 3][x] == 'S') { count++; }
                if (arr[y][x + 3] == 'S' && arr[y + 1][x + 2] == 'A' && arr[y + 2][x + 1] == 'M' && arr[y + 3][x] == 'X') { count++; }

            }
        }
        return count;
    }

    @Override
    public long solveOne() {
        return countWords();
    }

    public boolean isX(char left, char right) {
        return ((left == 'M' && right == 'S') || (left == 'S' && right == 'M'));
    }

    public int countXMas() {
        int count = 0;
        for (int y = 1; y < arr.length - 1; y++) {
            for (int x = 1; x < arr[0].length - 1; x++) {
                if (arr[y][x] != 'A') continue;
                if (isX(arr[y - 1][x - 1], arr[y + 1][x + 1]) &&
                isX(arr[y - 1][x + 1], arr[y + 1][x - 1])) count++;
            }
        }
        return count;
    }

    @Override
    public long solveTwo() {
        return countXMas();
    }

    @Override
    public void setup() {
        super.setup();
        int count = 0;
        arr = new char[input.size()][];
        for (String line : input) {
            arr[count++] = line.toCharArray();
        }
    }
}
