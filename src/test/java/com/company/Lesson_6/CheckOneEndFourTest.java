package com.company.Lesson_6;

import org.junit.Assert;
import org.junit.Test;

public class CheckOneEndFourTest {

    @Test
    public void shouldArrayOnlyOneEndFour() {
        Assert.assertTrue("Error: array not only one end four", CheckOneEndFour.oneEndFour(new int[]{1, 4}));
    }

    @Test
    public void shouldArrayNotJustOneEndFour() {
        Assert.assertTrue("Error: array not only one end four", CheckOneEndFour.oneEndFour(new int[]{1, 2, 4}));
    }
}
