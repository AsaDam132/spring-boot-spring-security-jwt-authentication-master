package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name="revrating")
public class RevRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
