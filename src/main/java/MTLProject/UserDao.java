package MTLProject;

import MTLProject.Manager.MTLManager;

public class UserDao {
    private UserDao proxy;

    public void setProxy(UserDao proxy) {
        this.proxy = proxy;
    }

    public void save() {
        System.out.println("UserDao save");
    }

    public int add(int a, int b) {
        System.out.println("UserDao add(" + a + ", " + b + ")");
        return a + b;
    }

    public long fun1(){
        System.out.println("UserDao fun1");
        long result = 1;
        for (int i = 1; i < 100000000; i++) {
            result += i;
        }

        return result;
    }

    public void aFun() {
        System.out.println("UserDao aFun");
        proxy.bFun();
    }

    public void bFun() {
        System.out.println("UserDao bFun");
        proxy.fun1();
    }

}
