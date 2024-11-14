package ee.ivkhkdev.helpers;

import ee.ivkhkdev.intefaces.Input;
import ee.ivkhkdev.intefaces.Service;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.model.LibraryCart;
import ee.ivkhkdev.model.User;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryCartAppHelperTest {

    private LibraryCartAppHelper libraryCardAppHelper;
    private Input mockInput;
    private Service<Book> mockBookService;
    private Service<User> mockUserService;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockInput = Mockito.mock(Input.class);
        mockBookService = Mockito.mock(BookService.class);
        mockUserService = Mockito.mock(UserService.class);

        // Инициализируем LibraryCartAppHelper с моками
        libraryCardAppHelper = new LibraryCartAppHelper(mockInput, mockBookService, mockUserService);
    }

    @Test
    void testCreateSuccess() {
        // Настройка моков для успешного создания LibraryCart
        Book mockBook = new Book();
        User mockUser = new User();
        when(mockBookService.print()).thenReturn(true);
        when(mockBookService.list()).thenReturn(List.of(mockBook));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор книги
        when(mockUserService.print()).thenReturn(true);
        when(mockUserService.list()).thenReturn(List.of(mockUser));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор пользователя

        // Выполнение метода create
        LibraryCart result = libraryCardAppHelper.create();

        // Проверка результата
        assertNotNull(result);
        assertEquals(mockBook, result.getBook());
        assertEquals(mockUser, result.getUser());
        assertEquals(LocalDate.now(), result.getBorrowdBookDate());
    }

    @Test
    void testCreateBookServicePrintFails() {
        // Настройка моков, чтобы bookService.print() вернул false
        when(mockBookService.print()).thenReturn(false);

        // Выполнение метода create
        LibraryCart result = libraryCardAppHelper.create();

        // Проверка результата
        assertNull(result);
    }

    @Test
    void testCreateUserServicePrintFails() {
        // Настройка моков для успешного выбора книги, но пользовательский сервис не удается
        Book mockBook = new Book();
        when(mockBookService.print()).thenReturn(true);
        when(mockBookService.list()).thenReturn(List.of(mockBook));
        when(mockInput.nextLine()).thenReturn("1"); // Выбор книги
        when(mockUserService.print()).thenReturn(false);

        // Выполнение метода create
        LibraryCart result = libraryCardAppHelper.create();

        // Проверка результата
        assertNull(result);
    }


    @Test
    void testPrintListWithNoReturnDates() {
        // Подготовка: создать список LibraryCart с незавершенными книгами
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCart libraryCart = new LibraryCart(book, user, LocalDate.now(), null); // Не возвращено
        List<LibraryCart> libraryCarts = List.of(libraryCart);

        // Выполняем метод printList
        boolean result = libraryCardAppHelper.printList(libraryCarts);

        // Проверка
        assertTrue(result);
    }

    @Test
    void testPrintListWithAllReturnDates() {
        // Подготовка: создать список LibraryCart с завершенными книгами
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCart libraryCart = new LibraryCart(book, user, LocalDate.now(), LocalDate.now()); // Книга возвращена
        List<LibraryCart> libraryCarts = List.of(libraryCart);

        // Выполняем метод printList
        boolean result = libraryCardAppHelper.printList(libraryCarts);

        // Проверка
        assertFalse(result);
    }

    @Test
    void testReturnBackWithValidSelection() {
        // Подготовка: создать список LibraryCart с незавершенной книгой
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCart libraryCart = new LibraryCart(book, user, LocalDate.now(), null);
        List<LibraryCart> libraryCarts = List.of(libraryCart);

        // Имитация ввода номера книги
        when(mockInput.nextLine()).thenReturn("1");

        // Выполняем метод returnBack
        List<LibraryCart> result = libraryCardAppHelper.returnBack(libraryCarts);

        // Проверка
        assertNotNull(result);
        assertNotNull(result.get(0).getReturnBookDate()); // Дата возврата должна быть установлена
    }

    @Test
    void testReturnBackWithNoBooksToReturn() {
        // Подготовка: создать список LibraryCart с завершенной книгой
        Book book = new Book("Test Book",List.of(new Author("firstnameAuthor","lastnameAuthor")), 2020);
        User user = new User("John", "Doe","123456");
        LibraryCart libraryCart = new LibraryCart(book, user, LocalDate.now(), LocalDate.now()); // Книга возвращена
        List<LibraryCart> libraryCarts = List.of(libraryCart);

        // Выполняем метод returnBack
        List<LibraryCart> result = libraryCardAppHelper.returnBack(libraryCarts);

        // Проверка
        assertNull(result); // Ожидаем null, так как нет книг для возврата
    }
}