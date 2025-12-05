package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {
    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day01.solve("""
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
                """)).isEqualTo(3);

        Path path = Path.of(Day01.class.getResource("Day01.txt").toURI());
        assertThat(Day01.solve(Files.readString(path))).isEqualTo(1105);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day01.solve2("""
                L68
                L30
                R48
                L5
                R60
                L55
                L1
                L99
                R14
                L82
                """)).isEqualTo(6);

        Path path = Path.of(Day01.class.getResource("Day01.txt").toURI());
        assertThat(Day01.solve2(Files.readString(path))).isEqualTo(6599);
    }
}
