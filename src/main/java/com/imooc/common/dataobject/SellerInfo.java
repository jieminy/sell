package com.imooc.common.dataobject;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by JieMin
 * 2017-07-23 23:02
 */
@Data
@Entity
@Table(name = "seller_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class SellerInfo implements UserDetails {

    @Id
    @Column(length = 32)
    private String sellerId;


    @Column(length = 64)
    private String username;

    @Column(length = 32)
    private String password;

    private boolean admin;

    private Date createTime;
    private Date updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
