package com.groovy

class Qian implements QianRuService{

	public void change(QianRuVO vo) {
		vo.lastDate = new Date();
		vo.sum = vo.a * vo.c;
	}

	public QianRuVO init(){
		def vo = new QianRuVO();
		vo.a = 30;
		vo.c = 40;
		change(vo);
		return vo;
	}
}
