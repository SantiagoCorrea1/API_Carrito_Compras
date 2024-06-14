package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Dto.AuthenticationRequest;
import com.santiago.carrito_compras.Dto.SingUpRequestDto;
import com.santiago.carrito_compras.Dto.UserDto;
import com.santiago.carrito_compras.Entities.User;
import com.santiago.carrito_compras.Repositories.UserRepository;
import com.santiago.carrito_compras.Services.AuthService;
import com.santiago.carrito_compras.Services.UserDetailsServiceImpl;
import com.santiago.carrito_compras.Util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    private final AuthService authService;

    final
    AuthenticationManager authenticationManager;
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthenticationController(AuthService authService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }


    @PostMapping("/user/sing-up")
    public ResponseEntity<?> singUpUser(@RequestBody SingUpRequestDto singUpRequestDto){
        if(authService.presentByEmail(singUpRequestDto.getEmail())){
            return new ResponseEntity<>("User already exits with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.SingUpClient(singUpRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public void createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest,
                                           HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getName(), authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("UserName or password incorrect", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getName());
        User user = userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), user.getId());


        response.getWriter().write(new JSONObject()
                .put("token", jwt)
                .toString()
        );
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization,"+
        " X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);
    }
}
