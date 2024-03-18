package ru.geekbrains.lesson2.homework2.models;


import ru.geekbrains.lesson2.homework2.Column;

import java.util.UUID;

@ru.geekbrains.lesson2.homework2.Entity
public class Entity {

    @Column(name = "id", primaryKey = true)
    private UUID id;

}
