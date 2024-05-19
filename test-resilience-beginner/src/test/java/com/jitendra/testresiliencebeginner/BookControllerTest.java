package com.jitendra.testresiliencebeginner;

import com.jitendra.testresiliencebeginner.controlle.BookController;
import com.jitendra.testresiliencebeginner.entity.Book;
import com.jitendra.testresiliencebeginner.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;



@SpringBootTest
public class BookControllerTest {


    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    Book book1 = Book
            .builder()
            .bookId(1L)
            .name("Java1")
            .summary("Learn Java")
            .rating(5)
            .build();

    Book book2 = Book
            .builder()
            .bookId(1L)
            .name("node JS")
            .summary("Learn node Js")
            .rating(4)
            .build();


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllBookRecordsTest() throws Exception {
        List<Book> expectedList = List.of(book1, book2, book2);

        Mockito.when(bookRepository.findAll())
                .thenReturn(expectedList);

        List<Book> actual = bookController.getAllBookRecords();

        Assertions.assertIterableEquals(expectedList,actual );
    }

    @Test
    public void getBookRecordByIdTest() {

        Book book1 = Book
                .builder()
                .bookId(1L)
                .name("Java1")
                .summary("Learn Java")
                .rating(5)
                .build();

        Mockito.when(bookRepository.findById(2L))
                        .thenReturn(Optional.ofNullable(book1));

        Assertions.assertNotNull(bookController.getBookRecordById(1L));
    }
}
