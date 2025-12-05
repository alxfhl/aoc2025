package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day02Test {

    public static final String EXAMPLE1 = """
            
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day02.solve(EXAMPLE1)).isEqualTo(3);

        Path path = Path.of(Day02.class.getResource("Day02.txt").toURI());
        assertThat(Day02.solve(Files.readString(path))).isEqualTo(42);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day02.solve2(EXAMPLE1)).isEqualTo(6);

        Path path = Path.of(Day02.class.getResource("Day02.txt").toURI());
        assertThat(Day02.solve2(Files.readString(path))).isEqualTo(42);
    }
}
