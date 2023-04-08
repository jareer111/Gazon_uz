package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.domains.auth.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    //    @Column(nullable = false)
//    private String title;

    @Column( nullable = false )
    private String body;


    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @ToString.Exclude
    private User user;
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @ToString.Exclude
    private Pitch pitch;
    @CreationTimestamp
    @Column( columnDefinition = "timestamp default now()" )
    private LocalDateTime createdAt;
    @Column( columnDefinition = "boolean default false" )
    private Boolean isDeleted;
}
