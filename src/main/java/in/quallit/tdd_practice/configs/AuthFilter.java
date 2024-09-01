package in.quallit.tdd_practice.configs;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import in.quallit.tdd_practice.services.security.CustomUserDetailService;
import in.quallit.tdd_practice.services.security.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * @author Jigs
 */
@Component
public class AuthFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private CustomUserDetailService customUserDetailService;
    private HandlerExceptionResolver handlerExceptionResolver;

    public AuthFilter(JWTService jwtService,
                      CustomUserDetailService customUserDetailService,
                      HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.customUserDetailService = customUserDetailService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String token = authHeader.substring(7);
            String username = this.jwtService.verifyToken(token);
            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        } catch (TokenExpiredException exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        } catch (SignatureVerificationException exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        } catch (JWTDecodeException exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
