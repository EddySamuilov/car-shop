package tu.carshop.services;

import java.util.List;

public abstract class BaseService<T> {
    protected abstract List<T> getAll();
    protected abstract T findById(Long id);
    protected abstract boolean deleteById(Long id);
    protected abstract T update();
}
