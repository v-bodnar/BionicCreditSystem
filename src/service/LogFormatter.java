package service;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/*
 * Class to override standard logger format
 */
public class LogFormatter extends Formatter {
	@Override
	public String format(LogRecord event) {
		StringBuilder message = new StringBuilder();
		message.append(event.getMessage() + "\n");
		return message.toString();
	}

	public Logger getLogger() {
		Logger inform = Logger.getLogger("my.logger");

		inform.setUseParentHandlers(false);
		Handler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		inform.addHandler(handler);
		return inform;
	}
}