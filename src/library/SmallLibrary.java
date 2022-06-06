package library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 
 * SmallLibrary represents a small collection of books, like a single person's home collection.
 */
public class SmallLibrary implements Library {

    // This rep is required! 
    // Do not change the types of inLibrary or checkedOut, 
    // and don't add or remove any other fields.
    // (BigLibrary is where you can create your own rep for
    // a Library implementation.)

    // rep
    private Set<BookCopy> inLibrary;
    private Set<BookCopy> checkedOut;
    
    // rep invariant:
    //    the intersection of inLibrary and checkedOut is the empty set
    //
    // abstraction function:
    //    represents the collection of books inLibrary union checkedOut,
    //      where if a book copy is in inLibrary then it is available,
    //      and if a copy is in checkedOut then it is checked out

    // TODO: safety from rep exposure argument
    
    public SmallLibrary() {
        //throw new RuntimeException("not implemented yet");
    	this.inLibrary = new HashSet<>();
    	this.checkedOut = new HashSet<>();
    	this.checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
        //throw new RuntimeException("not implemented yet");
    	Set<BookCopy> intersection = new HashSet<BookCopy>(inLibrary);
    	intersection.retainAll(this.checkedOut);
    	assert intersection.isEmpty() : "rep variant voilated";
    }

    @Override
    public BookCopy buy(Book book) {
        //throw new RuntimeException("not implemented yet");
    	BookCopy newcopyBook = new BookCopy(book);
    	//add to this library
    	this.inLibrary.add(newcopyBook);
    	this.checkRep();
    	return newcopyBook;
    }
    
    @Override
    public void checkout(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
    	if(this.isAvailable(copy))
    	{
    		this.inLibrary.remove(copy);
    		this.checkedOut.add(copy);
    	}
    	this.checkRep();
    }
    
    @Override
    public void checkin(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
    	if(this.checkedOut.contains(copy))
    	{
    		this.checkedOut.remove(copy);
    		this.inLibrary.add(copy);
    	}
    	this.checkRep();
    }
    
    @Override
    public boolean isAvailable(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
    	return this.inLibrary.contains(copy);
    }
    
    @Override
    public Set<BookCopy> allCopies(Book book) {
        //throw new RuntimeException("not implemented yet");
    	Set<BookCopy> allBooks = new HashSet<>();
    	for(BookCopy Book : this.inLibrary) {
    		//System.out.println(Book);
    		if(Book.getBook() == book)
    		{
    			allBooks.add(Book);
    		}
    	}
    	for(BookCopy Book : this.checkedOut) {
    		if(Book.getBook() == book)
    		{
    			allBooks.add(Book);
    		}
    	}
    	
    	
    	this.checkRep();
    	return allBooks;
    }
    
    @Override
    public Set<BookCopy> availableCopies(Book book) {
        //throw new RuntimeException("not implemented yet");
    	Set<BookCopy> availableBooks = new HashSet<>();
    	for(BookCopy Book: this.inLibrary) {
    		if(Book.getBook() == book)
    		{
    			availableBooks.add(Book);  
    		}
    	}
    	
    	this.checkRep();
    	return availableBooks;
    }

    @Override
    public List<Book> find(String query) {
        //throw new RuntimeException("not implemented yet");
    	//Set<Book> result = new HashSet<>();
    	List<Book> result = new ArrayList<>();
    	
    	for(BookCopy book : this.inLibrary) {
    		System.out.println("Title is : " +book.getBook().getTitle());
    		if(book.getBook().getTitle() == query)
    		{
    			//go through the authors
    			for(String author : book.getBook().getAuthors()) {
    				if(author == query) {
    					//Bug fix to add at the end of List
    					result.add(result.isEmpty() ? 0 : result.size() - 1,book.getBook());
    				}
    			}
    			//title matched but no author so add that book in the end
    			System.out.println("Set at the end of if ");
    			System.out.println(result);
    			if(!result.contains(book.getBook()))
    			{
    				//add at the end of list
    				result.add(result.isEmpty() ? 0 : result.size() - 1, book.getBook());
    				//result.add(book.getBook());
    			}
    			
    		}
    		else
    		{
    			//System.out.println("Inside Else condition");
    			//There was no match in the title so must check authors
    			for(String author : book.getBook().getAuthors()) {
    				if(author == query) {
    					//add at the end of list
    					result.add(result.isEmpty() ? 0 : result.size() - 1, book.getBook());
    					//result.add(book.getBook());
    				}
    			}
    			//System.out.println("Set at the end of Else : ");
    			//System.out.println(result);
    		}
    	}
    	
//    	List<Book> returnedList = new ArrayList<>(result);
//    	return returnedList;
    	System.out.println("LIST before RETURNED :" +result);
    	Collections.reverse(result);
    	return result;
    }
    
    public static void main(String[] args) {
    	SmallLibrary library = new SmallLibrary();
    	
    	Book originalBook1 = new Book("Furrukh", Arrays.asList("Furrukh"), 1985);
    	Book originalBook2 = new Book("Another one", Arrays.asList("Jim", "Furrukh"), 1985);
    	
    	BookCopy book1 = library.buy(originalBook1);
    	BookCopy book2 = library.buy(originalBook2);
    	
    	List<Book> searchedBooks = library.find("Furrukh");
    	System.out.println("LIST First Element :" +searchedBooks);
    }
    
    @Override
    public void lose(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
    	this.inLibrary.remove(copy);
    	this.checkedOut.remove(copy);
    	this.checkRep();
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
