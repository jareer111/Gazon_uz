package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.domains.auth.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "favorite_pitch", uniqueConstraints = {
        @UniqueConstraint( columnNames = {"pitch_id" , "user_id"} )
} )
public class FavoritePitch {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;


    @OneToOne
    private Pitch pitch;

    @OneToOne
    private User user;


}
