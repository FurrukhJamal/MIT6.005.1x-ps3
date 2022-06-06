package library;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BigLibrary represents a large collection of books that might be held by a city or
 * university library system -- millions of books.
 * 
 * In particular, every operation needs to run faster than linear time (as a function of the number of books
 * in the library).
 */
public class BigLibrary implements Library {

    // TODO: rep
//    private final Map<Book, Set<BookCopy>> inLibrary;
//    private final Map<Book, Set<BookCopy>> checkedOut;
    
    private final Map<Book, List<BookCopy>> inLibrary;
    private final Map<Book, List<BookCopy>> checkedOutList;
    
    // TODO: rep invariant
    /*An intersection of collections of books inLibrary and checkout for a book would be an empty set provided there is atleast one book in the library
     * */
    
    // TODO: abstraction function
    /*The library represent a collection of books where each union of similar keys in 
     * inLibrary and checkout represent the collection of books, if the book key is not their the book is not in the library*/
    
    // TODO: safety from rep exposure argument
    /*Everything is private*/
    
    public BigLibrary() {
        //throw new RuntimeException("not implemented yet");
    	this.inLibrary = new HashMap<>();
    	this.checkedOutList = new HashMap<>();
    	    	
    	
    	this.checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
        //throw new RuntimeException("not implemented yet");
    	Set <Book> allKeysinLibrary = this.inLibrary.keySet();
    	if(allKeysinLibrary.size() > 0) {
    		//get one book
    		Book book = allKeysinLibrary.iterator().next(); 
    		if(checkedOutList.containsKey(book))
    		{
    			//assert this.inLibrary.get(book).containsAll(this.checkedOutList.get(book)) == false : "rep invariat voilated";
    		}
    	}
    	
    }

    @Override
    public BookCopy buy(Book book) {
        //throw new RuntimeException("not implemented yet");
    	BookCopy copy = new BookCopy(book);
    	if(this.inLibrary.containsKey(book))
    	{
    		this.inLibrary.get(book).add(copy);
    	}
    	else
    	{
    		List<BookCopy> listForThisBook = new ArrayList<>();
        	listForThisBook.add(copy);
        	this.inLibrary.put(book, listForThisBook);
    	}
    	
    	this.checkRep();
    	return copy;
    }
    
    @Override
    public void checkout(BookCopy copy) {
       // throw new RuntimeException("not implemented yet");
    	if(this.isAvailable(copy))
    	{
    		Book book = copy.getBook();
    		//BookCopy issuedBook = this.inLibrary.get(book).remove(this.inLibrary.get(book).size() - 1);
    		int indexOfCheckedOutBook = this.inLibrary.get(book).indexOf(copy);
    		BookCopy issuedBook = this.inLibrary.get(book).remove(indexOfCheckedOutBook);
    		
    		if(!this.checkedOutList.containsKey(book))
    		{
    			List<BookCopy> checkedOutListForThisBook = new ArrayList<>();
    			checkedOutListForThisBook.add(issuedBook);
    			this.checkedOutList.put(book, checkedOutListForThisBook);
    		}
    		else
    		{
    			this.checkedOutList.get(book).add(issuedBook);
    		}
    		
    		
    	}
    	this.checkRep();
    	
    }
    
   
    
    @Override
    public void checkin(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
    	Book book = copy.getBook();
    	if(this.checkedOutList.containsKey(book) && this.checkedOutList.get(book).size() > 0)
    	{
    		int index = this.checkedOutList.get(book).lastIndexOf(copy);
    		BookCopy checkedOutBook = this.checkedOutList.get(book).remove(index);
    		//add to inLibrary
    		this.inLibrary.get(book).add(checkedOutBook);
    	}
    	//this.checkRep();
    }
    
    @Override
    public Set<BookCopy> allCopies(Book book) {
        //throw new RuntimeException("not implemented yet");
    	Set<BookCopy> allCopies = new HashSet<>();
    	if(this.inLibrary.containsKey(book))
    	{
    		for(BookCopy Bookcopy : this.inLibrary.get(book))
    		{
    			//System.out.println(Bookcopy);
    			allCopies.add(Bookcopy);
    			//System.out.println("Set" +allCopies);
    		}
    	}
    	
    	if(this.checkedOutList.containsKey(book))
    	{
    		for(BookCopy Bookcopy : this.checkedOutList.get(book))
    		{
    			allCopies.add(Bookcopy);
    		}
    	}
    	
    	return allCopies;
    }

