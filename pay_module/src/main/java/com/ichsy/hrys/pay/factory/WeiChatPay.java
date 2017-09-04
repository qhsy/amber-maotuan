package com.ichsy.hrys.pay.factory;



/**
 * @author xingchun
 *
 */
public class WeiChatPay extends PayFactory{

	@Override
	protected PayInterface creatPayWay() {
		return new WeiChatImpl();
	}
}
