package kopher.hellospring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortTest {

    Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
    }

    @Test
    void sort() {
        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // 검증 (then)
        assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    void sort3Items() {
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}