    @Override
    public Set<BookCopy> availableCopies(Book book) {
        //throw new RuntimeException("not implemented yet");
    	Set<BookCopy> availableCopies = new HashSet<>();
    	if(this.inLibrary.containsKey(book))
    	{
    		for(BookCopy copy : this.inLibrary.get(book))
    		{
    			availableCopies.add(copy);
    		}
    	}
    	
    	return availableCopies;
    }
    
    @Override
    public boolean isAvailable(BookCopy copy) {
       // throw new RuntimeException("not implemented yet");
    	Book book = copy.getBook();
    	if(this.inLibrary.containsKey(book) && this.inLibrary.get(book).size() > 0)
    	{
    		return true;
    	}
    	return false;
    }
    
    @Override
    public List<Book> find(String query) {
        //throw new RuntimeException("not implemented yet");
    	
    	Map<Book, Integer> testResult = new HashMap<>();
    	
    	List<Book> result = new ArrayList<>();
    	Set<Book> availableBooks = this.inLibrary.keySet();
    	for(Book book : availableBooks)
    	{
    		// for exact matches
    		if(book.getTitle() == query)
    		{
    			for(String author : book.getAuthors())
    			{
    				if(author == query)
    				{
    					result.add(result.isEmpty() ? 0 : result.size() - 1,book);
    				}
    			}
    			
    			if(!result.contains(book))
    			{
    				result.add(result.isEmpty() ? 0 : result.size() - 1,book);
    			}
    		}
    		else
    		{
    			for(String author : book.getAuthors())
    			{
    				if(author == query)
    				{
    					result.add(result.isEmpty() ? 0 : result.size() - 1,book);
    				}
    			}
    		}
    		
    		//for some words matching matching 
    		//converting the query in to an array
    		
    		//System.out.println("Query : " +query);
    		List<String> queryList = new ArrayList<String>(Arrays.asList(query.split(" ")));
    		List<String> titleAsList = new ArrayList<String>(Arrays.asList(book.getTitle().split(" ")));
    		int counter = 0;
    		for(String queryWord : queryList) {
    			System.out.println("queryWord: " +queryWord);
    			for(String wordInTitle : titleAsList) {
    				System.out.println("Single word in title: " +wordInTitle);
    				if(queryWord.equals(wordInTitle)) {
    					System.out.println("queryWord == wordInTitle");
    					counter += 1;
    					testResult.put(book, counter);
    					break;
    				}
    			}
    			
    			for (String author : book.getAuthors()) {
    				//converting author as List
    				List<String> eachAuthor = new ArrayList<String>(Arrays.asList(author.split(" ")));
    				for(String eachWordinAuthor : eachAuthor) {
    					if(eachWordinAuthor.equals(queryWord)) {
    						counter += 1;
    						testResult.put(book, counter);
    					}
    				}
    			}
    		}
    		
    		
    	}
    	
    	System.out.println("testResult: " +testResult);
    	List<Book> intermediateState = new ArrayList<>();
    	for(Book book : testResult.keySet()) {
    		intermediateState.add(book);
    		
    	}
    	System.out.println("intermediateState before sort" +intermediateState);
    	
    	intermediateState.sort(new Comparator<Book>() {
    		@Override public int compare(Book a, Book b) {
    			System.out.println("Inside compare of Comparator");
    			if(testResult.get(b) != testResult.get(a))
    			{
    				System.out.println("inside if of comparator");
    				return testResult.get(b).compareTo(testResult.get(a));
    			}
    			return +b.getYear() - a.getYear();
    			
    		}
    	}); 
    	
    	System.out.println("intermediateState After sort" +intermediateState);
    	
    	//THE IF ELSE IS A BUG FIX FOR test#1 and test#4
    	if(result.size() > 0)
    	{
    		for(Book Book : intermediateState)
        	{
        		System.out.println("Book in loop:" +Book);
        		if(!result.contains(Book))
        		{
        			result.add(result.isEmpty() ? 0 : result.size() - 1, Book);
        		}
        	}
    	}
    	else
    	{
    		result.addAll(intermediateState);
    	}
//    	for(Book Book : intermediateState)
//    	{
//    		System.out.println("Book in loop:" +Book);
//    		if(!result.contains(Book))
//    		{
//    			result.add(result.isEmpty() ? 0 : result.size() - 1, Book);
//    		}
//    	}
    	//result.addAll(intermediateState);
    	System.out.println("Result before return" +result);
    	 
    	return result;
    }
    
    
    
