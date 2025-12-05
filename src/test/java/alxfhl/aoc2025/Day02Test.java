package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class Day02Test {

    public static final String EXAMPLE1 = """
            11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
            1698522-1698528,446443-446449,38593856-38593862,565653-565659,
            824824821-824824827,2121212118-2121212124
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day02.solve(EXAMPLE1)).isEqualTo(1_227_775_554L);

        Path path = Path.of(Objects.requireNonNull(Day02.class.getResource("Day02.txt")).toURI());
        assertThat(Day02.solve(Files.readString(path))).isEqualTo(38_310_256_125L);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day02.solve2(EXAMPLE1)).isEqualTo(4_174_379_265L);

        Path path = Path.of(Objects.requireNonNull(Day02.class.getResource("Day02.txt")).toURI());
        assertThat(Day02.solve2(Files.readString(path))).isEqualTo(58_961_152_806L);
    }

    @Test
    void testNext() {
        assertThat(Day02.getNext(10)).isEqualTo(11);
        assertThat(Day02.getNext(11)).isEqualTo(22);
        assertThat(Day02.getNext(95)).isEqualTo(99);
        assertThat(Day02.getNext(998)).isEqualTo(1010);
        assertThat(Day02.getNext(1188511880)).isEqualTo(1188511885);
        assertThat(Day02.getNext(123)).isEqualTo(1010);
        assertThat(Day02.getNext(5446793)).isEqualTo(10001000);
    }

    @Test
    void testPrev() {
        assertThat(Day02.getPrev(23)).isEqualTo(22);
        assertThat(Day02.getPrev(115)).isEqualTo(99);
        assertThat(Day02.getPrev(1012)).isEqualTo(1010);
        assertThat(Day02.getPrev(1188511890)).isEqualTo(1188511885);
        assertThat(Day02.getPrev(5446793)).isEqualTo(999999); // 10001

    }
}
