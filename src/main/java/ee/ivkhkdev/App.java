package ee.ivkhkdev;

public class App {
    private Input input;

    // Теперь в конструктор передается Input вместо Scanner
    public App(Input input) {
        this.input = input;
    }

    public void run() {
        boolean repeat = true;
        do {
            System.out.println("Список задач:");
            System.out.println("0. Выйти из программы");
            System.out.print("Введите номер задачи: ");
            int task = input.nextInt();input.nextLine(); // Используем input
            switch (task) {
                case 0:
                    System.out.println("Выход из программы");
                    repeat = false;
                    break;
                default:
                    System.out.println("Выберите номер из списка задач!");
            }
        } while (repeat);
    }
}
