package org.example.model;

import lombok.*;

import javax.persistence.*;

@Entity         //Основная аннотация Hibernate. Этим мы даём понять, что класс является сущностью
@Table(name = "Person")     //Этим мы говорим, что класс связан с таблицей Person в бд
@Getter     //Эта аннотация создаёт геттеры для всех полей (Lombok)
@Setter     //Эта аннотация создаёт сеттеры для всех полей (Lombok)
@NoArgsConstructor    //Эта аннотация создаёт пустой конструктор, который ничего не делает
@ToString             //Эта аннотация создаёт метод toString
public class Person {
    @Id         //Даём понять, что это поле - первичный ключ
    @Column(name = "id")        //Даём понять, что это поле класса связано с полем таблицы "id"
    //Даём понять, каким образом у нас будет генерироваться первичный ключ в БД
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    //Здесь мы не передаём id. Как он будет сам устанавливаться? Благодаря @GeneratedValue!
    public Person(String email, String password, int age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }
}
