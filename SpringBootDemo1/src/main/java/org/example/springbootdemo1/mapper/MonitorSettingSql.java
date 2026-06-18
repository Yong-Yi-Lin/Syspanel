package org.example.springbootdemo1.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.example.springbootdemo1.pojo.MonitorSettings;
import org.example.springbootdemo1.pojo.SystemLog;

public class MonitorSettingSql {
    public String addSetting(final MonitorSettings monitorSettings) {
        return new SQL() {
            {
                INSERT_INTO("monitorSetting");
                VALUES("status", "#{status}");
                if (monitorSettings.getNumber() >= 0) {
                    VALUES("number", "#{number}");
                }
                if (monitorSettings.getCollectionInterval() >= 0) {
                    VALUES("collectionInterval", "#{collectionInterval}");
                }
                if (monitorSettings.getCpuWarningValue() >= 0) {
                    VALUES("cpuWarningValue", "#{cpuWarningValue}");
                }
                if (monitorSettings.getCpuDangerValue() >= 0) {
                    VALUES("cpuDangerValue", "#{cpuDangerValue}");
                }
                if (monitorSettings.getMemoryWarningValue() >= 0) {
                    VALUES("memoryWarningValue", "#{memoryWarningValue}");
                }
                if (monitorSettings.getMemoryDangerValue() >= 0) {
                    VALUES("memoryDangerValue", "#{memoryDangerValue}");
                }
            }
        }.toString();
    }

    public String deleteSetting() {
        return new SQL() {
            {
                DELETE_FROM("monitorSetting");
            }
        }.toString();
    }

    public String getSetting() {
        return new SQL() {
            {
                SELECT("*");
                FROM("monitorSetting");
            }
        }.toString();
    }
}
