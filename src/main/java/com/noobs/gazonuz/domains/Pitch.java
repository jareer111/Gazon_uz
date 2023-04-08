package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.enums.PitchStatus;
import com.noobs.gazonuz.utils.Utils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Setter
@Getter
//@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pitch {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    @Column( nullable = false )
    private String name;
    //    @Column( nullable = false )
    private String latitude;
    //    @Column( nullable = false )
    private String longitude;
    private String info;

    @Column( name = "full_address" )
    private String fullAddress;
    @Column( columnDefinition = "int default 0" )
    private int likes;
    @Column( columnDefinition = "int default 0" )
    private int dislikes;

    @Column( columnDefinition = "smallint default 0" )
    private Byte rating;
    @OneToMany( cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Collection<Document> documents;

//    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pitch" )
////    @ToString.Exclude
//    private Collection<Order> orders;

//    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pitch" )
////    @ToString.Exclude
//    private Collection<Comment> comments;

    private double price;
    private String phoneNumber;

    @ManyToOne( cascade = CascadeType.MERGE )
    private User user;
    private PitchStatus status = PitchStatus.REQUESTED;
    @ManyToOne
    private District district;

    @CreationTimestamp
    @Column( name = "created_at",columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;

    public String getCreatedAt() {
        return Utils.DATE_TIME_FORMATTER.format(this.createdAt);
    }
}