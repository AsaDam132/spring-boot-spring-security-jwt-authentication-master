package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "file")
public class FileManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
