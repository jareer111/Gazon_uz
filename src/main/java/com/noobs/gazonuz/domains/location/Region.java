package com.noobs.gazonuz.domains.location;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Table(name = "region")
public class Region {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;
    private String name;

    @OneToMany(mappedBy = "region",fetch = FetchType.LAZY)
    private Collection<District> districts;


}
