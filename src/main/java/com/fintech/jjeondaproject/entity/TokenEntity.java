package com.fintech.jjeondaproject.entity;

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
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "access_token", columnDefinition = "text")
    private String accessToken;
    @Column(name = "expires_in")
    private int expiresIn;
    @Column(name = "refresh_token", columnDefinition = "text")
    private String refreshToken;
    @Column(name = "user_seq_no")
    private Long userSeqNo;
}
