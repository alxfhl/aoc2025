package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day01 {


    public static int solve(String s) {
        final List<String> lines = Arrays.stream(s.split("\n")).filter(l -> !l.isBlank()).toList();

        int result = 0;
        int dial = 50;
        for (String line : lines) {
            int count = Integer.parseInt(line.substring(1));
            if (line.charAt(0) == 'R') {
                dial += count;
            } else {
                dial -= count;
            }
            if (dial % 100 == 0) {
                result++;
            }
        }
        return result;
    }

    public static int solve2(String s) {
        final List<String> lines = Arrays.stream(s.split("\n")).filter(l -> !l.isBlank()).toList();

        int result = 0;
        int dial = 50;
        for (String line : lines) {
            int count = Integer.parseInt(line.substring(1));
            result += count / 100;
            count = count % 100;
            if (count == 0) {
                continue;
            }
            if (line.charAt(0) == 'R') {
                dial += count;
            } else {
                dial -= count;
                if (dial == -count) {
                    // was 0
                    dial += 100;
                    continue;
                }
            }
            if (dial <= 0) {
                result++;
                if (dial < 0) {
                    dial += 100;
                }
            }
            if (dial >= 100) {
                result++;
                dial -= 100;
            }
        }
        return result;
    }
}
