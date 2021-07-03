package MTLProject;

import MTLProject.Manager.MTLManager;
import org.junit.Test;

public class UserDao {
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
}
