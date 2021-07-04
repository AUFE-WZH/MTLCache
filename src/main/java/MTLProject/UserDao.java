package MTLProject;

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
        long result = 1;
        for (int i = 1; i < 100000000; i++) {
            result += i;
        }
        proxy.bFun();
    }

    public void bFun() {
        System.out.println("UserDao bFun");
        long result = 1;
        for (int i = 1; i < 100000000; i++) {
            result += i;
        }
        proxy.fun1();
    }

    public void cFun() {
        System.out.println("UserDao cFun");
    }

    public void dFun() {
        System.out.println("UserDao dFun");
        proxy.fun1();
        proxy.add(1, 2);
        proxy.aFun();
    }

}
