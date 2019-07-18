package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {
	MathUtils mathUtils;
	
	// we can use TestInfo and TestReporter for logging or print messages on console
	TestInfo testInfo;
	TestReporter testReporter;

	/**
	 * @author mohammad.arif This method must be static for
	 *         TestInstance.Lifecycle.PER_METHOD and can be non static for
	 *         TestInstance.Lifecycle.PER_CLASS
	 */
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}

	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("initializing .....");
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
	}

	@AfterEach
	void cleanup() {
		System.out.println("cleaning up .....");
	}
	
	@Nested
	@Tag("math") // we can provide tagging information either by eclipse configuration or by maven plugin
	class AddTest{
		@Test
		@DisplayName("Testing add method for +")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1, 1), "the add method should add two no");
		}
		
		@Test
		@DisplayName("Testing add method for -")
		void testAddNegative() {
			assertEquals(-2, mathUtils.add(-1, -1), "the add method should add two no");
		}
	}
	
	/**
	 * @author mohammad.arif 
	 * assumeTrue(false) is same as disabled but it provides programmatic controls on method execution
	 */
	@Test
	@DisplayName("divide method")
	@Tag("math") // we can provide tagging information either by eclipse configuration or by maven plugin
	void testDevide() {
		assumeTrue(false);
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw");
	}
	
	@RepeatedTest(5)
	@Tag("math") // we can provide tagging information either by eclipse configuration or by maven plugin
	void testDevideRepeate(RepetitionInfo repetitionInfo) {
		int count = repetitionInfo.getCurrentRepetition();
		if (count%2==0) 
			assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw");
		else
			assertThrows(NullPointerException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw");
	}
	
	@Test
	@Tag("math") // we can provide tagging information either by eclipse configuration or by maven plugin
	void testMultiply() {
		assertAll(
				()->assertEquals(4, mathUtils.multiply(2, 2)),
				()->assertEquals(-2, mathUtils.multiply(-1, 2)),
				()->assertEquals(0, mathUtils.multiply(0, 2))
				);
	}

	@Test
	@Tag("circle") // we can provide tagging information either by eclipse configuration or by maven plugin
	void testComputeCricleArea() {
		// here message is eager i.e. each time string create either test passes or fails.
		assertEquals(314.1592653589793, mathUtils.computeCricleArea(10), 
				"should return area "+314.1592653589793+" but actual "+mathUtils.computeCricleArea(10));
		
		// here message is lazy i.e. string create only when test result passes.
		assertEquals(314.1592653589793, mathUtils.computeCricleArea(100), 
				()->"should return area "+314.1592653589793+" but actual "+mathUtils.computeCricleArea(100));
	}
	
	@Test
	@DisplayName("method should not run")
	@Disabled
	void testDisabled() {
		fail("This test should be disabled");
	}

}
