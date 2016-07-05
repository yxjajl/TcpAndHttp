package com.groovy

class Qian {

	static def void change(QianRuVO vo) {
		vo.lastDate = new Date();
		vo.sum = vo.a * vo.c;
	}

	static def QianRuVO init(){
		def vo = new QianRuVO();
		vo.a = 30;
		vo.c = 40;
		change(vo);
		return vo;
	}
}
