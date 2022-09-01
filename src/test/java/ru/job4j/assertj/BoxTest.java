package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void thisIsUnknown() {
        Box box = new Box(10, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");

    }

    @Test
    void getNumberOfVerticesIf0() {
        Box box = new Box(0, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(0);
    }

    @Test
    void getNumberOfVerticesIf10() {
        Box box = new Box(10, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(-1);
    }

    @Test
    void isExist() {
        Box box = new Box(0, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(-1, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void getAreaIfVertexIs8() {
        Box box = new Box(8, 1);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(6d, withPrecision(0.1d));
    }

    @Test
    void getAreaIfVertexIs10() {
        Box box = new Box(10, 1);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(0, withPrecision(0.1d));
    }
}