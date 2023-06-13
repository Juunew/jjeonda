package com.fintech.jjeondaproject.entity.openBanking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "openbanking")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class OBTokenEntity {
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
    @Column(nullable = true)
    private Long userId;
}
