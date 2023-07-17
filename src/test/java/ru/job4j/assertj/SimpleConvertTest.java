package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> result = simpleConvert.toList(new String[]{"first", "second", "three", "four", "five"});
        assertThat(result).hasSize(5)
                .contains("second", "three")
                .containsOnly("three", "four", "first", "second", "five")
                .containsExactly("first", "second", "three", "four", "five")
                .anySatisfy(e -> assertThat(e).contains("se"));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> result = simpleConvert.toSet(new String[]{"first", "second", "three", "four", "five"});
        assertThat(result).hasSize(5)
                .containsAnyOf("second", "thousand")
                .doesNotContain("six")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .allSatisfy(e -> assertThat(e).doesNotEndWith("ty"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> result = simpleConvert.toMap(new String[]{"first", "second", "three", "four", "five"});
        assertThat(result).hasSize(5)
                .containsKeys("first", "second")
                .containsValues(1, 2, 3)
                .doesNotContainKey("six")
                .doesNotContainValue(10)
                .containsEntry("second", 1);
    }
}