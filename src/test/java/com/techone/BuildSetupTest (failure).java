package com.techone;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SETUP-02 pipeline smoke test.
 *
 * <p>Checks that the build runs JUnit tests on the Java version the project
 * targets. This is a check of the build setup, not a TDD cycle for a business
 * rule.
 */
class BuildSetupTest {

    @Test
    @DisplayName("SETUP-02.AC3: tests run on Java 21 or newer")
    void testsRunOnJava21OrNewer() {
        int feature = Runtime.version().feature();
        assertTrue(feature >= 21, // <<< Fixed the value back to 21, finalizing the CI test
                () -> "Expected Java 21 or newer, but the tests ran on Java " + Runtime.version());
    }
}
