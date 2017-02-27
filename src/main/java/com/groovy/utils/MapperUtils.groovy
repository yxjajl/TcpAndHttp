package com.groovy.utils

class MapperUtils {
	def static gen(namespace,vopackage,tablename,columns) {
		def head="""<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">"""
		def end="""</mapper>"""


		write(head)
		write("""<mapper namespace="$namespace">""")
		write("""<sql id="BASECOLUMN">$columns</sql>""")

		//-------------select
		def strSelect="""
		<select id="select">
			select <include refid="BASECOLUMN" /> from $tablename
		</select>
		"""

		write(strSelect)

		//-----------update
		def vsetts = columns.split(',').collect{k->"""${k.trim()} =#{${k.trim()}}\n"""}.join(',')

		def strUpdate = """
		<update id="update">
			update $tablename
			set 
				$vsetts
			where 
			
		</update>
		"""

		write(strUpdate)

		//-----------------insert-----------
		def strvalues = columns.split(',').collect{k->"#{${k.trim()}}"}.join(',')
		def strInsert="""
		<insert id="insert">
			insert into $tablename ($columns) 
			values ($strvalues)
		</insert>
		"""
		
		write(strInsert)
		
		write(end)
	}
	
	def static write(def str) {
		println str
	}
	
	static main(args) {
		def columns = """epay_order_id, epay_order_code, appid, external_code, amount, card_holder_name, card_holder_id, time_expire, notify_url, success_url,
		second_underwriting, order_detail_url, renew_state, product_code, product_name"""
		gen("com.cignacmb.epayment.dao.EpayOrderMapper","com.cignacmb.epayment.domain","t_epay_order",columns)
	}
}
