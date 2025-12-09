package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day09Test {

    public static final String EXAMPLE1 = """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day09.solve(EXAMPLE1)).isEqualTo(50);

        Path path = Path.of(Day09.class.getResource("Day09.txt").toURI());
        assertThat(Day09.solve(Files.readString(path))).isEqualTo(4_767_418_746L);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day09.solve2(EXAMPLE1)).isEqualTo(24);

        Path path = Path.of(Day09.class.getResource("Day09.txt").toURI());
        assertThat(Day09.solve2(Files.readString(path))).isEqualTo(1_461_987_144L);
    }
}
