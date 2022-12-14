package com.hau.ketnguyen.it.controller.hau;

import com.hau.ketnguyen.it.model.dto.auth.UserDTO;
import com.hau.ketnguyen.it.model.request.SignupRequest;
import com.hau.ketnguyen.it.model.request.TokenRefreshRequest;
import com.hau.ketnguyen.it.model.response.APIResponse;
import com.hau.ketnguyen.it.model.response.TokenRefreshResponse;
import com.hau.ketnguyen.it.model.response.UserResponse;
import com.hau.ketnguyen.it.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh-token")
    public ResponseEntity<APIResponse<TokenRefreshResponse>> tokenRefresh(@RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(APIResponse.success(authService.refreshToken(request)));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse<UserDTO>> signup(@RequestBody @Valid SignupRequest request, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(APIResponse.success(authService.signup(request, servletRequest)));
    }

    @GetMapping("/activation")
    public ResponseEntity<APIResponse<Boolean>> verifyAccount(@RequestParam String code) {
        return ResponseEntity.ok(APIResponse.success(authService.verifyAccount(code)));
    }

    @GetMapping("/account")
    public ResponseEntity<APIResponse<UserResponse>> getAccount() {
        return ResponseEntity.ok(APIResponse.success(authService.getInfo()));
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return ResponseEntity.ok(APIResponse.success());
    }
}
