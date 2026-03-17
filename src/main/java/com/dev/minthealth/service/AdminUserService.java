package com.dev.minthealth.service;

import com.dev.minthealth.dto.AdminCreateUserRequest;
import com.dev.minthealth.dto.AdminCreateUserResponse;

public interface AdminUserService {
    AdminCreateUserResponse createUser(AdminCreateUserRequest request);
}

