package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskRepositoryHbt implements TaskRepository {

    private final SessionFactory sf;

    @Override
    public List<Task> findAll() {
        List<Task> result = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Task order by id");
            result = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Task> findAllCompleted() {
        List<Task> result = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Task as t where t.done =: fDone order by id", Task.class);
            query.setParameter("fDone", true);
            result = (query.list());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Task> findAllNotCompleted() {
        List<Task> result = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Task as t where t.done =: fDone order by id", Task.class);
            query.setParameter("fDone", false);
            result = (query.list());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Task> add(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.of(task);
    }

    @Override
    public void update(Task task, int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                    "UPDATE Task SET description = :fDesc, created = : fCreated, done =: fDone WHERE id = :fId")
                    .setParameter("fDesc", task.getDescription())
                    .setParameter("fCreated", task.getCreated())
                    .setParameter("fDone", task.isDone())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Task> findById(int id) {
        Optional<Task> result = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(
                    "from Task as t where t.id = :fId", Task.class);
            query.setParameter("fId", id);
            result = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Task> findByName(String key) {
        List<Task> result = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "from Task as t where t.description = :fName", Task.class);
            query.setParameter("fName", key);
            result = (query.list());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
             session.createQuery(
                    "DELETE Task WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

}
