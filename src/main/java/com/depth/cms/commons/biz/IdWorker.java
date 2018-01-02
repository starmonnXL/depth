package com.depth.cms.commons.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 14:10 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class IdWorker {
    protected static final Logger LOG = LoggerFactory.getLogger(IdWorker.class);

    private long workerId;
    // 数据标识id部分
    private long datacenterId;
    // 0，并发控制
    private long sequence = 0L;
    // 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
    private long twepoch = 1288834974657L;
    // 机器标识位数
    private long workerIdBits = 5L;
    // 数据中心标识位数
    private long datacenterIdBits = 5L;
    // 机器ID最大值
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 数据中心ID最大值
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    // 毫秒内自增位
    private long sequenceBits = 12L;
    // 机器ID偏左移12位
    private long workerIdShift = sequenceBits;
    // 数据中心ID左移17位
    private long datacenterIdShift = sequenceBits + workerIdBits;
    // 时间毫秒左移22位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    /* 上次生产id时间戳 */
    private long lastTimestamp = -1L;

    /**
     * @param workerId 机器编号，不能大于31
     * @param datacenterId 系列号，不能大于31
     */
    public IdWorker(long workerId, long datacenterId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        LOG.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            LOG.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}

