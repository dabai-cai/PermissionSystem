package cn.scau.hjr.model;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class SystemData {
    private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> pagesize = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> specialUser = new ThreadLocal<Integer>();
    private static ThreadLocal<String>  searchUserName=new ThreadLocal<String>();
    public static void setPageOffset(Integer _pageOffset) {
        pageOffset.set(_pageOffset);
    }

    public static String getSearchUserName() {
        return searchUserName.get();
    }
    public static void setSearchUserName(String name)
    {
        searchUserName.set(name);
    }
    public static void removeSearchUserName() {
        searchUserName.remove();
    }

    public static Integer getSpecialUser() {
        return specialUser.get();
    }

    public static void setSpecialUser(Integer value) {
        specialUser.set(value);
    }

    public static void removeSpecialUser() {
        specialUser.remove();
    }

    public static Integer getPageOffset() {
        return (Integer) pageOffset.get();
    }

    public static void removePageOffset() {
        pageOffset.remove();
    }

    public static void setPageSize(int _pagesize) {
        pagesize.set(_pagesize);
    }

    public static void removePageSize() {
        pagesize.remove();
    }

    public static int getPageSize() {
        return pagesize.get();
    }

    private SystemData() {
    }
}
