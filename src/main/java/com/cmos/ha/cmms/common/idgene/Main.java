package com.cmos.ha.cmms.common.idgene;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lixinjie
 * @since 2017-12-18
 */
public class Main {

	public static void main(String[] args) {
		//63位/20年=40位/127节点=7位/255线程=8位/255基数=8位
		//Calendar c = new Calendar.Builder().setDate(2017, 1, 1).setTimeOfDay(0, 0, 0, 0).build();
		final IdGenerator idg = new CycleRadixIdGenerator();
		
		ExecutorService es = Executors.newFixedThreadPool(255);
		for (int i = 0; i < 255; i++) {
			es.execute(new Runnable() {
	
				@Override
				public void run() {
					while (true) {
						System.out.println(idg.nextId());
					}
				}});
		}
	}

}
