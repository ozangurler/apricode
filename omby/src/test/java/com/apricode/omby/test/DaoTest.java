package com.apricode.omby.test;

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
	public void test1() {
		
		System.out.println("DAO1 tests starterted");
		assert (true);
	}
	
	
	@Test
	public void test2() {
		
		System.out.println("DAO2 tests starterted");
		assert (true);
	}

	

}
