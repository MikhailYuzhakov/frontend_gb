package ru.gb.homework;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.gb.lecture.orm.Connector;
import ru.gb.lecture.orm.Magic;

import java.util.List;

public class CourseEditor {
    public static void insertData() {
        int courses_number = 10;
        Connector connector = new Connector();
        Course course;

        try (Session session = connector.getSession()) {
            session.beginTransaction();
            for (int i = 0; i < courses_number; i++) {
                course = Course.create();
                System.out.println(course);
                session.save(course);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readData() {
        Connector connector = new Connector();

        try (Session session = connector.getSession()) {
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
            courses.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateData() {
        Connector connector = new Connector();

        try (Session session = connector.getSession()) {
            String hql = "from Course where id = :id";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("id", 6);
            Course course = query.getSingleResult();
            System.out.println(course);

            course.updateDuration();
            course.updateTitle();
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteData() {
        Connector connector = new Connector();

        try (Session session = connector.getSession()) {
            Transaction transaction = session.getTransaction();
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
            transaction.begin();
            courses.forEach(session::delete);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
