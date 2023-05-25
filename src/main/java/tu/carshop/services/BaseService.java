package tu.carshop.services;

import java.util.List;

public abstract class BaseService<T> {
    protected abstract List<T> getAll();
    protected abstract List<T> findById(Long id);
    protected abstract List<T> deleteById(Long id);
    protected abstract List<T> update();
}
