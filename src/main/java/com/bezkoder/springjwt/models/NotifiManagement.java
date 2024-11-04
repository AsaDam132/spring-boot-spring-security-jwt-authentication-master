package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "notify")
public class NotifiManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
