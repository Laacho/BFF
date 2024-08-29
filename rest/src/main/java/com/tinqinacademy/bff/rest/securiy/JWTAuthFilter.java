package com.tinqinacademy.bff.rest.securiy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTOutput;
import com.tinqinacademy.authenticationHotel.restExport.RestExportAuthentication;
import com.tinqinacademy.bff.rest.context.UserContex;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.tinqinacademy.authenticationHotel.api.models.operations.validateJWT.ValidateJWTInput;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter  extends OncePerRequestFilter {
    private final RestExportAuthentication restExportAuthentication;
    private final ObjectMapper objectMapper;
    private final UserContex userContex;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String jwt = authHeader.substring(7);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                ValidateJWTInput validateInput = ValidateJWTInput.builder()
                        .jwt(jwt)
                        .build();

                Boolean validatedToken = restExportAuthentication.isJwtValid(validateInput)
                        .getValidatedToken();
                if (validatedToken) {
                    Map<String, String> payloadMap = getPayload(jwt);

                    MyUser user = MyUser.builder()
                            .username(payloadMap.get("user_id"))
                            .role(payloadMap.get("role"))
                            .build();

                    CustomAuthenticationToken authToken = new CustomAuthenticationToken(user);
                    userContex.setUserId(user.getUsername());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private Map<String, String> getPayload(String jwt) throws JsonProcessingException {
        String[] parts = jwt.split("\\.");

        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        return objectMapper.readValue(payload, Map.class);
    }
}
