package MTLProject;

import MTLProject.Factory.Person;
import MTLProject.Factory.PersonFactory;
import MTLProject.Manager.MTLManager;
import MTLProject.Manager.MTLStorage;
import org.junit.Test;

import java.io.IOException;

public class MTLProjectTest {
    // 测试缓存Java函数
    @Test
    public void test1() {
        MTLManager mtlManager = MTLManager.getInstance();
        UserDao proxy = (UserDao) mtlManager.registerCacheClass(new UserDao());
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

    // 测试json序列化
    @Test
    public void test2() {
        Person person = PersonFactory.newPerson("小王", 26);
        try {
            MTLStorage.writeObjectToFile(person, "src/main/java/MTLProject/tttt.txt");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // 测试json反序列化
    @Test
    public void test3() {
        try {
            Person person = (Person) MTLStorage.readObjectFromFile("src/main/java/MTLProject/tttt.txt", Person.class);
            System.out.println(person.toString());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    // 测试递归调用
    @Test
    public void test4() {
        MTLManager mtlManager = MTLManager.getInstance();
        UserDao proxy = (UserDao) mtlManager.registerCacheClass(new UserDao());
        proxy.aFun();
        proxy.aFun();
        proxy.bFun();
        proxy.bFun();
//        mtlManager.showAllManagedClass();
//        mtlManager.showAllManagedFunction();
        mtlManager.showExecutionSequence();
    }

    // 测试函数调用栈
    @Test
    public void test5() {
        MTLManager mtlManager = MTLManager.getInstance();
        UserDao proxy = (UserDao) mtlManager.registerCacheClass(new UserDao());
//        proxy.aFun();
//        proxy.dFun();

        mtlManager.showExecutionTree();
    }
}
