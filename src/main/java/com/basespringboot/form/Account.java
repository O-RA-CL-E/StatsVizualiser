package com.basespringboot.form;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(
    name = "account",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_account_platform_handle",
        columnNames = {"platform","handle"}
    )
)
@Getter 
@Setter

public class Account {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Platform platform;

    @Column(nullable = false, length = 100)
    private String handle;

    @Column(length = 20)
    private String label;

    @Column(length = 255)
    private String url;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<StatSnapshot> snapshots = new ArrayList<>();

    @PrePersist 
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
