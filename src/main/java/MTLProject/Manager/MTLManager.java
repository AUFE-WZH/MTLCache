package MTLProject.Manager;

import java.util.*;

public class MTLManager {
    // 缓存函数、返回值、调用次数和命中次数
    private Map<MTLFunction, MTLFunctionCache> managedFunctionMap;

    // 缓存注册的类的实例
    private Set<Object> classInstanceSet;

    // 函数执行序列
    private List<MTLFunctionCache> executionSequence;

    // 单例模式
    private MTLManager() {
        managedFunctionMap = new HashMap<>();
        classInstanceSet = new HashSet<>();
        executionSequence = new ArrayList<>();
    }

    private static class MTLManagerInstance{
        private static final MTLManager instance = new MTLManager();
    }

    public static MTLManager getInstance(){
        return MTLManagerInstance.instance;
    }

    public Object registerCacheClass(Object target) {
        Object proxyInstance = new MTLProxy(target).getProxyInstance();
        classInstanceSet.add(target.getClass());
        return proxyInstance;
    }

    protected boolean judgeExistAndManage(MTLFunction mtlFunction) {
        boolean isExist;
        if (managedFunctionMap.containsKey(mtlFunction)) {
            MTLFunctionCache mtlFunctionCache = managedFunctionMap.get(mtlFunction);
            mtlFunctionCache.setHitCounts(mtlFunctionCache.getHitCounts() + 1);
            isExist = true;
        } else {
            MTLFunctionCache mtlFunctionCache = new MTLFunctionCache(mtlFunction);
            mtlFunctionCache.setExecutionCounts(1);
            managedFunctionMap.put(mtlFunction, mtlFunctionCache);
            isExist = false;
        }
        executionSequence.add(managedFunctionMap.get(mtlFunction));
        return isExist;
    }

    public Object getFunctionResultValue(MTLFunction mtlFunction) {
        if (managedFunctionMap.containsKey(mtlFunction)) {
            return managedFunctionMap.get(mtlFunction).getMtlReturnValue();
        } else {
            return new Exception();
        }
    }

    void setMtlFunctionReturnValue(MTLFunction mtlFunction, Object returnValue, long executionTimeMS) {
        if (managedFunctionMap.containsKey(mtlFunction)) {
            MTLFunctionCache mtlFunctionCache = managedFunctionMap.get(mtlFunction);
            mtlFunctionCache.setMtlReturnValue(returnValue);
            mtlFunctionCache.setExecutionTimeMS(executionTimeMS);
        }
    }

    public int getAllManagedFunctionNumber() {
        return managedFunctionMap.size();
    }

    public int getProxyInstanceSet() {
        return classInstanceSet.size();
    }

    public void showAllManagedFunction() {
        System.out.println("all managed function {");
        for (Map.Entry<MTLFunction, MTLFunctionCache> mtlFunctionMTLFunctionCacheEntry : managedFunctionMap.entrySet()) {
            MTLFunctionCache mtlFunctionCache = mtlFunctionMTLFunctionCacheEntry.getValue();
            System.out.println(mtlFunctionCache.toString());
        }
        System.out.println("}");
    }

    public void showAllManagedClass() {
        System.out.print("all managed class { ");
        Object[] classArray = classInstanceSet.toArray();
        for (int i = 0; i < classArray.length; i++) {
            System.out.print(classArray[i]);
            if (i != classArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }

    String getFunctionWithResult(MTLFunctionCache mtlFunctionCache) {
        if (mtlFunctionCache != null) {
            MTLFunction mtlFunction = mtlFunctionCache.getMtlFunction();
            String methodString = mtlFunction.getMtlMethod().toString().split("\\(")[0];
            List<MTLFunction.MTLParameter> parameterList = mtlFunction.getMtlParameterList();

            String returnValueString;
            if (mtlFunctionCache.getMtlReturnValue() == null) {
                returnValueString = "";
            } else {
                returnValueString = mtlFunctionCache.getMtlReturnValue().toString();
            }

            StringBuilder functionWithResult = new StringBuilder();
            functionWithResult.append(methodString).append("(");
            for (int i = 0; i < parameterList.size(); i++) {
                String cls = parameterList.get(i).getMtlParameterClass().toString();
                String value = parameterList.get(i).getMtlParameterValue().toString();
                functionWithResult.append(cls).append("{");
                functionWithResult.append(value).append("}");
                if (i != parameterList.size() - 1) {
                    functionWithResult.append(", ");
                }
            }
            functionWithResult.append(") = {");
            functionWithResult.append(returnValueString).append("}");
            return functionWithResult.toString();
        } else {
            return "";
        }
    }

    public void showExecutionSequence() {
        Set<MTLFunction> executionSet = new HashSet<>();
        System.out.println("function execution sequence {");
        for (int i = 0; i < executionSequence.size(); i++) {
            MTLFunctionCache mtlFunctionCache = executionSequence.get(i);
            MTLFunction mtlFunction = mtlFunctionCache.getMtlFunction();
            if (executionSet.contains(mtlFunction)) {
                System.out.println(getFunctionWithResult(mtlFunctionCache) + ", No Execution" );
            } else {
                executionSet.add(mtlFunction);
                long executionTimeMS = mtlFunctionCache.getExecutionTimeMS();
                System.out.println(getFunctionWithResult(mtlFunctionCache) + ", Execution = " + executionTimeMS + " ms");
            }

        }
        System.out.println("}");
    }
}
