package com.myAppium.Utils.uiUtil;

import java.io.IOException;

public class AutoTools {
    public static void runCMD(String cmd){
        try {
            Runtime.getRuntime().exec("cmd /c start " + cmd);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("执行window批处理命令失败：" + cmd);
        }
    }

    public static boolean isDecimals(String st){
        return st.contains(".");
    }

    public static int stringToInt(String st) throws NumberFormatException{
            return isDecimals(st)? (int) Double.parseDouble(st): Integer.parseInt(st);
    }
}