    @Override
    public void lose(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
    	Book book = copy.getBook();
    	if(this.inLibrary.containsKey(book))
    	{
    		if(this.inLibrary.get(book).contains(copy))
    		{
    			int index = this.inLibrary.get(book).indexOf(copy);
    			this.inLibrary.get(book).remove(index);
    			if(this.inLibrary.get(book).size() == 0)
    			{
    				this.inLibrary.remove(book);
    			}
    		}
    	}
    	if(this.checkedOutList.containsKey(book))
    	{
    		if(this.checkedOutList.get(book).contains(copy))
    		{
    			int index = this.checkedOutList.get(book).indexOf(copy);
    			this.checkedOutList.get(book).remove(index);
    		}
    	}
    	
    }
    
   
    public static void main(String []args) {
    	BigLibrary library = new BigLibrary();
    	
//    	Book book = new Book("Test title", Arrays.asList("Furrukh", "Jamal"), 1985);
//    	BookCopy libraryBook = library.buy(book);
//    	BookCopy libraryBook2 = library.buy(book);
//    	System.out.println("inLibrary:" +library.getInLibrary());
//    	System.out.println("CheckedOut:" +library.getcheckedOut());
//    	
//    	Set<Book> keys = library.getInLibraryKeys();
//    	for(Book Book : keys) {
//    		//System.out.println("Book: " +Book);
//    	}
//    	
//    	library.checkout(libraryBook);
//    	
//    	
//    	System.out.println("inLibrary after checkout using .inLibrary" +library.inLibrary);
//    	System.out.println("inLibrary after checkout:" +library.getInLibrary());
//    	System.out.println("CheckedOut after checkout:" +library.getcheckedOut());
//    	
//    	library.checkin(libraryBook);
//    	
//    	System.out.println("inLibrary after checkin:" +library.getInLibrary() );
//    	System.out.println("CheckedOut after checkin:" +library.getcheckedOut() );
//    	//assertTrue("The library book is available again", library.isAvailable(libraryBook));
//    	System.out.println("Availability in main:" + library.isAvailable(libraryBook));
    	
    	//debugging testFindOneBookwithTwoMatchesOneBookWithOneMatch
//    	Book originalBook1 = new Book("This is a title", Arrays.asList("Furrukh", "Jamal"), 1985);
//    	Book originalBook2 = new Book("This Another one", Arrays.asList("Furrukh", "Jamal"), 1985);
//    	Book originalBook3 = new Book("Fake one", Arrays.asList("Furrukh", "Jamal"), 1985);
//    	
//    	BookCopy book1 = library.buy(originalBook1);
//    	BookCopy book2 = library.buy(originalBook2);
//    	BookCopy book3 = library.buy(originalBook3);
//    	
//    	List<Book> searchedBooks = library.find("This is");
//    	System.out.println("searchedBooks result in main" +searchedBooks);
    	
    	//debugging testFindOneMatchFromTitleOneMatchFromAuthor
    	
    	
    	Book originalBook1 = new Book("This is a title", Arrays.asList("Another"), 1985);
    	Book originalBook2 = new Book("This Another one", Arrays.asList("Furrukh"), 1986);
    	Book originalBook3 = new Book("Fake one", Arrays.asList("Furrukh", "Jamal"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	BookCopy book3 = library.buy(originalBook3);
    	
    	List<Book> searchedBooks = library.find("Another");
    	System.out.println("Result gotten: " +searchedBooks);
    	
    }
    
    public Set<Book> getInLibraryKeys() {
    	return this.inLibrary.keySet();
    }
    
    public Set<Book> getCheckedOutKeys(){
    	return this.checkedOutList.keySet();
    }
    
    public Map<Book, List<BookCopy>> getInLibrary(){
    	Map<Book, List<BookCopy>> inLibrary = new HashMap<>();
    	inLibrary.putAll(this.inLibrary);
    	return inLibrary;
    }
    
    public Map<Book, List<BookCopy>> getcheckedOut(){
    	return this.checkedOutList;
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
