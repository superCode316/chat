package hdu.homework.chat.handler;

import hdu.homework.chat.entity.bean.Msg;
import hdu.homework.chat.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class CommonHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {EmptyResultDataAccessException.class, SQLSyntaxErrorException.class})
    public ResponseEntity<Msg<?>> internalServerError(HttpServletRequest request, Exception e) {
        logger.error("an error occurred" + e.getLocalizedMessage());
        return ResultUtil.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
