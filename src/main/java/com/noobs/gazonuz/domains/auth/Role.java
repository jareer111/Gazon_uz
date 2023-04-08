package com.noobs.gazonuz.domains.auth;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Role {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    @Column(columnDefinition = "varchar default gen_random_uuid()")
    private String id;
    @Column( nullable = false )
    private String name;
    @Column( nullable = false, unique = true )
    private String code;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable( name = "role_permissions", joinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "permission_id", referencedColumnName = "id" ) )
    private Collection<Permission> authPermissions;


//    @Override
//    public boolean equals(Object o) {
//        if ( this == o ) return true;
//        if ( o == null || getClass() != o.getClass() ) return false;
//        Role role = ( Role ) o;
//        return code.equals(role.code);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(code);
//    }
}
