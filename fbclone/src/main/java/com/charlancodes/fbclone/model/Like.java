package com.charlancodes.fbclone.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "dolike")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "likeid", nullable = false)
    private Long likeId;

    @Column(name = "userid", nullable = false)
    private Long userId;

    @Column(name = "postid", nullable = false)
    private Long postId;

    @Column(name = "commentid", nullable = false)
    private Long commentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postid", referencedColumnName = "id", insertable = false, updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commentid", referencedColumnName = "id", insertable = false, updatable = false)
    private Comment comment;

    @Column(name = "timestamp", nullable = false, length = 100)
    private Timestamp timestamp;
}
