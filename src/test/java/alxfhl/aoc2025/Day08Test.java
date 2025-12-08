package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day08Test {

    public static final String EXAMPLE1 = """
            162,817,812
            57,618,57
            906,360,560
            592,479,940
            352,342,300
            466,668,158
            542,29,236
            431,825,988
            739,650,466
            52,470,668
            216,146,977
            819,987,18
            117,168,530
            805,96,715
            346,949,466
            970,615,88
            941,993,340
            862,61,35
            984,92,344
            425,690,689
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day08.solve(EXAMPLE1, 10, 3)).isEqualTo(40);

        Path path = Path.of(Day08.class.getResource("Day08.txt").toURI());
        assertThat(Day08.solve(Files.readString(path), 1000, 3)).isEqualTo(83_520);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day08.solve2(EXAMPLE1)).isEqualTo(25272);

        Path path = Path.of(Day08.class.getResource("Day08.txt").toURI());
        assertThat(Day08.solve2(Files.readString(path))).isEqualTo(1_131_823_407);
    }
}
