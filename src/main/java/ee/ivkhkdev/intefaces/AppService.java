package ee.ivkhkdev.intefaces;

import java.util.List;

public interface AppService<T> {
    boolean add();
    boolean edit();
    boolean print();
    List<T> list();
}
