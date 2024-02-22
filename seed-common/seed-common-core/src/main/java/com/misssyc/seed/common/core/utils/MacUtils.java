package com.misssyc.seed.common.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

/**
 * @author 33992
 * @since 2020/7/9 09:11
 */
@Slf4j
public class MacUtils {

    private static String mac = "";

    private MacUtils() {
    }

    private static String getMac() {
        if (StringUtils.isNotEmpty(mac)) {
            return mac;
        }
        mac = getMacAddress();
        return mac;
    }

    private static String getMacAddress() {
        log.debug("获取 mac 地址");
        StringBuilder sb = new StringBuilder("0");

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface != null) {
                    byte[] bytes = networkInterface.getHardwareAddress();
                    sb.append(byteToString(bytes));
                }
            }
        } catch (Exception err) {
            log.error("{}", ExceptionUtils.getExceptionDetail(err));
        }
        return sb.toString();
    }

    private static String byteToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes != null) {
            for (int i = 0; i < bytes.length; i++) {
                if (i != 0) {
                    stringBuilder.append(":");
                }
                int tmp = bytes[i] & 0xff; // 字节转换为整数
                String str = Integer.toHexString(tmp);
                if (str.length() == 1) {
                    stringBuilder.append("0").append(str);
                } else {
                    stringBuilder.append(str);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String encryptMac() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] secretBytes = md.digest((getMac()).getBytes());
            StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code.insert(0, "0");
            }
            return md5code.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("{}", ExceptionUtils.getExceptionDetail(e));
            return mac;
        }
    }
}
