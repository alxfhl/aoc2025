package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day05Test {

    public static final String EXAMPLE1 = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day05.solve(EXAMPLE1)).isEqualTo(3);

        Path path = Path.of(Day05.class.getResource("Day05.txt").toURI());
        assertThat(Day05.solve(Files.readString(path))).isEqualTo(681);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day05.solve2(EXAMPLE1)).isEqualTo(14);

        Path path = Path.of(Day05.class.getResource("Day05.txt").toURI());
        assertThat(Day05.solve2(Files.readString(path))).isEqualTo(348820208020395L);
    }
}
