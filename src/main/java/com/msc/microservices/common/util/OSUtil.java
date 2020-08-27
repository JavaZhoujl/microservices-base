package com.msc.microservices.common.util;

/**
 * 操作系统工具类
 *
 * @author zjl
 */
public final class OSUtil {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isLinux() {
        return OS.contains("linux");
    }

    public static boolean isMacOS() {
        return OS.contains("mac") && OS.indexOf("os") > 0 && !OS.contains("x");
    }

    public static boolean isMacOSX() {
        return OS.contains("mac") && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
    }

    public static boolean isWindows() {
        return OS.contains("windows");
    }

    public static boolean isOS2() {
        return OS.contains("os/2");
    }

    public static boolean isSolaris() {
        return OS.contains("solaris");
    }

    public static boolean isSunOS() {
        return OS.contains("sunos");
    }

    public static boolean isMPEiX() {
        return OS.contains("mpe/ix");
    }

    public static boolean isHPUX() {
        return OS.contains("hp-ux");
    }

    public static boolean isAix() {
        return OS.contains("aix");
    }

    public static boolean isOS390() {
        return OS.contains("os/390");
    }

    public static boolean isFreeBSD() {
        return OS.contains("freebsd");
    }

    public static boolean isIrix() {
        return OS.contains("irix");
    }

    public static boolean isDigitalUnix() {
        return OS.contains("digital") && OS.indexOf("unix") > 0;
    }

    public static boolean isNetWare() {
        return OS.contains("netware");
    }

    public static boolean isOSF1() {
        return OS.contains("osf1");
    }

    public static boolean isOpenVMS() {
        return OS.contains("openvms");
    }

    public static Platform getPlatform() {
        if (isAix()) {
            return Platform.AIX;
        } else if (isLinux()) {
            return Platform.Linux;
        } else if (isWindows()) {
            return Platform.Windows;
        } else if (isDigitalUnix()) {
            return Platform.Digital_Unix;
        } else if (isFreeBSD()) {
            return Platform.FreeBSD;
        } else if (isHPUX()) {
            return Platform.HP_UX;
        } else if (isIrix()) {
            return Platform.Irix;
        } else if (isMacOS()) {
            return Platform.Mac_OS;
        } else if (isMacOSX()) {
            return Platform.Mac_OS_X;
        } else if (isMPEiX()) {
            return Platform.MPEiX;
        } else if (isNetWare()) {
            return Platform.NetWare_411;
        } else if (isOpenVMS()) {
            return Platform.OpenVMS;
        } else if (isOS2()) {
            return Platform.OS2;
        } else if (isOS390()) {
            return Platform.OS390;
        } else if (isOSF1()) {
            return Platform.OSF1;
        } else if (isSolaris()) {
            return Platform.Solaris;
        } else if (isSunOS()) {
            return Platform.SunOS;
        } else {
            return Platform.Others;
        }
    }

    enum Platform {
        /**
         * Linux
         */
        Linux("Linux"),
        /**
         * Mac_OS
         */
        Mac_OS("Mac OS"),
        /**
         * Mac_OS_X
         */
        Mac_OS_X("Mac OS X"),
        /**
         * Windows
         */
        Windows("Windows"),
        /**
         * OS2
         */
        OS2("OS/2"),
        /**
         * Solaris
         */
        Solaris("Solaris"),
        /**
         * SunOS
         */
        SunOS("SunOS"),
        /**
         * MPEiX
         */
        MPEiX("MPE/iX"),
        /**
         * HP_UX
         */
        HP_UX("HP-UX"),
        /**
         * AIX
         */
        AIX("AIX"),
        /**
         * OS390
         */
        OS390("OS/390"),
        /**
         * FreeBSD
         */
        FreeBSD("FreeBSD"),
        /**
         * Irix
         */
        Irix("Irix"),
        /**
         * Digital_Unix
         */
        Digital_Unix("Digital Unix"),
        /**
         * NetWare_411
         */
        NetWare_411("NetWare"),
        /**
         * OSF1
         */
        OSF1("OSF1"),
        /**
         * OpenVMS
         */
        OpenVMS("OpenVMS"),
        /**
         * Others
         */
        Others("Others");
        private final String description;

        Platform(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
