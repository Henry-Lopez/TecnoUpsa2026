package com.aguafutura.platform.iam.application.port;

import com.aguafutura.platform.iam.domain.User;

public interface JwtTokenPort {

    String generateAccessToken(User user);
}