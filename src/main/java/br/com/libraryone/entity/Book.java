package br.com.libraryone.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "books")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 1, max = 200, message = "Título deve ter entre 1 e 200 caracteres")
    @Column(nullable = false, length = 200)
    private String title;

    @NotBlank(message = "Autor é obrigatório")
    @Size(min = 1, max = 100, message = "Autor deve ter entre 1 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String author;

    @Column(unique = true, length = 20)
    private String isbn;

    @Column(name = "publication_year")
    private Integer publicationYear;
}
