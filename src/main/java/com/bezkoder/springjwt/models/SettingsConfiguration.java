package com.bezkoder.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name="settingconfig")
public class SettingsConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
