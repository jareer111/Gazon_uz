package com.noobs.gazonuz.domains.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Permission {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    @Column( columnDefinition = "varchar default gen_random_uuid()" )
    private String id;
    @Column( nullable = false )
    private String name;
    @Column( nullable = false, unique = true )
    private String code;

}
