package com.apricode.omby.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("@Before each test");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After each test");
	}

	@Test
	public void test() {
		
		System.out.println("DAO tests starterted");
		assert (true);
	}

}
