package com.alooma.javatester;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.SocketAppender;
import org.apache.logging.log4j.core.layout.JsonLayout;
import org.apache.logging.log4j.core.net.ssl.SslConfiguration;
import org.apache.logging.log4j.core.net.ssl.StoreConfigurationException;
import org.apache.logging.log4j.core.net.ssl.TrustStoreConfiguration;
import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * Hello world!
 *
 */
public class App {
	private static final Logger reporter = getLogger("alooma-reporter");

    public static void main( String[] args ) {
		if (args.length != 2) {
			System.out.println("Usage: java -jar javasdk-tester.jar <alooma hostname> <num of messages>");
			return;
		}

		int msgNum = Integer.parseInt(args[1]);

		Layout<String> jsonLayout = JsonLayout.createLayout(false, false, false, true, true, null);
		SocketAppender socketAppender;
		socketAppender = SocketAppender.createAppender(
				args[0], // alooma hostname
				"5001",
				"SSL", // protocol = "SSL"
				null,
				0,
				null,
				null,
				"Alooma", // Logger name
				"true", // immediateFlush = true
				null,
				jsonLayout,
				null, null, null);
		socketAppender.start();
		((org.apache.logging.log4j.core.Logger)reporter).addAppender(socketAppender);
		for (int i = 0; i < msgNum; i++) {
			reporter.info("{\"type\":\"java-sdk-tester\",\"message\":\"your message\",\"num\":{}}", i);
		}
		System.out.println( "Hello World!" );
    }
}
