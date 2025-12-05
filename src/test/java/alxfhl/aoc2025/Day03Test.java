package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day03Test {

    public static final String EXAMPLE1 = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day03.solve(EXAMPLE1)).isEqualTo(357);

        Path path = Path.of(Day03.class.getResource("Day03.txt").toURI());
        assertThat(Day03.solve(Files.readString(path))).isEqualTo(16812);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day03.solve2(EXAMPLE1)).isEqualTo(3_121_910_778_619L);

        Path path = Path.of(Day03.class.getResource("Day03.txt").toURI());
        assertThat(Day03.solve2(Files.readString(path))).isEqualTo(166_345_822_896_410L);
    }
}
