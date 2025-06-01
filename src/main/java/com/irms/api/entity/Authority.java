package com.irms.api.entity;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
