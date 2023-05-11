package ru.job4j.service;

import ru.job4j.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Category add(Category category);

    Optional<Category> findById(int id);

    List<Category> findByName(String key);

    boolean delete(int id);

    List<Category> findCategoryByIdList(List<Integer> list);

    List<Integer> findIdByCategoryList(List<Category> list);
}
