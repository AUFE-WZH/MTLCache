package MTLProject.Manager;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class MTLFunction {
    static class MTLParameter {
        private Class<?> mtlParameterClass;
        private Object mtlParameterValue;

        private MTLParameter() {

        }

        public MTLParameter(Class<?> mtlParameterClass, Object mtlParameterValue) {
            this.mtlParameterClass = mtlParameterClass;
            this.mtlParameterValue = mtlParameterValue;
        }

        public static MTLParameter getEmptyMTLParameter() {
            return new MTLParameter();
        }

        public Class<?> getMtlParameterClass() {
            return mtlParameterClass;
        }

        public Object getMtlParameterValue() {
            return mtlParameterValue;
        }

        public void setMtlParameterClass(Class<?> mtlParameterClass) {
            this.mtlParameterClass = mtlParameterClass;
        }

        public void setMtlParameterValue(Object mtlParameterValue) {
            this.mtlParameterValue = mtlParameterValue;
        }

        public static boolean checkMTLParameter(MTLParameter mtlParameter1, MTLParameter mtlParameter2) {
            return Objects.equals(mtlParameter1.mtlParameterClass, mtlParameter2.mtlParameterClass) &&
                    Objects.equals(mtlParameter1.mtlParameterValue, mtlParameter2.mtlParameterValue);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MTLParameter that = (MTLParameter) o;
            return Objects.equals(mtlParameterClass, that.mtlParameterClass) && Objects.equals(mtlParameterValue, that.mtlParameterValue);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + (mtlParameterClass == null ? 0 : mtlParameterClass.hashCode());
            hash = 31 * hash + (mtlParameterValue == null ? 0 : mtlParameterValue.hashCode());
            return hash;
        }

        @Override
        public String toString() {
            return "MTLParameter{" +
                    "mtlParameterClass = " + mtlParameterClass +
                    ", mtlParameterValue = " + mtlParameterValue +
                    "}";
        }
    }

    private Class<?> mtlClass;
    private Method mtlMethod;
    private List<MTLParameter> mtlParameterList;
    private Class<?> mtlReturnClass;

    private MTLFunction() {

    }

    public MTLFunction(
            Class<?> mtlClass,
            Method mtlMethod,
            List<MTLParameter> mtlParameterList,
            Class<?> mtlReturnClass) {
        this.mtlClass = mtlClass;
        this.mtlMethod = mtlMethod;
        this.mtlParameterList = mtlParameterList;
        this.mtlReturnClass = mtlReturnClass;
    }

    public static MTLFunction getEmptyMTLFunction() {
        return new MTLFunction();
    }

    public Class<?> getMtlClass() {
        return mtlClass;
    }

    public Method getMtlMethod() {
        return mtlMethod;
    }

    public List<MTLParameter> getMtlParameterList() {
        return mtlParameterList;
    }

    public Class<?> getMtlReturnClass() {
        return mtlReturnClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MTLFunction that = (MTLFunction) o;
        if (Objects.equals(mtlClass, that.mtlClass) &&
                Objects.equals(mtlMethod, that.mtlMethod) &&
                Objects.equals(mtlReturnClass, that.mtlReturnClass)) {
            if (mtlParameterList.size() == that.mtlParameterList.size()) {
                for (int i = 0; i < mtlParameterList.size(); i++) {
                    if (!MTLParameter.checkMTLParameter(mtlParameterList.get(i), that.mtlParameterList.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (mtlClass == null ? 0 : mtlClass.hashCode());
        hash = 31 * hash + (mtlMethod == null ? 0 : mtlMethod.hashCode());
        hash = 31 * hash + (mtlParameterList == null ? 0 : mtlParameterList.hashCode());
        hash = 31 * hash + (mtlReturnClass == null ? 0 : mtlReturnClass.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder parameterStringList = new StringBuilder();
        if (mtlParameterList.size() == 0) {
            parameterStringList.append("void");
        } else {
            for (int i = 0; i < mtlParameterList.size(); i++) {
                parameterStringList.append(mtlParameterList.get(i).toString());
                if (i != mtlParameterList.size() - 1) {
                    parameterStringList.append(", ");
                }
            }
        }

        return "MTLFunction{" + System.lineSeparator() +
                "mtlClass = " + mtlClass + System.lineSeparator() +
                "mtlMethod = " + mtlMethod + System.lineSeparator() +
                "mtlParameterList = " + parameterStringList + System.lineSeparator() +
                "mtlReturn = " + mtlReturnClass + System.lineSeparator() +
                "}";
    }
}