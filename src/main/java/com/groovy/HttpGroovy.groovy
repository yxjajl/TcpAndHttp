

package com.groovy

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST
import groovyx.net.http.HTTPBuilder

/**
 * 	log4j打印httpBuilder日志的配置
 * <category name="org.apache.http.headers">
 <priority value="DEBUG" />
 </category>
 <category name="org.apache.http.wire">
 <priority value="DEBUG" />
 </category>
 * @author r6yuxx
 *
 */
//@Grapes([
//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
//])
class HttpGroovy {
	static main(args) {
		tt4()
	}

	//Get Text
	static def tt1() {
		def http = new HTTPBuilder( 'http://www.cignacmb.com' )

		http.request(GET,TEXT) { req ->
			//uri.path = '/mail/help/tasks/' // overrides any path in the default URL
			headers.'User-Agent' = 'Mozilla/5.0'

			response.success = { resp, reader ->
				assert resp.status == 200
				println "My response handler got response: ${resp.statusLine}"
				println "Response length: ${resp.headers.'Content-Length'}"
				//System.out << reader // print response reader
			}

			// called only for a 404 (not found) status code:
			response.'404' = { resp -> println 'Not found' }
		}

		http.handler.'401' = { resp -> println "Access denied" }

		// Used for all other failure codes not handled by a code-specific handler:
		http.handler.failure = { resp -> println "Unexpected failure: ${resp.statusLine}" }
	}

	//post
	static def tt2() {
		def http = new HTTPBuilder( 'http://restmirror.appspot.com/' )
		def postBody = [name: 'bob', title: 'construction worker'] // will be url-encoded
		http.post( path: '/', body: postBody, requestContentType: URLENC ) { resp ->

			println "POST Success: ${resp.statusLine}"
			assert resp.statusLine.statusCode == 201
		}
	}

	//post
	static def tt3() {
		def http = new HTTPBuilder('http://restmirror.appspot.com/')
		http.request( POST ) {
			uri.path = '/'
			requestContentType = URLENC
			body =  [name: 'bob', title: 'construction worker']

			response.success = { resp ->
				println "POST response status: ${resp.statusLine}"
				assert resp.statusLine.statusCode == 201
			}
			response.'404' = { resp -> println 'Not found' }
		}

		http.handler.'401' = { resp -> println "Access denied" }
	}

	//post json
	static def tt4() {
		def http = new HTTPBuilder( 'http://ajax.googleapis.com' )
		http.request( GET, JSON ) {
			uri.path = '/ajax/services/search/web'
			uri.query = [ v:'1.0', q: 'Calvin and Hobbes' ]

			response.success = { resp, json ->
				assert json.size() == 3
				println "Query response: "
				json.responseData.results.each { println "  ${it.titleNoFormatting} : ${it.visibleUrl}" }
			}
		}
	}


}
