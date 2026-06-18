package org.example.springbootdemo1.service;

import org.example.springbootdemo1.mapper.MonitorSettingMapper;
import org.example.springbootdemo1.pojo.MonitorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorSettingImpl implements MonitorSettingService{
    MonitorSettingMapper monitorSettingMapper;

    @Autowired
    public void setMonitorSettingMapper(MonitorSettingMapper monitorSettingMapper) {
        this.monitorSettingMapper = monitorSettingMapper;
    }

    @Override
    public boolean addSetting(MonitorSettings monitorSettings) {
        return monitorSettingMapper.addSetting(monitorSettings);
    }

    @Override
    public boolean deleteSetting() {
        return monitorSettingMapper.deleteSetting();
    }

    @Override
    public MonitorSettings getSetting() {
        return monitorSettingMapper.getSetting();
    }
}
