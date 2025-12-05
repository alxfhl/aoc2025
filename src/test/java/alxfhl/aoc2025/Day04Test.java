package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day04Test {

    public static final String EXAMPLE1 = """
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day04.solve(EXAMPLE1)).isEqualTo(1_227_775_554L);

        Path path = Path.of(Day04.class.getResource("Day04.txt").toURI());
        assertThat(Day04.solve(Files.readString(path))).isEqualTo(38_310_256_125L);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day04.solve2(EXAMPLE1)).isEqualTo(4_174_379_265L);

        Path path = Path.of(Day04.class.getResource("Day04.txt").toURI());
        assertThat(Day04.solve2(Files.readString(path))).isEqualTo(58_961_152_806L);
    }
}
