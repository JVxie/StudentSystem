package utils;

public class PathUtil {

    public static String getWebClassesPath() {
        String path = PathUtil.class.getProtectionDomain().getCodeSource()
                .getLocation().getPath();
        path = trim(path);
        return path;

    }

    public static String getWebInfPath() throws IllegalAccessException {
        String path = getWebClassesPath();
        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(0, path.indexOf("WEB-INF") + 8);
            path = trim(path);
        } else {
            throw new IllegalAccessException("路径获取错误");
        }
        return path;
    }

    private static String trim(String s){
        if(s.startsWith("/")||s.startsWith("\\")){
            s = s.substring(1);
            trim(s);
        }
        return s;
    };
}
