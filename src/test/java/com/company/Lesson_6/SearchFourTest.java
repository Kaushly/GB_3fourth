package com.company.Lesson_6;

import org.junit.Assert;
import org.junit.Test;


public class SearchFourTest {

    @Test
    void shouldRuntimeExceptionInMethodSearchFour() {
        try {
            SearchFour.newArray(new int[]{1, 2, 3, 5});
        } catch (RuntimeException e) {
            Assert.fail("Error: RuntimeException because there is no number four");
        }
    }

    @Test
    void shouldSuccessfullyForNumberFourInArray() {
        Assert.assertArrayEquals(new int[]{1, 7}, SearchFour.newArray(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test
    void shouldNullOutputArray() {
        Assert.assertNull("Output array is empty", SearchFour.getList());
    }

}
