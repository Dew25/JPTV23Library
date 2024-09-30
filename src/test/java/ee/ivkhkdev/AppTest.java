package ee.ivkhkdev;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Customer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AppTest {

    private Input inputMock; // Мок объекта Input
    private ByteArrayOutputStream outContent; // Для перехвата вывода консоли
    private final PrintStream originalOut = System.out; // Оригинальный System.out

    @BeforeEach
    public void setUp() {
        // Мокируем Input
        inputMock = Mockito.mock(Input.class);

        // Перехватываем вывод в консоль
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        // Восстанавливаем оригинальный System.out после каждого теста
        System.setOut(originalOut);
    }

    @Test
    public void testExitProgram() {
        // Настраиваем поведение nextInt для завершения программы
        when(inputMock.nextInt()).thenReturn(0);

        // Создаем объект App с мокированным input
        App app = new App(inputMock);

        // Запускаем метод run
        app.run();

        // Ожидаемый вывод программы
//        String expectedOutput = "Список задач:\n" +
//                "0. Выйти из программы\n" +
//                "1. Добавить пользователя\n" +
//                "Введите номер задачи: " +
//                "Выход из программы\n";

        // Проверяем, что фактический вывод совпадает с ожидаемым
        // assertEquals(normalizeString(expectedOutput), normalizeString(outContent.toString()));
        assertTrue(outContent.toString().contains("Досвидания! :)"));
    }

    @Test
    public void testInvalidTaskNumber() {
        // Настраиваем поведение nextInt для неверного ввода и последующего завершения программы
        when(inputMock.nextInt()).thenReturn(5, 0); // Сначала неверный ввод, затем завершение
        // Создаем объект App с мокированным input
        App app = new App(inputMock);
        // Запускаем метод run
        app.run();
        assertTrue(outContent.toString().contains("Выберите номер из списка задач!") && outContent.toString().contains("Досвидания! :)"));
    }
    @Test
    public void testAddCustomer() {
        // Настраиваем поведение nextInt для неверного ввода и последующего завершения программы
        when(inputMock.nextInt()).thenReturn(1, 0); // Сначала неверный ввод, затем завершение
        // Создаем объект App с мокированным input
        App app = new App(inputMock);

        Customer expected = new Customer("Ivan", "Ivanov", "56565656");
        // Запускаем метод run
        app.run();
        // Проверяем, что фактический вывод совпадает с ожидаемым
        assertTrue(App.customers[0].getFirstName().equals("Ivan") && outContent.toString().contains("Досвидания! :)"));
    }

}

