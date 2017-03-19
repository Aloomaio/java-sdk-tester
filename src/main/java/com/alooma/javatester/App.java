package com.alooma.javatester;

import com.google.devtools.common.options.OptionsParser;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.SocketAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.util.Collections;

import static org.apache.logging.log4j.LogManager.getLogger;

public class App {
	private static final Logger reporter = getLogger("alooma-reporter");

	public static void main( String[] args ) {
        OptionsParser parser = OptionsParser.newOptionsParser(TesterParser.class);
        parser.parseAndExitUponError(args);
        TesterParser options = parser.getOptions(TesterParser.class);
        if (options.numOfEvents == 0 || options.host.isEmpty() || options.token.isEmpty()){
            printUsage(parser);
            return;
        }

		Layout<String> patternLayout = PatternLayout.createDefaultLayout();
		ConsoleAppender.createDefaultAppenderForLayout(patternLayout);
		SocketAppender socketAppender;
		socketAppender = SocketAppender.createAppender(
				options.host, // alooma hostname
				options.port,
				options.protocol, // protocol = "SSL"
				null, 0, null, null,
				"Alooma", // Logger name
				"true", // immediateFlush = true
				null, patternLayout, null, null, null);
		socketAppender.start();
		((org.apache.logging.log4j.core.Logger)reporter).addAppender(socketAppender);
		for (int i = 0; i < options.numOfEvents; i++) {
			reporter.info("{\"token\":\"" + options.token + "\",\"message\":{\"stringField\":\"Java reporter - tester\",\"numField\":" + i +"}}");
		}
	}

    private static void printUsage(OptionsParser parser) {
        System.out.println("Usage: java -jar javasdk-tester-new.jar OPTIONS");
        System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(),
                OptionsParser.HelpVerbosity.LONG));
    }
}