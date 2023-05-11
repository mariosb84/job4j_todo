package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateCategoryRepository implements CategoryRepository {

    private final HibernateCrudRepository crudRepository;

    @Override
    public List<Category> findAll() {
        return crudRepository.query("from  Category order by id asc", Category.class);
    }

    @Override
    public Category add(Category category) {
        crudRepository.run(session -> session.persist(category));
        return category;
    }

    @Override
    public Optional<Category> findById(int id) {
        return crudRepository.optional(
                "from Category where id = :fId", Category.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Category> findByName(String key) {
        return crudRepository.query(
                "from Category where name = :fName", Category.class,
                Map.of("fName", key)
        );
    }

    @Override
    public boolean delete(int id) {
        crudRepository.run(
                "delete from Category where id = :fId",
                Map.of("fId", id)
        );
        return true;
    }
    @Override
    public List<Category> findCategoryByIdList(List<Integer> list) {
        List<Category> result = new ArrayList<>();
        for (int a : list) {
            result.add(findById(a).get());
        }
        return result;
    }

    @Override
    public List<Integer> findIdByCategoryList(List<Category> list) {
        List<Integer> result = new ArrayList<>();
        for (Category category : list) {
            result.add(category.getId());
        }
        return result;
    }

}
