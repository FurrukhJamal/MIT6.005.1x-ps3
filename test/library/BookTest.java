package library;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for Book ADT.
 */
public class BookTest {

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals("This Test Is Just An Example", book.getTitle());
    }
    
    @Test 
    public void TestBookOneAuthor() {
    	Book book = new Book("Test title", Arrays.asList("Furrukh"), 1985);
    	assertEquals("Test title", book.getTitle());
    	assertTrue("Furrukh is in authors", book.getAuthors().contains("Furrukh"));
    	assertEquals(1985, book.getYear());
    }
    
    @Test 
    public void TestBookMultipleAuthors() {
    	String author1 = "Furrukh";
    	String author2 = "Jamal";
    	String author3 = "Waleed";
    	
    	Book book = new Book("Test Title", Arrays.asList(author1, author2, author3), 1985);
    	assertEquals("Test Title", book.getTitle());
    	assertEquals("Expected size of authors", 3, book.getAuthors().size());
    	assertTrue("All authors are there", book.getAuthors().containsAll(Arrays.asList(author1, author2, author3)));
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
