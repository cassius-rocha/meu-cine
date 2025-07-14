package com.api.backend.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Shelf> shelves = new ArrayList<>();

    @Column(nullable = false)
    private String password;

    public User() {

    }

    public User(Long id, String name, String email, List<Shelf> shelves, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.shelves = shelves;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
