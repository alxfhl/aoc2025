package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day07Test {

    public static final String EXAMPLE1 = """
            .......S.......
            ...............
            .......^.......
            ...............
            ......^.^......
            ...............
            .....^.^.^.....
            ...............
            ....^.^...^....
            ...............
            ...^.^...^.^...
            ...............
            ..^...^.....^..
            ...............
            .^.^.^.^.^...^.
            ...............
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day07.solve(EXAMPLE1)).isEqualTo(21);

        Path path = Path.of(Day07.class.getResource("Day07.txt").toURI());
        assertThat(Day07.solve(Files.readString(path))).isEqualTo(1698);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day07.solve2(EXAMPLE1)).isEqualTo(40L);

        Path path = Path.of(Day07.class.getResource("Day07.txt").toURI());
        assertThat(Day07.solve2(Files.readString(path))).isEqualTo(95_408_386_769_474L);
    }
}
