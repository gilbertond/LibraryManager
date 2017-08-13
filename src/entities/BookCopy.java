package entities;
import java.io.Serializable;
import lib.util.PrimaryKeyGenerator;

public class BookCopy extends Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String copyId;
    private boolean isAvailable = true;
//    private Book book;

    BookCopy() {
        this.copyId = PrimaryKeyGenerator.primaryKey();
//        this.book = book;
    }

//    public BookCopy() {
//    }
    
    /*
     * Accessory methods. 
     */
    public String getCopyId() {
        return copyId;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }

    @Override
    public String toString() {
        return "BookCopy [copyId=" + copyId +"]";
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
        BookCopy other = (BookCopy) obj;
        return this.getCopyId().equals(other.getCopyId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        return (result * 31 + getCopyId().hashCode());

    }
}
