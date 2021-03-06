package com.zendaimoney.trust.channel.pub.vo.req;

import com.zendaimoney.trust.channel.annotations.CmbAnnotation;
import com.zendaimoney.trust.channel.util.Constants;

public class DcckReq extends TrustBizReqVo {

	private static final long serialVersionUID = -6291997675608627391L;

	// 对账日期 20160310
	@CmbAnnotation(index = 1, length = 8, rightFill = true, filler = Constants.BLANK)
	private String accountDate;

	// 交易标识码
	@CmbAnnotation(index = 2, length = 4, rightFill = true, filler = Constants.BLANK)
	private String tradeCode;
	// 备用
	@CmbAnnotation(index = 3, length = 200, rightFill = true, filler = Constants.BLANK)
	private String spare;

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getSpare() {
		return spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}

}
