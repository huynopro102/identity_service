package identity.TuanHuy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemoryMonitorService {
    public void printMemoryStats() {
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory(); // Total memory in JVM
        long freeMemory = runtime.freeMemory();   // Free memory in JVM
        long maxMemory = runtime.maxMemory();     // Max memory JVM will attempt to use
        long usedMemory = totalMemory - freeMemory; // Used memory

        log.info("Memory Usage Statistics:");
        log.info("Used Memory: {} MB", usedMemory / 1024 / 1024);
        log.info("Free Memory: {} MB", freeMemory / 1024 / 1024);
        log.info("Total Memory: {} MB", totalMemory / 1024 / 1024);
        log.info("Max Memory: {} MB", maxMemory / 1024 / 1024);
        log.info("Memory Usage: {}%", (usedMemory * 100) / maxMemory);
    }

    // Method to get memory usage in MB
    public long getUsedMemoryInMB() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
    }

    // Method to get memory usage percentage
    public double getMemoryUsagePercentage() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        return (usedMemory * 100.0) / runtime.maxMemory();
    }
}
