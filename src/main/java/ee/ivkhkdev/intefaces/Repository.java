package ee.ivkhkdev.intefaces;

import java.util.List;

public interface Repository<T> {
    void save(T entity);
    List<T> load();
}
