package com.irms.api.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.irms.api.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleType role;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users = new ArrayList<>();

    public Authority() {
        // Explicit no args constructor for JPA
    }

    public Authority(RoleType role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public RoleType getRole() {
        return role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    
}
