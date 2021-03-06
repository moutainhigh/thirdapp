package com.zendaimoney.thirdpp.channel.dto.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.zendaimoney.thirdpp.common.enums.BizSys;
import com.zendaimoney.thirdpp.common.enums.BizType;
import com.zendaimoney.thirdpp.common.enums.ThirdType;


@XmlAccessorType(XmlAccessType.FIELD)
public abstract class ReqDto{
	
	/**
	 * 业务类型
	 */
	@XmlTransient
	private BizType bizType;
	
	
	/**
	 * 第三方通道类型
	 */
	@XmlTransient
	private ThirdType thirdType;
	
	
	
	/**
	 * 第三方业务系统
	 */
	@XmlTransient
	private BizSys bizSys;
	
	/**
	 * 通道请求ID
	 */
	@XmlTransient
	private String channelReqId;
	
	
	

	public BizType getBizType() {
		return bizType;
	}


	public void setBizType(BizType bizType) {
		this.bizType = bizType;
	}


	public ThirdType getThirdType() {
		return thirdType;
	}


	public void setThirdType(ThirdType thirdType) {
		this.thirdType = thirdType;
	}
	
	
	
	
	public BizSys getBizSys() {
		return bizSys;
	}


	public void setBizSys(BizSys bizSys) {
		this.bizSys = bizSys;
	}

	

	public String getChannelReqId() {
		return channelReqId;
	}


	public void setChannelReqId(String channelReqId) {
		this.channelReqId = channelReqId;
	}


	/**
	 * 对象转报文
	 * @return
	 */
	public abstract String encode();
	

}
