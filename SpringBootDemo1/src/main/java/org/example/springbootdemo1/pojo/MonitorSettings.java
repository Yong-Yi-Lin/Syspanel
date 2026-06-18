package org.example.springbootdemo1.pojo;

public class MonitorSettings {
    private boolean status; // 监控状态，true为启用，false为禁用
    private int number; // 保存数量
    private int collectionInterval; // 采集间隔（分钟）
    private int cpuWarningValue; // CPU警告值
    private int cpuDangerValue; // CPU危险值
    private int memoryWarningValue; // 内存警告值
    private int memoryDangerValue; // 内存危险值

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int saveDays) {
        this.number = saveDays;
    }

    public int getCollectionInterval() {
        return collectionInterval;
    }

    public void setCollectionInterval(int collectionInterval) {
        this.collectionInterval = collectionInterval;
    }

    public int getCpuWarningValue() {
        return cpuWarningValue;
    }

    public void setCpuWarningValue(int cpuWarningValue) {
        this.cpuWarningValue = cpuWarningValue;
    }

    public int getCpuDangerValue() {
        return cpuDangerValue;
    }

    public void setCpuDangerValue(int cpuDangerValue) {
        this.cpuDangerValue = cpuDangerValue;
    }

    public int getMemoryWarningValue() {
        return memoryWarningValue;
    }

    public void setMemoryWarningValue(int memoryWarningValue) {
        this.memoryWarningValue = memoryWarningValue;
    }

    public int getMemoryDangerValue() {
        return memoryDangerValue;
    }

    public void setMemoryDangerValue(int memoryDangerValue) {
        this.memoryDangerValue = memoryDangerValue;
    }

    @Override
    public String toString() {
        return "MonitorSettings{" +
                "status=" + status +
                ", number=" + number +
                ", collectionInterval=" + collectionInterval +
                ", cpuWarningValue=" + cpuWarningValue +
                ", cpuDangerValue=" + cpuDangerValue +
                ", memoryWarningValue=" + memoryWarningValue +
                ", memoryDangerValue=" + memoryDangerValue +
                '}';
    }
}
