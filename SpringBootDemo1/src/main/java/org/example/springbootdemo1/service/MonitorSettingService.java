package org.example.springbootdemo1.service;

import org.example.springbootdemo1.pojo.MonitorSettings;

public interface MonitorSettingService {
    public boolean addSetting(MonitorSettings monitorSettings);
    public boolean deleteSetting();
    public MonitorSettings getSetting();
}
