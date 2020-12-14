package algorithm.aliyun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Iscompare {

    public boolean isComABoolean(String s, String t) {
        int len = s.length();
        if (len == 0) {
            return false;
        }
        Map<Character, Character> pareMap = new HashMap<>();
        List<Character> exits = new ArrayList<>();
        for (int i = 0; i < len; i ++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            Character mapS = pareMap.get(sc);
            if (mapS == null) {
                if (!exits.contains(tc)) {
                    pareMap.put(sc, tc);
                    exits.add(tc);
                } else {
                    return false;
                }
            } else {
                if (mapS != tc) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        String t = "abcdefg", s = "bbhaksp";
        log.info(isComABoolean(t, s) + "");
    }

}
