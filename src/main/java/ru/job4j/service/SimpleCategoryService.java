package ru.job4j.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.model.Category;
import ru.job4j.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleCategoryService implements CategoryService {

    private final CategoryRepository store;

    public SimpleCategoryService(CategoryRepository store) {
        this.store = store;
    }

    @Override
    public List<Category> findAll() {
        return store.findAll();
    }

    @Override
    public Category add(Category category) {
        return store.add(category);
    }

    @Override
    public Optional<Category> findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<Category> findByName(String key) {
        return store.findByName(key);
    }

    @Override
    public boolean delete(int id) {
        return store.delete(id);
    }

    @Override
    public List<Category> findCategoryByIdList(List<Integer> list) {
        return store.findCategoryByIdList(list);
    }

    @Override
    public List<Integer> findIdByCategoryList(List<Category> list) {
        return store.findIdByCategoryList(list);
    }

}


