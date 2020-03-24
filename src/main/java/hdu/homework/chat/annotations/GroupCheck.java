package hdu.homework.chat.annotations;

import java.lang.annotation.*;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GroupCheck {
    boolean checkExist() default false;
    boolean checkIn() default false;
    boolean checkNotIn() default false;
}
