package MTLProject.Manager;

import java.util.List;

public class MTLExecutionTree {
    private MTLFunctionCache mtlFunctionCache;
    private boolean executionFlag;
    private List<MTLExecutionTree> internalExecutionFunctionList;

    public MTLExecutionTree(MTLFunctionCache mtlFunctionCache, boolean executionFlag, List<MTLExecutionTree> internalExecutionFunctionList) {
        this.mtlFunctionCache = mtlFunctionCache;
        this.executionFlag = executionFlag;
        this.internalExecutionFunctionList = internalExecutionFunctionList;
    }

    public MTLFunctionCache getMTLFunctionCache() {
        return mtlFunctionCache;
    }

    public void setMTLFunctionCache(MTLFunctionCache mtlFunctionCache) {
        this.mtlFunctionCache = mtlFunctionCache;
    }

    public boolean isExecutionFlag() {
        return executionFlag;
    }

    public void setExecutionFlag(boolean executionFlag) {
        this.executionFlag = executionFlag;
    }

    public List<MTLExecutionTree> getInternalExecutionFunctionList() {
        return internalExecutionFunctionList;
    }

    public void setInternalExecutionFunctionList(List<MTLExecutionTree> internalExecutionFunctionList) {
        this.internalExecutionFunctionList = internalExecutionFunctionList;
    }
}
