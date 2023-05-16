package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Category;

import java.util.Arrays;
import java.util.List;

public class ToDoIn {

    public static void main(String[] args) {

        final StandardServiceRegistry registry3 = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry3).buildMetadata().buildSessionFactory();
            List<Integer> list = Arrays.asList(1, 2, 3);
            String listName = "listName";
            var stored = listOf("FROM Category c WHERE c.id IN :listName", Category.class, sf, listName, list);
            for (Category category : stored) {
                System.out.println(category.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry3);
        }

    }

    public static <T> List<T> listOf(String query, Class<T> model, SessionFactory sf, String string, List<Integer> list) {
        Session session = sf.openSession();
        var rsl = session.createQuery(query, model).setParameterList(string, list)
                .getResultList();
        session.close();
        return rsl;
    }


}
