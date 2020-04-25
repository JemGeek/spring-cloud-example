package com.james.example.cloud.oauth.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * @author James
 * @date 2020/4/23
 */
@Data
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken implements Serializable {
    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "token")
    private byte[] token;

    @Column(name = "authentication")
    private byte[] authentication;

    private static final long serialVersionUID = 1L;
}