package com.jitendra.testresiliencebeginner.repository;

import com.jitendra.testresiliencebeginner.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
