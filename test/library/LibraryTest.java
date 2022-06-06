package library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test suite for Library ADT.
 */
@RunWith(Parameterized.class)
public class LibraryTest {

    /*
     * Note: all the tests you write here must be runnable against any
     * Library class that follows the spec.  JUnit will automatically
     * run these tests against both SmallLibrary and BigLibrary.
     */

    /**
     * Implementation classes for the Library ADT.
     * JUnit runs this test suite once for each class name in the returned array.
     * @return array of Java class names, including their full package prefix
     */
    @Parameters(name="{0}")
    public static Object[] allImplementationClassNames() {
        return new Object[] { 
            "library.SmallLibrary", 
            "library.BigLibrary"
        }; 
    }

    /**
     * Implementation class being tested on this run of the test suite.
     * JUnit sets this variable automatically as it iterates through the array returned
     * by allImplementationClassNames.
     */
    @Parameter
    public String implementationClassName;    

    /**
     * @return a fresh instance of a Library, constructed from the implementation class specified
     * by implementationClassName.
     */
    public Library makeLibrary() {
        try {
            Class<?> cls = Class.forName(implementationClassName);
            return (Library) cls.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * buy : a new copy of a book
     * checkout : Check book to be avaiable first then checked out
     * checkin : opposite of checkout
     * isAvaliable : check for two books, true, false
     * allcopies : zero book, > 1 books
     * availablecopies : zero, available, > 1 available
     * find : query not found, single query found, mulitple queries, result by year
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    
    
    @Test
    public void testExampleTest() {
        Library library = makeLibrary();
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals(Collections.emptySet(), library.availableCopies(book));
    }
    
    
    /*Tests for Lose*/
    @Test
    public void testLose() {
    	Library library = makeLibrary();
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        BookCopy copy1 = library.buy(book);
        BookCopy copy2 = library.buy(book);
        BookCopy copy3 = library.buy(book);
        
        library.lose(copy1);
        Set<BookCopy> allcopies = library.allCopies(book);
        assertEquals("Expected size of books returned", 2 , allcopies.size());
        assertTrue("Book contains copy2", allcopies.contains(copy2));
        assertTrue("Book contains copy3", allcopies.contains(copy3));
        
        library.lose(copy3);
        library.lose(copy2);
        allcopies = library.allCopies(book);
        
        assertTrue("Returned an empty set when all books lost", allcopies.isEmpty());
    }
    
    
    
    /*Tests for find*/
    @Test
    public void testFindNoBook() {
    	Library library = makeLibrary();
    	List<Book> searchedBooks = library.find("some book");
    	assertTrue("searched a non exsistant book", searchedBooks.isEmpty());
    	
    	
    }
    @Test
    public void testFindNoBookinLibraryWithBook() {
    	Library library = makeLibrary();
    	
    	Book book = new Book("This is a title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy book1 = library.buy(book);
    	
    	List<Book> searchedBooks = library.find("word not in book");
    	assertTrue("no book was returned", searchedBooks.isEmpty());
    }
    
    @Test
    public void testFindExactMatchinTitle() {
    	Library library = makeLibrary();
    	
    	Book originalBook1 = new Book("This is a title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	Book originalBook2 = new Book("Another one", Arrays.asList("Furrukh", "Jamal"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	
    	List<Book> searchedBooks = library.find("This is a title");
    	assertEquals("expected size is true", 1 , searchedBooks.size());
    	assertTrue("expected book in the retuned books", searchedBooks.contains(originalBook1));
    }
    
    @Test
    public void testFindExactMatchinAuthor() {
    	Library library = makeLibrary();
    	
    	Book originalBook1 = new Book("This is a title", Arrays.asList("Furrukh Jamal"), 1985);
    	Book originalBook2 = new Book("Another one", Arrays.asList("Jim", "Jama"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	
    	List<Book> searchedBooks = library.find("Furrukh Jamal");
    	assertEquals("expected size is true", 1 , searchedBooks.size());
    	assertTrue("expected book in the returned books", searchedBooks.contains(originalBook1));
    }
    
    @Test
    public void testFindMatchinTitleAndAuthor() {
    	Library library = makeLibrary();
    	
    	Book originalBook1 = new Book("Furrukh", Arrays.asList("Furrukh"), 1985);
    	Book originalBook2 = new Book("Another one", Arrays.asList("Jim", "Furrukh"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	
    	List<Book> searchedBooks = library.find("Furrukh");
    	System.out.println("Returned Result in Test is: " +searchedBooks);
    	assertEquals("Expected size returned", 2 , searchedBooks.size());
    	assertEquals("number of matches is given preference", originalBook1, searchedBooks.get(0));
    	assertEquals("less number of matches is below a higher number of match", originalBook2, searchedBooks.get(1));
    }
    
    
    @Test
    public void testFindSameTitelAndAuthor() {
    	Library library = makeLibrary();
    	
    	Book originalBook1 = new Book("My Life", Arrays.asList("Furrukh"), 1985);
    	Book originalBook2 = new Book("My Life", Arrays.asList("Furrukh"), 1999);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	
    	List<Book> searchedBooks = library.find("Furrukh");
    	assertEquals("Expected size returned", 2 , searchedBooks.size());
    	assertEquals("recent date released is given preference", originalBook2, searchedBooks.get(0));
    	assertEquals("old published book at the bottom", originalBook1, searchedBooks.get(1));
    }
    
    
    
    
    
    
    
    
//    
    
    /*Tests for availableCopies*/
    @Test
    public void testAvailableCopiesNoneAvailable() {
    	Library library = makeLibrary();
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	
    	BookCopy book1 = library.buy(book);
    	BookCopy book2 = library.buy(book);
    	BookCopy book3 = library.buy(book);
    	
    	library.checkout(book1);
    	library.checkout(book2);
    	library.checkout(book3);
    	
    	Set<BookCopy> availableBooks = library.availableCopies(book);
    	assertTrue("No books available", availableBooks.isEmpty());
    	assertEquals("size of no books is 0", 0, availableBooks.size());
    }
   
    
    @Test
    public void testAvailableCopiesOneBookAvailable() {
    	Library library = makeLibrary();
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	
    	BookCopy book1 = library.buy(book);
    	BookCopy book2 = library.buy(book);
    	BookCopy book3 = library.buy(book);
    	
    	library.checkout(book1);
    	library.checkout(book2);
    	    	
    	Set<BookCopy> availableBooks = library.availableCopies(book);
    	assertEquals("Correct size of returned books", 1, availableBooks.size());
    	assertTrue("Correct book returned", availableBooks.contains(book3));
    }
    
    
    
    
    /*Tests for allcopies*/
    @Test
    public void testAllCopiesEmptyLibrary() {
    	Library library = makeLibrary();
    	
    	Book book1 = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	Set<BookCopy> allBooks = library.allCopies(book1);
    	assertTrue("returned an empty set", allBooks.isEmpty());
    	assertEquals(allBooks, Collections.emptySet());
    }
    
    
    @Test 
    public void testAllCopiesMultipleBooks() {
    	Library library = makeLibrary();
    	
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy book1 = library.buy(book);
    	BookCopy book2 = library.buy(book);
    	BookCopy book3 = library.buy(book);
    	
    	Set<BookCopy> books = new HashSet<BookCopy>() ;
    		books.add(book1);
    		books.add(book2);
    		books.add(book3);
    	
    	
    	Set<BookCopy> allBooks = library.allCopies(book);
    	assertEquals("The returned number of books is correct", 3, allBooks.size());
    	assertTrue("Correct books returned", allBooks.containsAll(books));
    }
    
    
    
    @Test
    public void testIsAvailable() {
    	Library library = makeLibrary();
    	
    	Book book1 = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy libraryBook = library.buy(book1);
    	
    	Book book2 = new Book("Another Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy copyBook2 = new BookCopy(book2);
    	
    	assertTrue("Bought book by library return true in isAvailable", library.isAvailable(libraryBook));
    	assertFalse("Not Bought book by library returns false in isAvailable", library.isAvailable(copyBook2));
    }
    
    
    @Test
    public void testCheckIn() {
    	Library library = makeLibrary();
    	
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy libraryBook = library.buy(book);
    	library.checkout(libraryBook);
    	assertFalse("The library book is checked out", library.isAvailable(libraryBook));
    	library.checkin(libraryBook);
    	assertTrue("The library book is available again", library.isAvailable(libraryBook));
    }
    
    
    
    
    @Test 
    public void testCheckOut() {
    	Library library = makeLibrary();
    	
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy libraryBook = library.buy(book);
    	assertTrue("the book is available", library.isAvailable(libraryBook));
    	//assertTrue("the book is avaialable", true);
    	library.checkout(libraryBook);
    	assertFalse("The library book is checkedout", library.isAvailable(libraryBook));
    	
    }
    
    /*Buy*/
    
    @Test
    public void testBuyMoreBooks() {
    	Library library = makeLibrary();
    	
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy libraryBook = library.buy(book);
    	BookCopy libraryBook2 = library.buy(book);
    	BookCopy libraryBook3 = library.buy(book);
    	
    	assertEquals("All books were added in the library", 3 , library.allCopies(book).size());
    }
    
    @Test
    public void testBuy() {
    	Library library = makeLibrary();
    	
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy libraryBook = library.buy(book);
    	assertEquals("New book bought", libraryBook.getBook(), book);
    	assertEquals("New in condition", libraryBook.getCondition(), BookCopy.Condition.GOOD);
    }
    
    @Test
    public void testBuyForLargeLibrary() {
    	Library library = makeLibrary();
    	
    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	BookCopy libraryBook = library.buy(book);
    	BookCopy libraryBook2 = library.buy(book);
    	
    	assertEquals("New book bought", libraryBook.getBook(), book);
    	assertEquals("New in condition", libraryBook.getCondition(), BookCopy.Condition.GOOD);
    	//assertEquals("expected size of books in inLibrary set or map key's val", 2 , library.)
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
