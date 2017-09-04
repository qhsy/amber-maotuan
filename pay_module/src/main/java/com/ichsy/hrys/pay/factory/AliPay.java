package com.ichsy.hrys.pay.factory;


public class AliPay extends PayFactory {

	@Override
	protected PayInterface creatPayWay() {
		return new AliPayImpl();
	}

}
