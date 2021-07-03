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

    @Test
    public void test() {
        MTLManager mtlManager = MTLManager.getInstance();
        UserDao proxy = (UserDao) mtlManager.registerClass(new UserDao());
        System.out.println(mtlManager.getAllManagedFunctionNumber());
        proxy.save();
        proxy.save();
        System.out.println(mtlManager.getAllManagedFunctionNumber());
        proxy.add(2, 4);
        proxy.add(2, 4);
        proxy.add(10000, 100000);
        proxy.add(10000, 100000);
        proxy.add(10000, 100000);
        System.out.println(mtlManager.getAllManagedFunctionNumber());
        proxy.fun1();
        proxy.fun1();
        proxy.fun1();
        System.out.println(mtlManager.getAllManagedFunctionNumber());
//        mtlManager.showAllManagedClass();
//        mtlManager.showAllManagedFunction();
        mtlManager.showExecutionSequence();
    }

}
