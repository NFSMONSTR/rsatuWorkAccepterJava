package ru.rsatu.rwa.resource;


import ru.rsatu.rwa.pojo.dto.BookDto;
import ru.rsatu.rwa.service.BooksService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books/api/v1")
public class BookResource {

    @Inject
    BooksService booksService;

    @GET
    @Path("/loadBookList")
    public List<BookDto> loadBookList() {
        return booksService.loadBookList();
    }

    @GET
    @Path("/book/author/{authorId}")
    public List<BookDto> getBooksByAuthor(@PathParam("authorId") Long authorId) {
        return booksService.getBooksByAuthor(authorId);
    }

    @DELETE
    @Path("/book/{bookId}")
    public void deleteBook(@PathParam("bookId") Long bookId) {
        booksService.deleteBook(bookId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/saveBook")
    public BookDto saveBook(BookDto bookDto) {
        return booksService.saveBook(bookDto);
    }

}
