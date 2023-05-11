package ru.job4j.repository;

import ru.job4j.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll();

    Category add(Category category);

    Optional<Category> findById(int id);

    List<Category> findByName(String key);

    List<Category> findCategoryByIdList(List<Integer> list);

    List<Integer> findIdByCategoryList(List<Category> list);

    boolean delete(int id);

}
