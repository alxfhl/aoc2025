package alxfhl.aoc2025;

import alxfhl.aoc2025.tools.CharMatrix;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toCollection;

public class Day06 {

    @RequiredArgsConstructor
    enum Operation {
        ADDITION("+", Long::sum),
        MULTIPLICATION("*", (a, b) -> a * b);

        final String operation;
        final BiFunction<Long, Long, Long> function;

        static Operation parseOperation(String s) {
            return Arrays.stream(values()).filter(o -> o.operation.equals(s)).findFirst().orElseThrow();
        }
    }

    public static long solve(String s) {
        List<String> lines = Arrays.stream(s.split("\n")).filter(l -> !l.isBlank()).toList();
        List<List<Long>> numbers = new ArrayList<>();
        List<Operation> operations = new ArrayList<>();
        for (int i = 0; i < lines.size() - 1; i++) {
            numbers.add(Arrays.stream(lines.get(i).split("\\s+")).filter(n -> !n.isBlank()).map(Long::parseLong).toList());
        }
        operations = Arrays.stream(lines.getLast().split("\\s+")).filter(n -> !n.isBlank()).map(Operation::parseOperation).toList();
        long result = 0;
        for (int x = 0; x < numbers.get(0).size(); x++) {
            Operation operation = operations.get(x);
            long current = numbers.get(0).get(x);
            for (int i = 1; i < numbers.size(); i++) {
                current = operation.function.apply(current, numbers.get(i).get(x));
            }
            result += current;
        }
        return result;
    }

    public static long solve2(String s) {
        List<String> lines = Arrays.stream(s.split("\n")).filter(l -> !l.isBlank()).collect(toCollection(ArrayList::new));
        long maxLength = lines.stream().mapToLong(String::length).max().getAsLong();
        for (int i = 0; i < lines.size(); i++) {
            while (lines.get(i).length() < maxLength) {
                lines.set(i, lines.get(i) + " ");
            }
        }
        CharMatrix matrix = CharMatrix.valueOf(lines).transposed();
        long result = 0;
        long intermediate = 0;
        Operation operation = Operation.ADDITION;
        for (int i = 0; i < matrix.getHeight(); i++) {
            String row = String.valueOf(matrix.getRow(i)).trim();
            if (row.isEmpty()) {
                result += intermediate;
                intermediate = 0;
                continue;
            }
            if (row.endsWith("*")) {
                operation = Operation.MULTIPLICATION;
                row = row.substring(0, row.length() - 1);
                intermediate = 1;
            } else if (row.endsWith("+")) {
                operation = Operation.ADDITION;
                row = row.substring(0, row.length() - 1);
                intermediate = 0;
            }
            long value = Long.parseLong(row.trim());
            intermediate = operation.function.apply(intermediate, value);
        }
        return result + intermediate;
    }
}
