package com.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

/**
 * blog http://www.micmiu.com
 *
 * @author Michael
 *
 */
public class HelloServerDemo {
	public static final int SERVER_PORT = 8090;

	public void startServer() {
		try {
			System.out.println("HelloWorld TSimpleServer start ....");

			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());
			// HelloWorldService.Processor<HelloWorldService.Iface>
			// tprocessor =
			// new HelloWorldService.Processor<HelloWorldService.Iface>(
			// new HelloWorldImpl());

			// 简单的单线程服务模型，一般用于测试
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			// TServer.Args tArgs = new TServer.Args(serverTransport);
			// TThreadPoolServer.Args tArgs = new
			// TThreadPoolServer.Args(serverTransport); // 使用标准的阻塞式IO
			// 线程池

			//使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式
			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(SERVER_PORT);
			TNonblockingServer.Args tArgs = new TNonblockingServer.Args(tnbSocketTransport);

			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			// tArgs.protocolFactory(new TCompactProtocol.Factory());
			// tArgs.protocolFactory(new TJSONProtocol.Factory());
			TServer server = new TSimpleServer(tArgs);
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServerDemo server = new HelloServerDemo();
		server.startServer();
	}

}