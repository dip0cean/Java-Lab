package me.osean.effective_java.chapter_01.item_03.singleton_pattern_08;

import java.io.*;
import java.time.LocalDate;

public class Book implements Serializable {

    // Java 는 serialVersionUid 를 정의하지 않으면 자동으로 지정해준다.
    // 별도로 serialVersionUid 를 정의하지 않은 상태에서 객체를 직렬화 한 후 클래스 정보를 변경한 경우
    // Java 에서 해당 클래스의 serialVersionUid 를 다른 값으로 변경하여 자동 정의한다.
    // 때문에 serialVersionUid 를 정의하지 않고 변경 전 직렬화 데이터를 변경 후 클래스로 역직렬화 하는 경우 역직렬화를 수행하지 못한다.

    // 정적 필드는 직렬화를 수행하지 않는다.
    private static String CATEGORY;

    private final String isbn;
    private final String title;
    private final String author;
    private final LocalDate published;
    // transient 예약어를 사용하는 경우, 직렬화를 수행하지 않는다.
    private transient int numberOfSold;

    public Book(String isbn, String title, String author, LocalDate published) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.published = published;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published=" + published +
                ", numberOfSold=" + numberOfSold +
                ", category=" + CATEGORY +
                '}';
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublished() {
        return published;
    }

    public int getNumberOfSold() {
        return numberOfSold;
    }

    public void setNumberOfSold(int numberOfSold) {
        this.numberOfSold = numberOfSold;
    }

    public static void main(String[] args) {
        Book book = new Book("12345", "Effective Java", "조슈아 어쩌구", LocalDate.now());
        book.CATEGORY = "개발서적";
        book.setNumberOfSold(1000);

        // Output
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("book.obj"))) {
            out.writeObject(book);
            book.CATEGORY = "이벤트";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Input
        try (ObjectInput in = new ObjectInputStream(new FileInputStream("book.obj"))) {
            Book nBook = (Book) in.readObject();
            System.out.printf("직렬화 전 책 정보 : %s\n", book);
            System.out.printf("역직렬화 후 책 정보 : %s\n", nBook);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
