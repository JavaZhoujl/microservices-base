package com.msc.microservices.common.util;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 网络工具类
 *
 * @author zjl
 */
public final class InetUtil {
    /**
     * 测试端口是否空闲
     *
     * @param port 端口
     * @return 是否空闲
     */
    public static boolean isPortFree(int port) {
        boolean free = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
            free = true;
        } catch (IOException e) {

        } finally {
            serverSocket = null;
        }
        return free;
    }
}
