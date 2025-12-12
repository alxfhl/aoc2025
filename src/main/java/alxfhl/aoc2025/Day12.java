package alxfhl.aoc2025;

import java.util.Arrays;
import java.util.List;

public class Day12 {

    public static long solve(String s) {
        long result = 0;
        int sure = 0;
        int realistic = 0;
        int maximum = 0;
        final List<String> lines = Arrays.stream(s.split("\n")).filter(l -> l.contains("x")).toList();
        // number of tiles the piece has itself
        double[] min = {7, 7, 7, 5, 6, 7};
        // a realistic guess of how many tiles the piece will block on average in larger numbers
        double[] prob = {8, 7.5, 8, 6, 6, 8.5};
        // number of tiles the piece will take at max (3x3)
        double[] max = {9, 9, 9, 9, 9, 9};
        for (String line : lines) {
            int area = Integer.parseInt(line.substring(0, 2)) * Integer.parseInt(line.substring(3, 5));
            List<Integer> counts = Arrays.stream(line.substring(7).split(" ")).map(Integer::parseInt).toList();
            double minSum = 0;
            double probSum = 0;
            double maxSum = 0;
            for (int i = 0; i < 6; i++) {
                minSum += min[i] * counts.get(i);
                probSum += prob[i] * counts.get(i);
                maxSum += max[i] * counts.get(i);
            }
            if (maxSum <= area) {
                sure++;
                realistic++;
                maximum++;
            } else if (probSum <= area) {
                realistic++;
                maximum++;
            } else if (minSum <= area) {
                maximum++;
            }
        }
        // well... the puzzle is set in a way that all these three numbers are actually identical
        System.out.println("sure: " + sure);
        System.out.println("realistic: " + realistic);
        System.out.println("maximum: " + maximum);
        return result;
    }
}
