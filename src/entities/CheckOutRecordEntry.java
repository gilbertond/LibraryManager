package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

public class CheckOutRecordEntry implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date checkOutDate;
    private Date dueDate;
    private CheckOutRecord checkOutRecord;
    private BookCopy bookCopy;
    private SimpleStringProperty checkOutDateP;
    private SimpleStringProperty dueDateP;
    private SimpleStringProperty isbn;
    private SimpleStringProperty title;
    private boolean isAvailable = true;

    public CheckOutRecordEntry(Date checkOutDate, Date dueDate,
            CheckOutRecord checkOutRecord, BookCopy bookCopy) {
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.checkOutRecord = checkOutRecord;
        this.bookCopy = bookCopy;
        setCheckOutDateP();
        setDueDateP();
        // save the CheckOutRecordEntry object in the checkOutRecordEntry.txt.

    }

    public void setIsAvailable(boolean state) {
        this.isAvailable = state;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    /*
     * Accessory methods.
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public CheckOutRecord getCheckOutRecord() {
        return checkOutRecord;
    }

    public void setCheckOutRecord(CheckOutRecord checkOutRecord) {
        this.checkOutRecord = checkOutRecord;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public String getCheckOutDateP() {
        return checkOutDateP.get();
    }

    public void setCheckOutDateP() {
        this.checkOutDateP = new SimpleStringProperty(new SimpleDateFormat(
                "dd/MM/yyyy").format(getCheckOutDate()));
    }

    public String getDueDateP() {
        return dueDateP.get();
    }

    public void setDueDateP() {
        this.dueDateP = new SimpleStringProperty(new SimpleDateFormat(
                "dd/MM/yyyy").format(getDueDate()));
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn() {
        this.isbn = new SimpleStringProperty(bookCopy.getISBN());
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle() {
        this.title = new SimpleStringProperty(bookCopy.getTitle());
    }

    @Override
    public String toString() {
        return "CheckOutRecordEntry{" + "checkOutDate=" + checkOutDate + ", dueDate=" + dueDate + ", checkOutRecord=" + checkOutRecord + ", bookCopy=" + bookCopy + ", checkOutDateP=" + checkOutDateP + ", dueDateP=" + dueDateP + ", isbn=" + isbn + ", title=" + title + ", isAvailable=" + isAvailable + '}';
    }    
}
