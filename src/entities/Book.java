package entities;

import java.util.List;
import java.util.ArrayList;
import lib.util.PrimaryKeyGenerator;
import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String ISBN;
    private String title;
    private int maxCheckOutLength;
    private List<Author> author;
    private List<BookCopy> bookCopy;
    private int checkoutLength;

    public Book(String ISBN, String title, int maxCheckOutLength, Author authorx, int numCopy) {
        this.id = PrimaryKeyGenerator.primaryKey();
        this.ISBN = ISBN;
        this.title = title;
        this.maxCheckOutLength = maxCheckOutLength;
        this.author = new ArrayList<>();
        this.author.add(authorx);
        this.bookCopy = new ArrayList<>();
        this.checkoutLength = 0;
        addCopy(numCopy);
    }

    public Book() {
    }

    public int getCheckoutLength() {
        return checkoutLength;
    }
    
    public void setCheckoutLength(int checkoutLength) {
        this.checkoutLength = checkoutLength;
    }        
    
    public void addAuthor(Author author){
        this.getAuthor().add(author);
    }
    /*
     * Accessory methods. 
     */
    public String getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxCheckOutLength() {
        return maxCheckOutLength;
    }

//    public void setMaxCheckOutLength(int maxCheckOutLength) {
//        this.maxCheckOutLength = maxCheckOutLength;
//    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<BookCopy> getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(List<BookCopy> bookCopy) {
        this.bookCopy = bookCopy;
    }

    /*
     * Operational methods
     */
    public void addCopy(int numCopy) {
        for (int i = 1; i <= numCopy; i++) {
            BookCopy copy = new BookCopy();
            this.bookCopy.add(copy);
        }
    }

    public void writtenBy(Author author) {
        this.author.add(author);
    }

    @Override
    public String toString() {
        return "Book [ISBN=" + ISBN + ", title=" + title + ", maxCheckOutLength=" + maxCheckOutLength + ", author="
                + author + ", copies=" + bookCopy.size() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().getName() != obj.getClass().getName()) {
            return false;
        }
        Book other = (Book) obj;
        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        return (result * 31 + getId().hashCode());
    }
}
