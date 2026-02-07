package com.yorkhuul.life.map.tools;

public class RuntimeMemoryUsage {

    public static String memoryUsage() {
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        String message = "Memory usage: %d %s";

        //if (memory > 1073741823) return String.format(message, memory / 1073741824, "GB");
        if (memory > 1048575) return String.format(message, memory / 1048576, "MB");
        if (memory > 1023) return String.format(message, memory / 1024, "KB");
        else return String.format(message, memory, "Bytes");
    }
}
