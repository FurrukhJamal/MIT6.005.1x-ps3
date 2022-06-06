package library;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Test suite for BookCopy ADT.
 */
public class BookCopyTest {

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
        BookCopy copy = new BookCopy(book);
        assertEquals(book, copy.getBook());
    }
    
    @Test
    public void testCopyBookTwoBooks() {
    	Book book = new Book("Test Title", Arrays.asList("Furrukh Jamal", "Tun Khan"), 1985);
    	BookCopy copy1 = new BookCopy(book);
    	BookCopy copy2 = new BookCopy(book);
    	
    	assertEquals("copy1 refers to original book", copy1.getBook(), book);
    	assertEquals("copy2 refers to original book", copy2.getBook(), book);
    	assertTrue("copy1 and copy2 refers to original book", copy1.getBook().equals(copy2.getBook()));
    }
    @Test 
    public void testCopyBookOldAndGoodPointsToSameBook() {
    	Book book = new Book("Test Title", Arrays.asList("Furrukh Jamal", "Tun Khan"), 1985);
    	BookCopy copy1 = new BookCopy(book);
    	BookCopy copy2 = new BookCopy(book);
    	
    	copy1.setCondition(BookCopy.Condition.DAMAGED);
    	
    	assertEquals("Conditions changed", copy1.getCondition(), BookCopy.Condition.DAMAGED);
    	
    	assertTrue("Damaged and new Books still point to correct originaal", copy1.getBook().equals(copy2.getBook()));
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
