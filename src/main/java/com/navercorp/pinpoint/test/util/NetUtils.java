package com.navercorp.pinpoint.test.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author Taejin Koo
 */
public class NetUtils {

    public static final String LOOPBACK_ADDRESS_V4 = "127.0.0.1";

    public static String getLocalV4Ip() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String localIp = localHost.getHostAddress();
            if (validationIpV4FormatAddress(localIp)) {
                return localIp;
            }
        } catch (UnknownHostException ignore) {
            // skip
        }
        return LOOPBACK_ADDRESS_V4;
    }

    public static boolean validationIpV4FormatAddress(String address) {
        try {
            String[] eachDotAddress = address.split("\\.");
            if (eachDotAddress.length != 4) {
                return false;
            }

            for (String eachAddress : eachDotAddress) {
                if (Integer.parseInt(eachAddress) > 255) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException ignore) {
            // skip
        }

        return false;
    }

}
