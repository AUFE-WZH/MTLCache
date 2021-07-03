package MTLProject.Manager;

public class MTLFunctionCache {
    private MTLFunction mtlFunction;
    private Object mtlReturnValue;
    private int executionCounts;
    private long executionTimeMS;
    private int hitCounts;

    public MTLFunctionCache(MTLFunction mtlFunction) {
        this.mtlFunction = mtlFunction;
        this.mtlReturnValue = null;
        this.executionCounts = 0;
        this.executionTimeMS = 0;
        this.hitCounts = 0;
    }

    public void clear() {
        mtlReturnValue = null;
        executionCounts = 0;
        executionTimeMS = 0;
        hitCounts = 0;
    }

    public MTLFunction getMtlFunction() {
        return mtlFunction;
    }

    public int getExecutionCounts() {
        return executionCounts;
    }

    public int getHitCounts() {
        return hitCounts;
    }

    public void setExecutionCounts(int executionCounts) {
        this.executionCounts = executionCounts;
    }

    public void setHitCounts(int hitCounts) {
        this.hitCounts = hitCounts;
    }

    public Object getMtlReturnValue() {
        return mtlReturnValue;
    }

    public void setMtlReturnValue(Object mtlReturnValue) {
        this.mtlReturnValue = mtlReturnValue;
    }

    public long getExecutionTimeMS() {
        return executionTimeMS;
    }

    public void setExecutionTimeMS(long executionTimeMS) {
        this.executionTimeMS = executionTimeMS;
    }

    @Override
    public String toString() {
        return "MTLFunctionCache{" + System.lineSeparator() +
                "mtlFunction = " + System.lineSeparator() + mtlFunction + System.lineSeparator() +
                "mtlReturnValue = " + mtlReturnValue + System.lineSeparator() +
                "executionCounts = " + executionCounts + System.lineSeparator() +
                "executionTime = " + executionTimeMS + " ms" + System.lineSeparator() +
                "hitCounts = " + hitCounts + System.lineSeparator() +
                "}";
    }
}
