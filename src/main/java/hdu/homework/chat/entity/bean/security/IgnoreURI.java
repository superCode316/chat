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
        if (ignoreURI.contains(uri))
            return true;
        final String[] uriSplit = uri.split("/");
        return ignoreURI.stream().map(s -> s.split("/")).anyMatch(strings -> match(uriSplit, strings));
    }
    private boolean match(String[] s1, String[] s2) {
        if (s1.length + 1 < s2.length || !s2[s2.length - 1].equals("**")) return false;
        for (int i = 1; i < s2.length-1; i++) {
            if (!s1[i].equals(s2[i]))
                return false;
        }
        return true;
    }
}
