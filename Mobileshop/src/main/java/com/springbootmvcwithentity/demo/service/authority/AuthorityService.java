package com.springbootmvcwithentity.demo.service.authority;

import com.springbootmvcwithentity.demo.entity.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> getAllAuthorities();

    Authority createAuthority(Authority authority);

    void deleteAuthority(String username);
}
