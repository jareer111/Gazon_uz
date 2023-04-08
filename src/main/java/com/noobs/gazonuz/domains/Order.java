package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table( name = "orders" )

public class Order {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    @Column( columnDefinition = "varchar default gen_random_uuid()" )
    private String id;

    @CreationTimestamp
    @Column( columnDefinition = "timestamp default current_timestamp", name = "created_at" )
    private LocalDateTime createdAt;


    @Column( name = "accepted_at" )
    private LocalDateTime acceptedAt;

    @Column( columnDefinition = "timestamp default now()", name = "start_time" )
    private LocalDateTime startTime;
    private int minutes;

    @Column( name = "order_status" )
    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.REQUESTED;


    @Column( columnDefinition = "boolean default false" )
    private Boolean isDeleted;
//    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.EAGER )
////    @ToString.Exclude
//    private User user;

//    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY )
////    @ToString.Exclude
//    private Pitch pitch;


    @Column( name = "user_id", nullable = false )
    private String userId;
    @Column( name = "pitch_id", nullable = false )
    private String pitchId;
}
