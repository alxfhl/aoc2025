package alxfhl.aoc2025;

import alxfhl.aoc2025.tools.Parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public class Day10 {

    public static long solve(String s) {
        var lines = s.split("\n");
        long result = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }
            var parts = line.split(" ");
            var target = parseTarget(parts[0]);
            List<Long> buttons = new ArrayList<>();
            for (int i = 1; i < parts.length - 1; i++) {
                buttons.add(parseButton(parts[i]));
            }
            var min = Integer.MAX_VALUE;
            for (int combo = 0; combo < (1 << buttons.size()); combo++) {
                long sum = 0;
                int presses = 0;
                for (int i = 0; i < buttons.size(); i++) {
                    if ((combo & (1 << i)) != 0) {
                        sum ^= buttons.get(i);
                        presses++;
                    }
                }
                if (sum == target) {
                    min = Math.min(min, presses);
                }
            }
            result += min;
        }
        return result;
    }

    private static long parseButton(String part) {
        return Arrays.stream(part.split("\\D+")).filter(p -> !p.isBlank()).mapToInt(p -> 1 << (Integer.parseInt(p))).sum();
    }

    private static long parseTarget(String part) {
        long result = 0;
        long bit = 1;
        for (int i = 1; i < part.length() - 1; i++) {
            if (part.charAt(i) == '#') {
                result += bit;
            }
            bit *= 2;
        }
        return result;
    }

    public static long solve2(String s) {
        var lines = Collections.synchronizedList(Arrays.stream(s.split("\n"))
                .filter(l -> !l.isBlank())
                .sorted(comparingInt(String::length))
                .collect(Collectors.toCollection(ArrayList::new)));
        AtomicLong sum = new AtomicLong(0);
        int threads = 2;
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            for (int i = 0; i < threads; i++) {
                executorService.submit(() -> {
                    while (true) {
                        String line;
                        synchronized (lines) {
                            if (lines.isEmpty()) {
                                return;
                            }
                            line = lines.removeLast();
                        }
                        sum.addAndGet(solveLine2(line));
                    }
                });
            }
        }
        System.out.println("total: " + sum.get());
        return sum.get();
    }

    private static int solveLine2(String line) {
        long startTime = System.currentTimeMillis();
        // parse puzzle input
        var parts = line.split(" ");
        int[] target = toArray(Parse.getIntegers(parts[parts.length - 1]));
        List<int[]> buttons = new ArrayList<>();
        for (int i = 1; i < parts.length - 1; i++) {
            buttons.add(toArray(Parse.getIntegers(parts[i])));
        }
        buttons.sort((a, b) -> Integer.compare(b.length, a.length));
        Integer min = getMin(target, new Buttons(buttons, target), 10000);
        if (min == null) {
            System.out.println("no solution! " + line);
            return 0;
        } else {
            long endTime = System.currentTimeMillis();
            System.out.println(min + (endTime - startTime > 10000 ? " (" + (endTime - startTime) / 1000 + "s)" : "") + " <--- " + line);
            return min;
        }
    }

    static class Buttons {
        List<int[]> buttons;
        int lowestPos;
        List<int[]> matchingButtons = new ArrayList<>();
        int[] button;
        List<int[]> remainingButtons;

        Buttons(List<int[]> buttons, int[] target) {
            this.buttons = buttons;
            // count how many buttons affect each position
            int[] counts = countButtonEffects(target);
            // find the lowest count where the target is not 0
            findLowestPos(target, counts);
            // now we know that there are "lowest" buttons that affect "lowestPos"
            for (int[] b : this.buttons) {
                if (contains(b, lowestPos)) {
                    matchingButtons.add(b);
                }
            }
            if (matchingButtons.isEmpty()) {
                return;
            }
            button = matchingButtons.getFirst();
            remainingButtons = new ArrayList<>(this.buttons.size());
            for (int[] b : this.buttons) {
                if (b != button) {
                    remainingButtons.add(b);
                }
            }
        }

        private int[] countButtonEffects(int[] target) {
            int[] counts = new int[target.length];
            for (int[] button : buttons) {
                for (int pos : button) {
                    counts[pos]++;
                }
            }
            return counts;
        }

        private void findLowestPos(int[] target, int[] counts) {
            int lowest = Integer.MAX_VALUE;
            lowestPos = Integer.MAX_VALUE;
            for (int pos = 0; pos < target.length; pos++) {
                int count = counts[pos];
                if (target[pos] == 0 || count == 0) {
                    continue;
                }
                if (count < lowest) {
                    lowest = count;
                    lowestPos = pos;
                }
            }
        }
    }

    private static Integer getMin(int[] target, Buttons buttons, int limit) {
        boolean done = true;
        for (int t : target) {
            if (t > 0) {
                done = false;
                break;
            }
        }
        if (done) {
            return 0;
        }
        if (buttons.buttons.isEmpty() || limit <= 0 || buttons.lowestPos == Integer.MAX_VALUE) {
            return null;
        }
        int maxPresses = target[buttons.lowestPos];
        if (buttons.matchingButtons.size() == 1) {
            if (maxPresses > limit) {
                return null;
            }
            // there is only one button that affects that position, so we know exactly how often to press it
            int[] newTarget = new int[target.length];
            for (int pos = 0; pos < target.length; pos++) {
                int newValue = contains(buttons.button, pos) ? target[pos] - maxPresses : target[pos];
                if (newValue < 0) {
                    return null;
                }
                newTarget[pos] = newValue;
            }
            Integer min = getMin(newTarget, new Buttons(buttons.remainingButtons, newTarget), limit - maxPresses);
            return min == null ? null : maxPresses + min;
        }
        // otherwise we need to try all possibilities for the first button
        int[] newTarget = new int[target.length];
        System.arraycopy(target, 0, newTarget, 0, target.length);
        for (int pos : buttons.button) {
            if (newTarget[pos] < maxPresses) {
                maxPresses = newTarget[pos];
            }
        }
        if (limit < maxPresses) {
            maxPresses = limit;
        }
        Integer min = null;
        for (int presses = 0; presses <= maxPresses; presses++) {
            int newLimit = min == null ? limit - presses : min - 1;
            Integer additionalPresses = getMin(newTarget, new Buttons(buttons.remainingButtons, newTarget), newLimit);
            if (additionalPresses != null) {
                additionalPresses += presses;
                if (min == null || additionalPresses < min) {
                    min = additionalPresses;
                }
            }
            for (Integer pos : buttons.button) {
                newTarget[pos]--;
            }
        }
        return min;
    }

    private static boolean contains(int[] array, int value) {
        for (int x : array) {
            if (x == value) {
                return true;
            }
        }
        return false;
    }

    private static int[] toArray(List<Integer> integers) {
        return integers.stream().mapToInt(i -> i).toArray();
    }
}
