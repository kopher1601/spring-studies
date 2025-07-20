package jp.kopher1601.splearn.adapter;

import jp.kopher1601.splearn.domain.member.DuplicateEmailException;
import jp.kopher1601.splearn.domain.member.DuplicateProfileException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

// ResponseEntityExceptionHandler -> Spring 이 정의한 거의 대부분의 예외가 있다

@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @NotNull
    private static ProblemDetail getProblemDetail(HttpStatus status, Exception exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, exception.getMessage());
        problemDetail.setProperty("exception", exception.getClass().getSimpleName());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exception) {
        return getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    // ProblemDetail : Spring 6.0
    // ProblemDetail, 즉 표준(RFC 9457)을 사용함으로써 { data: ..., status: ... message: ... }같은 응답값을 직접 만들어서 반환할 필요가 없어졌다.
    // {"type":"about:blank","title":"Conflict","status":409,"detail":"email is already registered","instance":"/api/members"}
    @ExceptionHandler({DuplicateEmailException.class, DuplicateProfileException.class})
    public ProblemDetail emailExceptionHandler(DuplicateEmailException exception) {
        // RFC 9457
        return getProblemDetail(HttpStatus.CONFLICT, exception);
    }
}
