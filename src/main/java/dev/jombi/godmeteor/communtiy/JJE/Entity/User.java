package dev.jombi.godmeteor.communtiy.JJE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    @PrePersist
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
