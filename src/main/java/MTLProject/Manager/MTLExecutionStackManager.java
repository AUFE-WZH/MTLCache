package MTLProject.Manager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MTLExecutionStackManager {
    // 函数执行树
    private MTLExecutionTree mtlExecutionTreeRoot;

    // 当前执行树节点
    private MTLExecutionTree currentMtlExecutionTree;

    // 函数调用栈
    private LinkedList<MTLExecutionTree> executionStack;

    public MTLExecutionStackManager() {
        mtlExecutionTreeRoot = new MTLExecutionTree(null, false, null);
        currentMtlExecutionTree = mtlExecutionTreeRoot;
        executionStack = new LinkedList<>();
        executionStack.addLast(currentMtlExecutionTree);
    }

    public void pushExecutionStack(MTLFunctionCache mtlFunctionCache, boolean executionFlag) {
        if (mtlFunctionCache != null) {
            MTLExecutionTree subMtlExecutionTree = new MTLExecutionTree(mtlFunctionCache, executionFlag, null);
            if (currentMtlExecutionTree.getInternalExecutionFunctionList() == null) {
                List<MTLExecutionTree> subMtlExecutionTreeList = new ArrayList<>();
                subMtlExecutionTreeList.add(subMtlExecutionTree);
                currentMtlExecutionTree.setInternalExecutionFunctionList(subMtlExecutionTreeList);
            } else {
                currentMtlExecutionTree.getInternalExecutionFunctionList().add(subMtlExecutionTree);
            }
            currentMtlExecutionTree = subMtlExecutionTree;
            executionStack.addLast(currentMtlExecutionTree);
        }
    }

    public void popExecutionStack(MTLFunctionCache mtlFunctionCache) {
        if (mtlFunctionCache != null && executionStack.size() > 1) {
            MTLFunctionCache stackMTLFunctionCache = executionStack.getLast().getMTLFunctionCache();
            if (Objects.equals(stackMTLFunctionCache, mtlFunctionCache)) {
                executionStack.removeLast();
                currentMtlExecutionTree = executionStack.getLast();
            }
        } else {
            System.out.println("Method call stack error！");
        }
    }

    public MTLExecutionTree getMtlExecutionTreeRoot() {
        return mtlExecutionTreeRoot;
    }

    public String showMTLExecutionTree() {
        return "";
    }
}
