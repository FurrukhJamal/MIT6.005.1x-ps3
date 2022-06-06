package library;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BookCopy is a mutable type representing a particular copy of a book that is held in a library's
 * collection.
 */
public class BookCopy {

    private static int bookId = 0;
    private final int id;
	// TODO: rep
	private final String title;
	private final List<String> authors;
	private final int year;
    private final Book parentBook;
    private Condition condition;
    // TODO: rep invariant
	/*
     * @param string:  must be a non empty with atleast one charater
     * @param authors: must be non empty and each name must contain atleast one character
     * @param year : a non negative number*/
	
    // TODO: abstraction function
	/*A book copy is a book uniquely identified by its title and can have two states*/ 
	
    // TODO: safety from rep exposure argument
    
    public static enum Condition {
        GOOD, DAMAGED
    };
    
    /**
     * Make a new BookCopy, initially in good condition.
     * @param book the Book of which this is a copy
     */
    public BookCopy(Book book) {
        //throw new RuntimeException("not implemented yet");
    	this.title = book.getTitle();
    	this.authors = book.getAuthors();
    	this.year = book.getYear();
    	this.parentBook = book;
    	this.condition = Condition.GOOD;
    	
    	this.id = ++bookId;
    	
    	this.checkRep();
    }
    
    // assert the rep invariant
    private void checkRep() {
        //throw new RuntimeException("not implemented yet");
    	assert this.parentBook != null;
    	assert condition == Condition.GOOD || condition == Condition.DAMAGED; 
    }
    
    /**
     * @return the Book of which this is a copy
     */
    public Book getBook() {
        //throw new RuntimeException("not implemented yet");
    	return this.parentBook;
    }
    
    /**
     * @return the condition of this book copy
     */
    public Condition getCondition() {
        //throw new RuntimeException("not implemented yet");
    	return this.condition;
    }

    /**
     * Set the condition of a book copy.  This typically happens when a book copy is returned and a librarian inspects it.
     * @param condition the latest condition of the book copy
     */
    public void setCondition(Condition condition) {
        //throw new RuntimeException("not implemented yet");
    	this.condition = condition;
    	this.checkRep();
    }
    
    /**
     * @return human-readable representation of this book that includes book.toString()
     *    and the words "good" or "damaged" depending on its condition
     */
    public String toString() {
        //throw new RuntimeException("not implemented yet");
    	String book = this.parentBook.toString();
    	String condition = this.condition == Condition.GOOD ? "good" : "damaged";
    	book += "\n Condition: " + condition ;
    	return book;
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
     @Override
     public boolean equals(Object that) {
         //throw new RuntimeException("not implemented yet");
    	 if(!(that instanceof BookCopy))
    	 {
    		 return false;
    	 }
    	 
    	 BookCopy thatBook = (BookCopy)that;
    	 System.out.println("Equals hitting");
    	 return thatBook == this;
     }
     
     @Override
     public int hashCode() {
         //throw new RuntimeException("not implemented yet");
    	 int result = 2;
    	 int code = 0;
    	 code += this.parentBook.hashCode();
    	 //code += this.id;
    	 
    	 result = 37 * result + code;
    	 return result;
    	 
     }
     
     public static void main(String [] args) {
    	 Book book = new Book("Test Title", Arrays.asList("Furrukh", "Jamal"), 1985);
    	 BookCopy copy1 = new BookCopy(book);
    	 BookCopy copy2 = new BookCopy(book);
    	 BookCopy copy3 = new BookCopy(book);
    	 
    	 System.out.println("id: " +copy1.id);
    	 System.out.println("id2: " +copy2.id);
    	 System.out.println("id3: " +copy3.id);
    	 System.out.println("copy1.hashcode: " +copy1.hashCode());
    	 System.out.println("copy2.hashcode: " +copy2.hashCode());
    	 
    	 
    	 System.out.println(copy1.equals(copy2));
    	 System.out.println(copy1.getBook().equals(copy2.getBook()));
    	 System.out.println(copy1 == copy2);
    	 
    	 System.out.println("HashCode Tests\n");
    	 System.out.println("Copy1" +copy1.hashCode());
    	 System.out.println("Copy2" +copy2.hashCode());
    	 
    	 System.out.println("Book hashcode" +book.hashCode());
    	 
    	 
    	 Set<BookCopy> test = new HashSet<>();
    	 test.add(copy2);
    	 test.add(copy1);
    	 System.out.println(test);
    	 
    	 Map<Book, Set<BookCopy>> test2 = new HashMap<>();
    	 test2.put(book, test);
    	 System.out.println("MAP");
    	 System.out.println(test2);
    	 
    	 test2.get(book).add(copy3);
    	 
    	 System.out.println("NEW SET");
    	 System.out.println(test2);
    	 System.out.println("New MAP");
    	 System.out.println(test2);
    	 
    	 System.out.println("_______________");
    	 System.out.println("After Removeing a book from set");
    	 System.out.println("_______________");
    	 
    	 test.remove(copy1);
    	 System.out.println(test2.get(book));
    	 System.out.println(test2);
    	 
     }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
