package com.softserve.edu.atqc.tools;

import org.testng.Reporter;

public class LoggerWrapper {
	private static volatile LoggerWrapper instance = null;
	private final String SLASH = "/";
	private final String BR_ERROR = "<br>[ERROR] ";
	private final String BR_INFO = "<br>[INFO] ";
	//private final String BR_DIV_IMG = "<br><image width=\"50%\" src=\"%s\"/>";
	private final String BR_DIV_IMG = "<br><div><image style=\"max-width:100%%\" src=\"%s\"/></div>";

	private LoggerWrapper() {
	}

	public static LoggerWrapper get() {
		if (instance == null) {
			synchronized (LoggerWrapper.class) {
				if (instance == null) {
					instance = new LoggerWrapper();
				}
			}
		}
		return instance;
	}

	public void errorLog(String message) {
		Reporter.log(BR_ERROR + message);
	}

	public void infoLog(String message) {
		Reporter.log(BR_INFO + message);
	}

	public void infoLogInsertScreenShot(String fileNamePath) {
		Reporter.log(String.format(BR_DIV_IMG,
				fileNamePath.substring(fileNamePath.lastIndexOf(SLASH) + 1)));
	}

}
