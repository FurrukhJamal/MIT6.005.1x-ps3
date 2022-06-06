package library;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Test suite for BigLibrary's stronger specs.
 */
public class BigLibraryTest {
    
    /* 
     * NOTE: use this file only for tests of BigLibrary.find()'s stronger spec.
     * Tests of all other Library operations should be in LibraryTest.java 
     */

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for BigLibrary.find() should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    
	@Test
    public void testFindOneBookwithTwoMatchesOneBookWithOneMatch() {
    	Library library = new BigLibrary();
    	
    	Book originalBook1 = new Book("This is a title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	Book originalBook2 = new Book("This Another one", Arrays.asList("Furrukh", "Jamal"), 1985);
    	Book originalBook3 = new Book("Fake one", Arrays.asList("Furrukh", "Jamal"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	BookCopy book3 = library.buy(originalBook3);
    	
    	List<Book> searchedBooks = library.find("This is");
    	assertEquals("Expected size in searchedBooks is returned", 2 , searchedBooks.size());
    	assertEquals("Contains the first expexcted book", originalBook1, searchedBooks.get(0));
    	assertEquals("Contains the expexcted book", originalBook2, searchedBooks.get(1));
    	
    }
	
	
	@Test 
    public void testFindauthorMatch() {
    	Library library = new BigLibrary();
    	
    	Book originalBook1 = new Book("This is a title", Arrays.asList("Furrukh"), 1985);
    	Book originalBook2 = new Book("This Another one", Arrays.asList("Furrukh"), 1985);
    	Book originalBook3 = new Book("Fake one", Arrays.asList("Furrukh", "Jamal"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	BookCopy book3 = library.buy(originalBook3);
    	
    	List<Book> searchedBooks = library.find("Jamal");
    	assertEquals("Expected book returned based on the author", originalBook3 , searchedBooks.get(0));
    	assertEquals("Expected size returned", 1 , searchedBooks.size());
    }
    
    @Test 
    public void testFindOneMatchFromTitleOneMatchFromAuthor() {
    	Library library = new BigLibrary();
    	
    	Book originalBook1 = new Book("This is a title", Arrays.asList("Another"), 1985);
    	Book originalBook2 = new Book("This Another one", Arrays.asList("Furrukh"), 1986);
    	Book originalBook3 = new Book("Fake one", Arrays.asList("Furrukh", "Jamal"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	BookCopy book3 = library.buy(originalBook3);
    	
    	List<Book> searchedBooks = library.find("Another");
    	assertEquals("Expected number of books returned", 2 , searchedBooks.size());
    	assertEquals("Expected first element in searched book", originalBook2, searchedBooks.get(0));
    	assertEquals("Expected first element in searched book", originalBook1, searchedBooks.get(1));
    	
    }
	
	
	@Test
    public void testExampleTest() {
        // this is just an example test, you should delete it
        Library library = new BigLibrary();
        assertEquals(Collections.emptyList(), library.find("This Test Is Just An Example"));
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
