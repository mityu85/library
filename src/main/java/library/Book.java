package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;
    private String title;

    @Column(name = "rental_date")
    private String rentalDate;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public Book(String author, String title, String rentalDate) {
        this.author = author;
        this.title = title;
        this.rentalDate = rentalDate;
    }
}
