package com.project.epharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import java.util.Date;
@Entity
@Table(name = "user_token")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@Data
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String token;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault("1")
    private Integer active;
}
