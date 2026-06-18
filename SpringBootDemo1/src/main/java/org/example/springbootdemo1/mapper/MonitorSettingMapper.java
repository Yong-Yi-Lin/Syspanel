package org.example.springbootdemo1.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.example.springbootdemo1.pojo.MonitorSettings;

public interface MonitorSettingMapper {
    @InsertProvider(type = MonitorSettingSql.class,method = "addSetting")
    boolean addSetting(MonitorSettings monitorSettings);

    @SelectProvider(type = MonitorSettingSql.class,method = "getSetting")
    MonitorSettings getSetting();

    @DeleteProvider(type = MonitorSettingSql.class,method = "deleteSetting")
    boolean deleteSetting();
}
