package hdu.homework.chat.handler;

import hdu.homework.chat.entity.bean.response.Msg;
import hdu.homework.chat.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

/**
 * created by 钱曹宇@supercode on 3/14/2020
 */
@ControllerAdvice
@Slf4j
public class SQLHandler {
    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<Msg<?>> internalServerError(HttpServletRequest request, Exception e) {
        log.error("an error occurred" + e.getLocalizedMessage());
        e.printStackTrace();
        return ResultUtil.error(getReason(e));
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Msg<?>> duplicateEntryError(HttpServletRequest request, Exception e) {
        StackTraceElement[] element = e.getStackTrace();
        Throwable[] throwables = e.getSuppressed();
        return ResultUtil.error(HttpStatus.BAD_REQUEST,"记录重复");
    }
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<Msg<?>> emptyResultError(HttpServletRequest request, Exception e) {
        StackTraceElement[] element = e.getStackTrace();
        Throwable[] throwables = e.getSuppressed();
        return ResultUtil.error(HttpStatus.NOT_FOUND);
    }

    private String getReason(Exception e) {
        return e.getMessage().split("java.sql.SQLException: ")[1];
    }
}
