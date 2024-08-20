package com.example.contersback.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@ToString
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String ref;

    @Column
    private String aiRef;

    @Column
    @Enumerated(EnumType.STRING)
    private PhotoStatus status;

    @Column
    private String type;

    @Column
    private long userId;

    public Photo(String url) {
        this.ref = url;
        this.status = PhotoStatus.UNCHECKED;
        this.type = "mercury";
        this.userId = 0;
    }

}
