package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day06Test {

    public static final String EXAMPLE1 = """
            123 328  51 64 
             45 64  387 23 
              6 98  215 314
            *   +   *   +  
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day06.solve(EXAMPLE1)).isEqualTo(4_277_556);

        Path path = Path.of(Day06.class.getResource("Day06.txt").toURI());
        assertThat(Day06.solve(Files.readString(path))).isEqualTo(7_326_876_294_741L);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day06.solve2(EXAMPLE1)).isEqualTo(3_263_827L);

        Path path = Path.of(Day06.class.getResource("Day06.txt").toURI());
        assertThat(Day06.solve2(Files.readString(path))).isEqualTo(10_756_006_415_204L);
    }
}
