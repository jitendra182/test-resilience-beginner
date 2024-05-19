package com.jitendra.testresiliencebeginner;

import com.jitendra.testresiliencebeginner.controlle.BookController;
import com.jitendra.testresiliencebeginner.entity.Book;
import com.jitendra.testresiliencebeginner.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookControllerMvcTest {
    @InjectMocks
    BookController bookController;

    @Mock
    BookRepository bookRepository;

    @Test
    public void getAllBookRecords()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Book book1 = Book.builder().bookId(1L).name("Java1").summary("Learn Java").rating(5).build();
        Book book2 = Book.builder().bookId(1L).name("node JS").summary("Learn node Js").rating(4).build();

        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book1,book2));
        List<Book> responseEntity = bookController.getAllBookRecords();

        Assertions.assertEquals(2 ,responseEntity.size());
    }

}
