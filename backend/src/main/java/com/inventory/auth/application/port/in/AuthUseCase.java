package com.inventory.auth.application.port.in;

import com.inventory.auth.application.port.in.dto.request.LoginRequest;
import com.inventory.auth.application.port.in.dto.request.RegisterRequest;
import com.inventory.auth.application.port.in.dto.response.AuthResponse;

/**
 * Inbound port — defines what the auth use cases expose to the outside world.
 * Controllers depend on this interface, not on the concrete service (DIP).
 */
public interface AuthUseCase {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
