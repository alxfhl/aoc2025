package alxfhl.aoc2025;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class Day11Test {

    public static final String EXAMPLE1 = """
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out
            """;
    public static final String EXAMPLE2 = """
            svr: aaa bbb
            aaa: fft
            fft: ccc
            bbb: tty
            tty: ccc
            ccc: ddd eee
            ddd: hub
            hub: fff
            eee: dac
            dac: fff
            fff: ggg hhh
            ggg: out
            hhh: out
            """;

    @Test
    void part1() throws IOException, URISyntaxException {
        assertThat(Day11.solve(EXAMPLE1)).isEqualTo(5);

        Path path = Path.of(Day11.class.getResource("Day11.txt").toURI());
        assertThat(Day11.solve(Files.readString(path))).isEqualTo(413);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        assertThat(Day11.solve2(EXAMPLE2)).isEqualTo(2);

        Path path = Path.of(Day11.class.getResource("Day11.txt").toURI());
        assertThat(Day11.solve2(Files.readString(path))).isEqualTo(525_518_050_323_600L);
    }
}
