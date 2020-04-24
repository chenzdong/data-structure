package backtrack;

/**
 * 正则匹配
 * “*”匹配任意多个（大于等于 0 个）任意字符
 * “?”匹配零个或者一个任意字符
 * @author: czd
 * @create: 2020-04-24 13:20
 */
public class Pattern {
    private boolean matched = false;
    /**
     * 正则表达式及其长度
     */
    private char[] pattern;
    private int plen;

    public Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    /**
     * 文本是否匹配
     * @param text 文本
     * @param tlen 文本长度
     * @return 匹配与否
     */
    public boolean match(char[] text, int tlen) {
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }
    /**
     * 文本匹配正则表达式
     * @param ti 文本index
     * @param pi 正则表达式index
     * @param text 文本
     * @param tlen 文本长度
     */
    private void rmatch(int ti, int pi, char[] text, int tlen) {
        // 终止条件:已经匹配了直接跳出
        if (matched) {return;}
        // 终止条件：文本和正则表达式匹配上了
        if (pi == plen) {
            if (ti == tlen) {
                matched =true;
            }
            return;
        }
        // 根据不同规则匹配
        // *匹配任意个字符
        // ?匹配0或者1个字符
        if (pattern[pi] == '*') {
            for (int i = 0; i <= tlen - ti; i++) {
                rmatch(ti+i, pi+1, text, tlen);
            }
        } else if (pattern[pi] == '?') {
            rmatch(ti, pi+1, text, tlen);
            rmatch(ti+1, pi+1, text, tlen);
        } else if(ti < tlen && pattern[pi] == text[ti]){
            rmatch(ti+1, pi+1, text, tlen);
        }
    }

    public static void main(String[] args) {
        Pattern pattern = new Pattern("a*b?".toCharArray(), 4);
        System.out.println(pattern.match("baacbl".toCharArray(), 6));
    }
}
