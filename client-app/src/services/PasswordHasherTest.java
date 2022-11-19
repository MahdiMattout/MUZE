/**
 * 
 */
package services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author mahdimattout
 *
 */
class PasswordHasherTest {

	@SuppressWarnings("deprecation")
	@Test
	static void test() {
		PasswordHasher p = new PasswordHasher();
		String password = "This is a test";
		String hash = p.hashPassword(password);
		String testString = p.hashPassword("This is a test");
		Assert.assertEquals(hash, testString);
	}
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
