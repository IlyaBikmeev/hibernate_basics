package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main( String[] args ) {
        //Будем здесь делать транзакции
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class);   //Даём понять хибернейту, что у нас есть сущность Person

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //Делаем транзакцию (Добавляем сущности/удаляем/изменяем)

        //Создание нового человека.
        Person person1 = new Person("i@ibikmeev.ru", "12345678", 99);
        session.save(person1);

        //Получение человека
        Person person2 = session.get(Person.class, 3);
        System.out.printf("Person with id 3: %s\n", person2);

        //Изменение человека
        Person person3 = session.get(Person.class, 1);
        person3.setPassword("1234567890");


        //Удаление человека
        Person person4 = session.get(Person.class, 5);
        session.delete(person4);

        /*
        Hibernate нам даёт язык HQL
        HQL работает с сущностями.(с классами @Entity) и их полями.
        Сам этот язык НИЧЕГО не знает о таблицах в бд
        Пример:
        FROM Person WHERE age > 35
         */
        //Список людей старше 35 лет
        List<Person> middleAgePeople =
                session.createQuery("FROM Person WHERE age > 35").getResultList();

        System.out.println("People older than 35:");
        middleAgePeople.forEach(p -> System.out.println(p.getEmail()));

        //Список людей, email которых начинается на букву i.
        List<Person> startsWithIPeople =
                session.createQuery("FROM Person WHERE email LIKE 'i%'").getResultList();
        System.out.println("Email starts with i:");
        startsWithIPeople.forEach(p -> System.out.println(p.getEmail()));

        session.getTransaction().commit();      //Сохраняем изменения
        session.close();
    }
}
