package dev.jombi.godmeteor.communtiy.JJE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post extends PostDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String contents;
}
