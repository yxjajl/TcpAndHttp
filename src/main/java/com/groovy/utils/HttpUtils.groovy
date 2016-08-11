package com.groovy.utils
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST
import groovyx.net.http.HTTPBuilder
class HttpUtils {

	static main(args) {
		//get("http://www.baidu.com")

		def map =[
			"user":"SZK160048","password":"xxx"
		];

		String url = "http://10.140.2.55:7021/login.aspx?action=login";

		println post(url,map)
	}

	static def get(url) {
		def http = new HTTPBuilder( url)
		def result =null

		http.handler.'401' = { resp -> println "Access denied" }
		http.handler.failure = { resp -> println "Unexpected failure: ${resp.statusLine}" }
		http.request(GET,TEXT) { req ->
			//uri.path = '/mail/help/tasks/' // overrides any path in the default URL
			headers.'User-Agent' = 'Mozilla/5.0'

			response.success = { resp, reader ->
				assert resp.status == 200
				//println "My response handler got response: ${resp.statusLine}"
				//println "Response length: ${resp.headers.'Content-Length'}"
				//System.out << reader // print response reader
				result = reader.text
			}

			// called only for a 404 (not found) status code:
			response.'404' = { resp -> println 'Not found' }
		}


		return result
	}

	static def post(url,postdata) {
		def result =null
		def http = new HTTPBuilder(url)
		http.request( POST ) {
			uri.path = '/'
			requestContentType = URLENC
			body= postdata

			response.success = { resp ->
				println "POST response status: ${resp.statusLine}"
				assert resp.statusLine.statusCode == 201
				result = reader.text
			}
		}

		return result
	}
}
