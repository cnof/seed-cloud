package com.misssyc.seed.common.core.utils;

/**
 * @author 李升平
 */
public class ExceptionUtils {

    private ExceptionUtils() {}
    /**
     * 获取异常的详细信息
     * */
    public static String getExceptionDetail(Exception ex) {
        StringBuilder stringBuffer = new StringBuilder(ex.toString() + "\n");
        stringBuffer.append("\t").append(ex.getMessage()).append("\n");
        StackTraceElement[] messages = ex.getStackTrace();
        for (StackTraceElement message : messages) {
            stringBuffer.append("\t").append(message.toString()).append("\n");
        }
        return stringBuffer.toString();
    }
}
