package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Book is an immutable type representing an edition of a book -- not the physical object, 
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author 
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {

    // TODO: rep
	private final String title;
	private final List<String> authors;
	private final int year;
    
    // TODO: rep invariant
    /*
     * @param string:  must be a non empty with atleast one charater
     * @param authors: must be non empty and each name must contain atleast one character
     * @param year : a non negative number*/
	
	// TODO: abstraction function
	/*A book is uniquely identifed by its title*/
	
    
	// TODO: safety from rep exposure argument
	/*Each filed is private and final
	 * for authors defensive copying is implemented
	 * year is finat so it is immutable*/
    
    /**
     * Make a Book.
     * @param title Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain 
     * at least one non-space character.
     * @param year Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative. 
     */
    public Book(String title, List<String> authors, int year) {
        if(title == null || title.isEmpty()) {
        	throw new IllegalArgumentException("Title is null or empty");
        }
        if (authors == null || authors.isEmpty()) {
        	throw new IllegalArgumentException("Authors cannot be empty");
        }
        if(year <= 0) {
        	throw new IllegalArgumentException("Year can not be negative");
        }
        
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.year  = year;
        
    }
    
    // assert the rep invariant
    private void checkRep() {
        //throw new RuntimeException("not implemented yet");
    	assert this.title != null && !this.title.isEmpty(): "Rep invariant violated";
    	assert this.authors != null && !this.authors.isEmpty() : "Rep invariant violated";
    	assert this.year > 0: "Rep invariant violated";
    		
    }
    
    /**
     * @return the title of this book
     */
    public String getTitle() {
        //throw new RuntimeException("not implemented yet");
    	return this.title;
    }
    
    /**
     * @return the authors of this book
     */
    public List<String> getAuthors() {
        //throw new RuntimeException("not implemented yet");
    	List<String> copyAuthors = new ArrayList<>();
    	copyAuthors.addAll(this.authors);
    	return copyAuthors; 
    	
    }

    /**
     * @return the year that this book was published
     */
    public int getYear() {
        //throw new RuntimeException("not implemented yet");
    	return this.year;
    }

    /**
     * @return human-readable representation of this book that includes its title,
     *    authors, and publication year
     */
    public String toString() {
        //throw new RuntimeException("not implemented yet");
    	String authors = "";
    	for(String author : this.authors)
    	{
    		authors += " " + author;
    	}
    	String stringRepresentation = "Title: " + this.title + "\n" + "Authors:" + authors + "\n" + "Year : " + this.year;
    	return stringRepresentation;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

//     uncomment the following methods if you need to implement equals and hashCode,
//     or delete them if you don't
     //@Override
//     public boolean equals(Object that) {
//         if(!(that instanceof Book))
//         {
//        	 return false;
//         }
//         
//         Book thatBook = (Book) that;
//         return thatBook.getTitle().equals(this.getTitle());
//         
//     }
//    // 
//     @Override
//     public int hashCode() {
//         //throw new RuntimeException("not implemented yet");
//    	 int result = 2;
//    	 int code = 0;
//    	 code += this.year;
//    	 code+= this.getTitle().hashCode();
//    	 for (String author : this.authors)
//    	 {
//    		 code += author.hashCode();
//    	 }
//    	 
//    	 result = 37 * result + code;
//    	 
//    	 return result;
//     }

     

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
