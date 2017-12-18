package com.cmos.ha.cmms.common.idgene;

import java.util.Calendar;

/**
 * @author lixinjie
 * @since 2017-12-18
 */
public class Main {

	public static void main(String[] args) {
		//63位/20年=40位/127节点=7位/255线程=8位/255基数=8位
		Calendar c = new Calendar.Builder().setDate(2017, 1, 1).setTimeOfDay(0, 0, 0, 0).build();
		System.out.println(c);
		long baseTime = c.getTimeInMillis();
		long currTime = System.currentTimeMillis();
		System.out.println(Long.toBinaryString(baseTime));
		System.out.println(Long.toBinaryString(currTime));
		System.out.println(Long.toBinaryString(currTime - baseTime));
		System.out.println(Long.toBinaryString(currTime - baseTime).length());
		System.out.println(Integer.toBinaryString(255));
		System.out.println(Thread.currentThread().getId());
		System.out.println(0xffffffffffL);
	}

}
