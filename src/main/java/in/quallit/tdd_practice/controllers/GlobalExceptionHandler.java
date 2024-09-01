package in.quallit.tdd_practice.controllers;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @author Jigs
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ProblemDetail handleNoResourceFoundException(NoResourceFoundException exception) {
        exception.printStackTrace();
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()), exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException exception) {
        exception.printStackTrace();
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ProblemDetail handleTokenExpiredException(TokenExpiredException exception) {
        exception.printStackTrace();
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ProblemDetail handleSignatureVerificationException(SignatureVerificationException exception) {
        exception.printStackTrace();
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ProblemDetail handleJWTDecodeException(JWTDecodeException exception) {
        exception.printStackTrace();
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exception) {
        exception.printStackTrace();
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception.getMessage());
    }
}
