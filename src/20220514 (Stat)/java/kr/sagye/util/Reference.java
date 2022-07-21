package kr.sagye.util;

public class Reference {
    public static final String MOD_ID = "omodstat";
    public static final String MOD_NAME = "OModStat";
    public static final String VERSION = "0.1";
    public static final String Client_Side = "kr.sagye.proxy.ClientProxy";
    public static final String Server_Side = "kr.sagye.proxy.CommonProxy";


    private static class InnerInstanceGameVariableClazz {
        private static final Reference uniqueGameVariable = new Reference();
    }

    public static Reference Instance() {
        return InnerInstanceGameVariableClazz.uniqueGameVariable;
    }

}
