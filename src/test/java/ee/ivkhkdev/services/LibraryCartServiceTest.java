package ee.ivkhkdev.services;

import ee.ivkhkdev.helpers.LibraryCartAppHelper;
import ee.ivkhkdev.intefaces.AppHelper;
import ee.ivkhkdev.intefaces.AppRepository;
import ee.ivkhkdev.model.LibraryCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryCartServiceTest {

    private LibraryCartAppService libraryCartService;
    private AppHelper<LibraryCart> mockLibraryCardAppHelper;
    private AppRepository<LibraryCart> mockRepository;

    @BeforeEach
    void setUp() {
        // Создаем моки для зависимостей
        mockLibraryCardAppHelper = Mockito.mock(AppHelper.class);
        mockRepository = Mockito.mock(AppRepository.class);

        // Инициализируем LibraryCartAppService с моками
        libraryCartService = new LibraryCartAppService(mockLibraryCardAppHelper, mockRepository);
    }

    @Test
    void testAddLibraryCardSuccess() {
        // Подготовка: создать LibraryCart и настроить заглушки
        LibraryCart mockLibraryCart = new LibraryCart();
        when(mockLibraryCardAppHelper.create()).thenReturn(mockLibraryCart);

        // Выполняем метод add
        boolean result = libraryCartService.add();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).save(mockLibraryCart); // Убедиться, что метод save был вызван один раз
    }

    @Test
    void testAddLibraryCardFailureWhenLibraryCardIsNull() {
        // Настроить заглушку, чтобы create возвращал null
        when(mockLibraryCardAppHelper.create()).thenReturn(null);

        // Выполняем метод add
        boolean result = libraryCartService.add();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).save(any()); // Убедиться, что метод save не был вызван
    }

    @Test
    void testAddLibraryCardExceptionHandling() {
        // Подготовка: создать LibraryCart и выбросить исключение при вызове save
        LibraryCart mockLibraryCart = new LibraryCart();
        when(mockLibraryCardAppHelper.create()).thenReturn(mockLibraryCart);
        doThrow(new RuntimeException("Save error")).when(mockRepository).save(mockLibraryCart);

        // Выполняем метод add
        boolean result = libraryCartService.add();

        // Проверка
        assertFalse(result); // Ожидаем, что метод вернет false при возникновении исключения
    }

    @Test
    void testPrint() {
        // Подготовка: создать список LibraryCart и настроить заглушки
        List<LibraryCart> mockLibraryCartList = List.of(new LibraryCart());
        when(mockRepository.load()).thenReturn(mockLibraryCartList);
        when(mockLibraryCardAppHelper.printList(mockLibraryCartList)).thenReturn(true);

        // Выполняем метод print
        boolean result = libraryCartService.print();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
        verify(mockLibraryCardAppHelper, times(1)).printList(mockLibraryCartList); // Убедиться, что метод printList был вызван один раз
    }

    @Test
    void testList() {
        // Подготовка: создать список LibraryCart и настроить заглушки
        List<LibraryCart> mockLibraryCartList = List.of(new LibraryCart());
        when(mockRepository.load()).thenReturn(mockLibraryCartList);

        // Выполняем метод list
        List<LibraryCart> result = libraryCartService.list();

        // Проверка
        assertEquals(mockLibraryCartList, result);
        verify(mockRepository, times(1)).load(); // Убедиться, что метод load был вызван один раз
    }

    @Test
    void testReturnBookSuccess() {
        // Подготовка: создать список LibraryCart и настроить заглушки
        LibraryCartAppHelper mockLibraryCardAppHelperCast = mock(LibraryCartAppHelper.class);
        List<LibraryCart> mockLibraryCartList = List.of(new LibraryCart());
        when(mockLibraryCardAppHelperCast.returnBack(mockLibraryCartList)).thenReturn(mockLibraryCartList);
        when(mockRepository.load()).thenReturn(mockLibraryCartList);

        // Вызов метода returnBook
        boolean result = new LibraryCartAppService(mockLibraryCardAppHelperCast, mockRepository).returnBook();

        // Проверка
        assertTrue(result);
        verify(mockRepository, times(1)).saveAll(mockLibraryCartList); // Убедиться, что метод saveAll был вызван один раз
    }

    @Test
    void testReturnBookFailure() {
        // Подготовка: создать список LibraryCart и настроить заглушки
        LibraryCartAppHelper mockLibraryCardAppHelperCast = mock(LibraryCartAppHelper.class);
        List<LibraryCart> mockLibraryCartList = List.of(new LibraryCart());
        when(mockLibraryCardAppHelperCast.returnBack(mockLibraryCartList)).thenReturn(null);
        when(mockRepository.load()).thenReturn(mockLibraryCartList);

        // Вызов метода returnBook
        boolean result = new LibraryCartAppService(mockLibraryCardAppHelperCast, mockRepository).returnBook();

        // Проверка
        assertFalse(result);
        verify(mockRepository, never()).saveAll(any()); // Убедиться, что метод saveAll не был вызван
    }
}