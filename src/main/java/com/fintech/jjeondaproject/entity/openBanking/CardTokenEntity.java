package com.fintech.jjeondaproject.entity.openBanking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tokentable")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class CardTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "access_token", columnDefinition = "text")
    private String access_token;
    @Column(name = "expires_in")
    private int expires_in;
    @Column(name = "refresh_token", columnDefinition = "text")
    private String refresh_token;
    @Column(name = "user_seq_no")
    private Long user_seq_no;
}

