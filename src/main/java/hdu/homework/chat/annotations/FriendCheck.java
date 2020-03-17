package hdu.homework.chat.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FriendCheck {
    String targetName() default "fid";
}
