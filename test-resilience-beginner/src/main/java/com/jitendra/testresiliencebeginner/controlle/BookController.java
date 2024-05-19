package com.jitendra.testresiliencebeginner.controlle;

import com.jitendra.testresiliencebeginner.entity.Book;
import com.jitendra.testresiliencebeginner.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public List<Book> getAllBookRecords() {
        return bookRepository.findAll();
    }

    @GetMapping("/{bookId}")
    public Book getBookRecordById(@PathVariable("bookId") Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @PostMapping()
    public Book createBookRecord(@RequestBody Book bookRecord) {
        return bookRepository.save(bookRecord);
    }
    @PutMapping()
    public Book updateBookRecord(@RequestBody Book bookRecord) throws Exception {
        if(bookRecord == null || bookRecord.getBookId() == null) {
            throw new Exception("bookRecord or Id must not be null");
        }

        Optional<Book> existingBookOptional = bookRepository.findById(bookRecord.getBookId());
        if(existingBookOptional.isEmpty()) {
            throw new Exception("Boom with Id " + bookRecord.getBookId() + "  does not exist.");
        }
        Book existingBook = existingBookOptional.get();

        existingBook.setName(bookRecord.getName());
        existingBook.setSummary(bookRecord.getSummary());
        existingBook.setRating(bookRecord.getRating());

        return bookRepository.save(existingBook);

    }

}
