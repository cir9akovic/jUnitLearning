package rs.cir9akovic;

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
import org.junit.jupiter.api.TestInstance;

/*
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS)  // Create one instance per class and if this is a case
 * 														we can create instance in @BeforeAll and then @BeforeAll don't 							
 *  													need to be static.
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD) // DEFAULT - create instance of a class before each test
class MyMathTest {
	
	/* - assertEquals 
	 *	 Asserts that expected and actual are equal
	 *
	 * - assertArrayEquals
	 * 	 Verifies each item in the arrays are equal in the right position
	 * 
	 * - assertIterableEquals
	 * 	 Verifies each item in the iterable are equal in the corresponding positions
	 * 
	 * - assertNotEquals
	 *   Assert that expected and actual are not equal
	 * 
	 * - assertFalse
	 *   Assert that the supplied condition is false
	 *   
	 * - assertNotNull
	 * 	 if it is Null test fails
	 */

	MyMath myMath;
	
	
	// This must be static because don't have a class to run a method. It is the same for @AfteAll 
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This is run before all");
	}
	
	@BeforeEach
	void init() {

		// Create new instance before every test method
		myMath = new MyMath();
	}
	
	@AfterEach
	void cleanup() {
		System.out.println("This run afte each test!");
	}
	
	@Nested // Grouping tests
	@DisplayName("add method")// Show this name in jUnit-Tab for easier readability 
	@Tag("Math")
	class AddTest{
		
		@Test
		@DisplayName("Testing when adding two positive number!") 
		void addPositiveTest() {
			
			// jUnit assert method for equals, use LAMBDA for showing message if fail.
			assertEquals(2, myMath.add(1, 1), () -> "This msg show if test fail in test result");
		}
		
		
		@Test
		@DisplayName("Testing when adding two negative number!") 
		void addNegativeTest() {
			
			// jUnit assert method for equals
			assertEquals(-2, myMath.add(-1, -1), () -> "This msg show if test fail in test result");
		}
		
	}
	
	
	@Test
	@DisplayName("Multiply method")
	@Tag("Math")
	void testMultiply() {
		
		// More in one with Lambda executable
		assertAll(
				() -> assertEquals(4, myMath.multiply(2, 2)),
				() -> assertEquals(0, myMath.multiply(2, 0)),
				() -> assertEquals(-2, myMath.multiply(2, -1))
				);
		
	}
	
	@Test
	@Tag("Math") // Group test with tag notation
	void divideTest() {
		
		// ASSUME
		assumeTrue(true); // Run this test only if something is true, otherwise skip it.
		
		assertThrows(ArithmeticException.class, () -> myMath.divide(1, 0),() ->  "Invide by zero should throw");
	}
	
	@RepeatedTest(3) // Run this test 3 times. Can add arg about repetition
	@Tag("Circle")
	void computeCircleRadiusTest(RepetitionInfo repetitionInfo) {
		
		// repetitionInfo.getCurrentRepetition()
		// repetitionInfo.getTotalRepetitions()
		assertEquals(314.1592653589793, myMath.computeCircleArea(10),() ->  "Should return right circle area");
	}
	
	@Test
	@Disabled // Disable - skip the test and don't run it. This is good for TestDD.
	@DisplayName("Testing disabled test!") // Show this name in jUnit-Tab for easier readability 
	void testDisabled() {
		fail("This test should be disabled");
	}
	
	
	/*  We can control from outside which test will be run.
	 * 
	 *  @EnabledOnOs(OS.LINUX) - run test only on Linux
	 *  @EnabledOnJre(JRE.JAVA_11) - run test only if java version is 11
	 *  @EnabledIf
	 *  @EnabledIfSystemProperty ---> named = "os.arch", matches = ".*64.*"
	 *  @EnabledIfEnvironmentVariable ---> named = "ENV", matches = ".*development.*"
	 *  
	 */

}
