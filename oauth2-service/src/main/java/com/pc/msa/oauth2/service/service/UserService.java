package com.pc.msa.oauth2.service.service;

import com.pc.msa.oauth2.service.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser save(AppUser user);
    List<AppUser> findAll();
    void delete(long id);
}
