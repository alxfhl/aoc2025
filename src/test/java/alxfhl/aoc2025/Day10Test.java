package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day10Test {

    public static final String EXAMPLE1 = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day10.solve(EXAMPLE1)).isEqualTo(7);

        Path path = Path.of(Day10.class.getResource("Day10.txt").toURI());
        assertThat(Day10.solve(Files.readString(path))).isEqualTo(473);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day10.solve2(EXAMPLE1)).isEqualTo(33);

        Path path = Path.of(Day10.class.getResource("Day10.txt").toURI());
        assertThat(Day10.solve2(Files.readString(path))).isEqualTo(18681);
    }
}
