package examples;

import org.testng.Assert;
import org.testng.annotations.*;

public class SimpleTest {

    @BeforeClass
    public void setUp() {
        System.out.println("setUp");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("tearDown");
    }

    @BeforeTest
    public void beforeEachTest () {
        System.out.println(" << Call before each test >>");
    }

    @AfterTest
    public void afterEachTest () {
        System.out.println(" << Call after each test >>");
    }

    @BeforeMethod
    public void beforeEachTestMetchod () {
        System.out.println("     --> Call before each test method **");
    }

    @AfterMethod
    public void afterEachTestMetchod () {
        System.out.println("     <-- Call after each test method **");
    }

    // @Test(groups = { "first" })
    @Test(description="Test 1 description")
    public void FirstTest() {
        System.out.println("First test");
    }

    // @Test(groups = { "sends" })
    @Test(description="Test 2 description")
    public void SecondTest() {
        System.out.println("Second test");
    }

    // @Test(groups = { "sends" })
    @Test(description="Test 3 description")
    public void ThirdTest() {
        System.out.println("Second test");
    }

    @Test(description="Test that should failed", enabled = false)
    public void assertFailureTest() {
        // Comparing two different Strings
        Assert.assertEquals("testng", "java");
    }
}
