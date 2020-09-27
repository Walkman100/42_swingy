package wtc.mcarter.swingy.util;

public class PadStr {
    public static String padRight(String str, long len, char c) {
        if (str.length() >= len) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < len) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static String padLeft(String str, long len, char c) {
        if (str.length() >= len) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        while (sb.length() < len - str.length()) {
            sb.append(c);
        }
        sb.append(str);

        return sb.toString();
    }

    public static String padRight(String str, long len) {
        return padRight(str, len, ' ');
    }

    public static String padLeft(String str, long len) {
        return padLeft(str, len, ' ');
    }

    public static String padRight(int n, long len) {
        return padRight(Integer.toString(n), len, ' ');
    }

    public static String padLeft(int n, long len) {
        return padLeft(Integer.toString(n), len, ' ');
    }
}
