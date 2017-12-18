package com.cmos.ha.cmms.common.tempmsg.dubbo;

import com.cmos.ha.cmms.common.tempmsg.SlideResult;
import com.cmos.ha.cmms.common.tempmsg.TempMsg;

/**
 * @author lixinjie
 * @since 2017-12-18
 */
public interface ITempMsgSlider {

	SlideResult slideTempMsg(TempMsg tempMsg);
}
