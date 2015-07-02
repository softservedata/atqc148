package com.softserve.edu.atqc.span;

import org.testng.Assert;

import com.softserve.edu.atqc.controls.IButton;
import com.softserve.edu.atqc.controls.ILabel;
import com.softserve.edu.atqc.controls.ILink;
import com.softserve.edu.atqc.controls.ITextField;
import com.softserve.edu.atqc.tools.CaptureScreenImage;
import com.softserve.edu.atqc.tools.LoggerWrapper;

public class AssertWrapper {
	private final String NEW_LINE = "\n";
	private final String APPEND_TEXT = "%sDescription: %s";
	private static final String SCREEN_IMAGE_PATH = "Screen image is located by the next path %s";
	private boolean summaryResult;
	private StringBuilder summaryDescription;
	private static volatile AssertWrapper instance = null;

	private AssertWrapper() {
		this.summaryResult = true;
		this.summaryDescription = new StringBuilder();
	}

	public static AssertWrapper get() {
		if (instance == null) {
			synchronized (AssertWrapper.class) {
				if (instance == null) {
					instance = new AssertWrapper();
				}
			}
		}
		return instance;
	}

	// getters
	
	public boolean getPassed() {
		return summaryResult;
	}

	public String getSummaryDescription() {
		return summaryDescription.toString();
	}
	
	// business

	public void verify(boolean pass, String errorText) {
		summaryResult = summaryResult && pass;
		if (!pass) {
			summaryDescription.append(String.format(APPEND_TEXT, NEW_LINE,
					errorText));
			LoggerWrapper.get().errorLog(errorText);
			String fileNamePath = CaptureScreenImage.get()
					.captureAndSaveScreen();
			LoggerWrapper.get().infoLog(
					String.format(SCREEN_IMAGE_PATH, fileNamePath));
			LoggerWrapper.get().infoLogInsertScreenShot(fileNamePath);
		}
	}

	public void addWarning(String warningText) {
		summaryDescription.append(String.format(APPEND_TEXT, NEW_LINE,
				warningText));
	}

	public void check() {
		boolean result = getPassed();
		String description = getSummaryDescription();
		this.summaryResult = true;
		this.summaryDescription = new StringBuilder();
		Assert.assertTrue(result, description);
	}

	public ButtonCriteria forElement(IButton button) {
		return ButtonCriteria.get(button, this);
	}

	public LabelCriteria forElement(ILabel label) {
		return LabelCriteria.get(label, this);
	}

	public LinkCriteria forElement(ILink link) {
		return LinkCriteria.get(link, this);
	}

	public TextCriteria forElement(String text) {
		return TextCriteria.get(text, this);
	}

	public TextFieldCriteria forElement(ITextField textField) {
		return TextFieldCriteria.get(textField, this);
	}

}
