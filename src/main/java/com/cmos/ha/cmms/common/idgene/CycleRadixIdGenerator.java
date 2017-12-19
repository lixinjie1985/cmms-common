package com.cmos.ha.cmms.common.idgene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixinjie
 * @since 2017-12-18
 */
public class CycleRadixIdGenerator implements IdGenerator {
	
	private final static Logger log = LoggerFactory.getLogger(CycleRadixIdGenerator.class);
	
	//63位/20年=40位/127节点=7位/255线程=8位/255基数=8位
	//2017-01-01 00:00:00.000
	private long baseTime = 0b10101100111110101010000010001010000000000L;
	private long cycleNum = -1;
	private long nodeNum = -1;
	
	private long prevTimeNum = -1;
	private long prevThreadNum = -1;
	private long prevCycleNum = -1;
	
	public long getCycleNum() {
		if (cycleNum >= 255) {
			cycleNum = -1;
		}
		return ++cycleNum;
	}
	
	public long getNodeNum() {
		if (nodeNum < 0) {
			String nodeNumStr = System.getProperty("nodeNum");
			if (nodeNumStr == null) {
				nodeNum = 0;
			} else {
				nodeNum = Long.parseLong(nodeNumStr);
			}
			log.info("NodeNum：{}", nodeNum);
		}
		return nodeNum;
	}
	
	public long getThreadNum() {
		if (log.isDebugEnabled()) {
			log.debug("Thread ID：{}", Thread.currentThread().getId());
		}
		return Thread.currentThread().getId() & 0b11111111;
	}
	
	public long getTimeNum() {
		return System.currentTimeMillis() - baseTime;
	}
	
	@Override
	public synchronized long nextId() {
		long timeNum = getTimeNum();
		long threadNum = getThreadNum();
		long cycleNum = getCycleNum();
		while (isConflict(timeNum, threadNum, cycleNum)) {
			log.info("Conflict occured，TimeNum：{}，NodeNum：{}", timeNum, getNodeNum());
			timeNum = getTimeNum();
			cycleNum = getCycleNum();
		}
		updateState(timeNum, threadNum, cycleNum);
		long id = timeNum;
		id = (id << 7) | getNodeNum();
		id = (id << 8) | threadNum;
		id = (id << 8) | cycleNum;
		return id;
	}
	
	private boolean isConflict(long timeNum, long threadNum, long cycleNum) {
		return (timeNum == prevTimeNum) && (threadNum == prevThreadNum)
				&& (cycleNum == prevCycleNum);
	}
	
	private void updateState(long timeNum, long threadNum, long cycleNum) {
		prevTimeNum = timeNum;
		prevThreadNum = threadNum;
		prevCycleNum = cycleNum;
	}
}
