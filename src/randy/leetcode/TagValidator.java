package randy.leetcode;

import java.util.Stack;

// 591 https://leetcode-cn.com/problems/tag-validator/
public class TagValidator {
    public boolean isValid(String code) {
        if (code == null || code.isEmpty()) return true;

        char[] str = code.toCharArray();

        boolean hasRoot = false;
        Status status = Status.CONTENT;
        Stack<String> stack = new Stack<>();
        StringBuilder token = new StringBuilder();

        for (char c: str) {
            switch (status) {
                case START_TAG:
                    if (token.length() == 0) {
                        if (c == '/') {
                            status = Status.END_TAG;
                        } else if (c == '!') {
                            status = Status.CDATA;
                        } else if (Character.isUpperCase(c) && token.length() < 9) {
                            token.append(c);
                        } else {
                            return false;
                        }
                    } else if (c == '>') {
                        if (hasRoot && stack.isEmpty()) {
                            return false;
                        }
                        hasRoot = true;
                        stack.push(token.toString());
                        token.setLength(0);
                        status = Status.CONTENT;
                    } else if (Character.isUpperCase(c) && token.length() < 9) {
                        token.append(c);
                    } else {
                        return false;
                    }
                    break;

                case END_TAG:
                    if (c == '>') {
                        if (stack.isEmpty()) {
                            return false;
                        }
                        String tag = stack.pop();
                        if (tag.equals(token.toString())) {
                            token.setLength(0);
                            status = Status.CONTENT;
                        } else {
                            return false;
                        }
                    } else if (Character.isUpperCase(c) && token.length() < 9) {
                        token.append(c);
                    } else {
                        return false;
                    }
                    break;

                case CDATA:
                    token.append(c);
                    if (token.length() == 7) {
                        String start = token.toString();
                        if (!start.equals("[CDATA[")) {
                            return false;
                        }
                    } else if (c == '>' && token.length() >= 10) {
                        String end = token.substring(token.length() - 3);
                        if (end.equals("]]>")) {
                            if (stack.isEmpty()) {
                                return false;
                            } else {
                                token.setLength(0);
                                status = Status.CONTENT;
                            }
                        }
                    }
                    break;

                default:
                    if (c == '<') {
                        if (token.length() > 0) {
                            if (stack.isEmpty()) {
                                return false;
                            }
                            token.setLength(0);
                        }
                        status = Status.START_TAG;
                    } else {
                        token.append(c);
                    }
                    break;
            }
        }

        return status == Status.CONTENT && stack.isEmpty() && token.length() == 0;
    }

    enum Status {
        START_TAG,
        END_TAG,
        CDATA,
        CONTENT
    }

    public static void main(String[] args) {
        TagValidator tagValidator = new TagValidator();
        System.out.println(tagValidator.isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));
        System.out.println(tagValidator.isValid("<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"));
        System.out.println(tagValidator.isValid("<A>  <B> </A>   </B>"));
        System.out.println(tagValidator.isValid("<DIV>  div tag is not closed  <DIV>"));
        System.out.println(tagValidator.isValid("<DIV>  unmatched <  </DIV>"));
        System.out.println(tagValidator.isValid("<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"));
        System.out.println(tagValidator.isValid("<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"));
        System.out.println(tagValidator.isValid("<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"));
    }
}
