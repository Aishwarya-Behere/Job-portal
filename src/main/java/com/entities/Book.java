package libManage.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "book_title", nullable = false)
	private String title;
	
	@Column(name = "book_author", nullable = false)
	private String author;
	
	@Column(name = "international_book_no", unique = true, nullable = false)
	private String isbn;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(name = "added_on", nullable = false)
	private LocalDateTime addedOn;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Genre genre;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Availability availability;
	
}
