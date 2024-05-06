package br.victoremerick.esales.user.api;

import br.victoremerick.esales.user.service.UserService;
import br.victoremerick.esales.user.model.dto.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/isAuthenticated")
    public ResponseEntity<?> isAuthenticated(
            Principal connectedUser
    ) {
        return ResponseEntity.ok(connectedUser.getName());
    }
}
