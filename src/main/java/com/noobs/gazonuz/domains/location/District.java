package com.noobs.gazonuz.domains.location;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Table(name = "district")@Lazy
public class District {


    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private  Region region;

}
