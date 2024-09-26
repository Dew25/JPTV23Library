package ee.ivkhkdev;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AppTest {

    @Test
    public void testExitProgram() {
        // Мокируем Input
        Input inputMock = Mockito.mock(Input.class);
        // Настраиваем поведение метода nextInt() для завершения программы
        when(inputMock.nextInt()).thenReturn(0);

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Передаем мокированный Input в конструктор
        App app = new App(inputMock);

        // Запускаем метод run()
        app.run();

        // Проверяем, что метод nextInt() был вызван хотя бы один раз
        verify(inputMock, atLeastOnce()).nextInt();

        // Ожидаемый вывод
        String expectedOutput = "Список задач:\n" +
                "0. Выйти из программы\n" +
                "Введите номер задачи: \n" +
                "Выход из программы\n" +
                "Выберите номер из списка задач!\n";

        // Фактический вывод
        String actualOutput = outContent.toString();
        actualOutput = actualOutput.replaceAll("\\r\\n", "\n");
        // Выводим фактический и ожидаемый результаты для отладки
        System.out.println("Ожидаемый вывод: ");
        System.out.println(expectedOutput);
        System.out.println("Фактический вывод: ");
        System.out.println(actualOutput);

        // Сравниваем строки
        assertEquals(normalizeString(expectedOutput), normalizeString(actualOutput));
    }

    @Test
    public void testInvalidTaskNumber() {
        // Мокируем Input
        Input inputMock = Mockito.mock(Input.class);
        // Настраиваем поведение метода nextInt() для неверного ввода
        when(inputMock.nextInt()).thenReturn(5, 0); // Сначала неверный ввод, затем выход

        // Перехватываем вывод в консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Передаем мокированный Input в конструктор
        App app = new App(inputMock);

        // Запускаем метод run()
        app.run();

        // Проверяем, что метод nextInt() был вызван дважды
        verify(inputMock, times(2)).nextInt();

        // Ожидаемый вывод
        String expectedOutput = "Список задач:\n" +
                "0. Выйти из программы\n" +
                "Введите номер задачи: \n" +
                "Выберите номер из списка задач!\n" +
                "Список задач:\n" +
                "0. Выйти из программы\n" +
                "Введите номер задачи: \n" +
                "Выход из программы\n" +
                "Выберите номер из списка задач!\n";

        // Фактический вывод
        String actualOutput = outContent.toString();
        actualOutput = normalizeString(actualOutput);
        // Выводим фактический и ожидаемый результаты для отладки
        System.out.println("Ожидаемый вывод: ");
        System.out.println(expectedOutput);
        System.out.println("Фактический вывод: ");
        System.out.println(actualOutput);

        // Сравниваем строки
        assertEquals(normalizeString(expectedOutput), normalizeString(actualOutput));
    }

    // Метод для нормализации строк: убираем лишние пробелы и унифицируем переносы строк
    private String normalizeString(String input) {
        return input.trim().replaceAll("\\r\\n", "\n").replaceAll("\\s+$", "");
    }
}
