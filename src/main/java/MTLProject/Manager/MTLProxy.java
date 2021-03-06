package MTLProject.Manager;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.*;


/**
 * Cglib子类代理工厂
 * (对UserDao 在内存中动态构建一个子类对象)
 */
public class MTLProxy implements MethodInterceptor {
    // 维护目标对象
    private Object target;

    public MTLProxy(Object target) {
        this.target = target;
    }

    // 给目标对象创建代理对象
    public Object getProxyInstance() {
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    private MTLFunction getMTLFunction(Object obj, Method method, Object[] args) {
        Class<?> className = obj.getClass();
        Class<?>[] parameterTypes = method.getParameterTypes();
        List<MTLFunction.MTLParameter> mtlParameterList = new ArrayList<>();
        for (int i = 0; i < parameterTypes.length; i++) {
            mtlParameterList.add(new MTLFunction.MTLParameter(parameterTypes[i], args[i]));
        }

        return new MTLFunction(className, method, mtlParameterList, method.getReturnType());
    }

    private boolean preProcess(MTLFunction mtlFunction) {
        return MTLManager.getInstance().judgeExistAndManage(mtlFunction);
    }

    private void postProcess(MTLFunction mtlFunction, boolean cacheFlag, Object returnValue, long executionTimeMS) {
        MTLManager.getInstance().setMtlFunctionReturnValueAndManage(mtlFunction, cacheFlag, returnValue, executionTimeMS);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        long startTime = System.currentTimeMillis();
        MTLFunction mtlFunction = getMTLFunction(target, method, args);
        boolean cacheFlag = preProcess(mtlFunction);
        Object returnValue;
        // 已经缓存过了，直接返回返回值
        if (cacheFlag) {
            returnValue = MTLManager.getInstance().getFunctionResultValue(mtlFunction);
        } else {
            // 执行目标对象的方法
            returnValue = method.invoke(target, args);
        }
        long endTime = System.currentTimeMillis();
        postProcess(mtlFunction, cacheFlag, returnValue, endTime - startTime);
        return returnValue;
    }




}