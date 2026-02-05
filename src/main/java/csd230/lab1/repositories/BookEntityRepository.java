package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.pojos.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    List<Book> findByIsbn(String isbn);
    Book findById(long id);

    @Query
    BookEntity findByPriceRange(
            @Param("minPrice") double min,
            @Param("maxPrice") double max
    );
}