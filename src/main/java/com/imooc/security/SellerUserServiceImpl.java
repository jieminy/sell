package com.imooc.security;

import com.imooc.dataobject.SellerInfo;
import com.imooc.seller.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by JieMin
 * 2017-06-18 23:31
 */
public class SellerUserServiceImpl implements UserDetailsService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SellerInfo sellerInfo = sellerInfoRepository.findByUsername(s);
        return sellerInfo;
    }
}
