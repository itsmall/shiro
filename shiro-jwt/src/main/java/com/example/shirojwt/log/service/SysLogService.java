package com.example.shirojwt.log.service;

import com.example.shirojwt.log.bean.SysLog;
import com.example.shirojwt.log.dao.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    public void saveSysLog(SysLog syslog) {
        sysLogDao.save(syslog);
    }

    public List listLog() {
        return this.sysLogDao.findAll();
    }

}
