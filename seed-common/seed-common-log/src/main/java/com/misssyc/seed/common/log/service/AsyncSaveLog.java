package com.misssyc.seed.common.log.service;

import com.misssyc.seed.api.admin.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * 异步保存日志
 * @author 李生平
 * @since 2024/2/21
 **/
@Slf4j
@Service
@EnableAsync
public class AsyncSaveLog {

    @Async
    public void saveLog(SysLog sysLog) {
        try {
            // TODO 保存日志的真实逻辑
        } catch (Exception err) {
            log.error("错误：保存日志出错，错误信息：{}", err.getMessage());
        }
    }
}
