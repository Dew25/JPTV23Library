package ee.ivkhkdev;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Customer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
    void testRunExitProgram() {
        // Настраиваем поведение nextLine для завершения программы
        when(inputMock.nextLine()).thenReturn("0");

        // Создаем объект App с мокированным input
        App app = new App(inputMock);

        // Запускаем метод run
        app.run();
        assertTrue(outContent.toString().contains("До свидания! :)"));
    }

    @Test
    public void testRunInvalidTaskNumber() {
        // Настраиваем поведение nextInt для неверного ввода и последующего завершения программы
        when(inputMock.nextLine()).thenReturn("5", "0"); // Сначала неверный ввод, затем завершение
        // Создаем объект App с мокированным input

        App app = new App(inputMock);
        // Запускаем метод run
        app.run();

        assertTrue(outContent.toString().contains("Выберите номер из списка задач!") && outContent.toString().contains("До свидания! :)"));
    }

    @Test
    public void testAddCustomer() {

        when(inputMock.nextLine()).thenReturn("1", "Ivan","Ivanov", "56565656","0");
        // Создаем объект App с мокированным input

        App app = new App(inputMock);
        // Запускаем метод run
        app.run();

        Customer expected = new Customer("Ivan", "Ivanov", "56565656");
        // Проверяем, что фактический вывод совпадает с ожидаемым
        assertTrue(App.customers[0].getFirstName().equals("Ivan") && outContent.toString().contains("До свидания! :)"));
    }

}

