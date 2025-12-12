package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day12Test {

    public static final String EXAMPLE1 = """
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        Path path = Path.of(Day12.class.getResource("Day12.txt").toURI());
        Day12.solve(Files.readString(path));
    }
}
