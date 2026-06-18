package org.example.springbootdemo1.pojo;

public class SystemInfo {
    private double cpuUtilization;
    private double totalMemory;
    private double usedMemory;
    private double freeMemory;
    private double memoryUtilization;
    private double totalDiskSpace;
    private double usedDiskSpace;
    private double diskUtilization;
    private int totalProcesses;
    private double networkDataReceived;
    private double networkDataSent;
    private String currentTime;

    // Getters and Setters for each field
    public double getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public double getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(double totalMemory) {
        this.totalMemory = totalMemory;
    }

    public double getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(double usedMemory) {
        this.usedMemory = usedMemory;
    }

    public double getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(double freeMemory) {
        this.freeMemory = freeMemory;
    }

    public double getMemoryUtilization() {
        return memoryUtilization;
    }

    public void setMemoryUtilization(double memoryUtilization) {
        this.memoryUtilization = memoryUtilization;
    }

    public double getTotalDiskSpace() {
        return totalDiskSpace;
    }

    public void setTotalDiskSpace(double totalDiskSpace) {
        this.totalDiskSpace = totalDiskSpace;
    }

    public double getUsedDiskSpace() {
        return usedDiskSpace;
    }

    public void setUsedDiskSpace(double usedDiskSpace) {
        this.usedDiskSpace = usedDiskSpace;
    }

    public double getDiskUtilization() {
        return diskUtilization;
    }

    public void setDiskUtilization(double diskUtilization) {
        this.diskUtilization = diskUtilization;
    }

    public int getTotalProcesses() {
        return totalProcesses;
    }

    public void setTotalProcesses(int totalProcesses) {
        this.totalProcesses = totalProcesses;
    }

    public double getNetworkDataReceived() {
        return networkDataReceived;
    }

    public void setNetworkDataReceived(double networkDataReceived) {
        this.networkDataReceived = networkDataReceived;
    }

    public double getNetworkDataSent() {
        return networkDataSent;
    }

    public void setNetworkDataSent(double networkDataSent) {
        this.networkDataSent = networkDataSent;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    // 添加其他 getter 和 setter 方法...

    @Override
    public String toString() {
        return "SystemInfo{" +
                "cpuUtilization=" + cpuUtilization +
                ", totalMemory=" + totalMemory +
                ", usedMemory=" + usedMemory +
                ", freeMemory=" + freeMemory +
                ", memoryUtilization=" + memoryUtilization +
                ", totalDiskSpace=" + totalDiskSpace +
                ", usedDiskSpace=" + usedDiskSpace +
                ", diskUtilization=" + diskUtilization +
                ", totalProcesses=" + totalProcesses +
                ", networkDataReceived=" + networkDataReceived +
                ", networkDataSent=" + networkDataSent +
                ", currentTime='" + currentTime + '\'' +
                '}';
    }
}
