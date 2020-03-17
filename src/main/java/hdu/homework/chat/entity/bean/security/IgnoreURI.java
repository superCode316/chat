package hdu.homework.chat.entity.bean.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/13/2020
 */
public class IgnoreURI {
    private List<String> ignoreURI;
    public void add(String... uris) {
        if (ignoreURI == null)
            ignoreURI = new ArrayList<>();
        ignoreURI.addAll(Arrays.asList(uris));
    }
    public boolean check(String uri) {
        return ignoreURI.contains(uri);
    }
}
