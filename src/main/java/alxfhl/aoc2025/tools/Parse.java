package alxfhl.aoc2025.tools;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Parse {
    public static List<Long> getLongs(String s) {
        List<Long> result = new ArrayList<>();
        for (String part : s.split("[^-0-9]+")) {
            if (!part.isBlank()) {
                result.add(Long.valueOf(part));
            }
        }
        return result;
    }

    public static List<Integer> getIntegers(String s) {
        List<Integer> result = new ArrayList<>();
        for (String part : s.split("[^-0-9]+")) {
            if (!part.isBlank()) {
                result.add(Integer.valueOf(part));
            }
        }
        return result;
    }

    /**
     * Split list of input lines by empty lines into non-empty blocks.
     */
    public static List<List<String>> splitIntoBlocks(List<String> lines) {
        List<List<String>> blocks = new ArrayList<>();
        List<String> currentBlock = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) {
                if (!currentBlock.isEmpty()) {
                    blocks.add(currentBlock);
                    currentBlock = new ArrayList<>();
                }
            } else {
                currentBlock.add(line);
            }
        }
        if (!currentBlock.isEmpty()) {
            blocks.add(currentBlock);
        }
        return blocks;
    }
}
