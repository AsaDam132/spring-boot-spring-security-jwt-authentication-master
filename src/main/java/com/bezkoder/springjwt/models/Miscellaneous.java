package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "miscalleneous")
public class Miscellaneous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
