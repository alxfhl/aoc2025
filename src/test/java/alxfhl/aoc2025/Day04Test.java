package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day04Test {

    public static final String EXAMPLE1 = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day04.solve(EXAMPLE1)).isEqualTo(13);

        Path path = Path.of(Day04.class.getResource("Day04.txt").toURI());
        assertThat(Day04.solve(Files.readString(path))).isEqualTo(1395);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day04.solve2(EXAMPLE1)).isEqualTo(43);

        Path path = Path.of(Day04.class.getResource("Day04.txt").toURI());
        assertThat(Day04.solve2(Files.readString(path))).isEqualTo(8451);
    }
}
