package main.java.com.softserve.edu.atqc.helpers;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Xdr on 6/12/15.
 */
public class Listeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        Report.logWithColor("Test '" + iTestResult.getName() + "' started.", "blue");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    Report.logWithColor("Test '" + iTestResult.getName() + "' SUCCEED.", "green");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Report.logWithColor("Test '" + iTestResult.getName() + "' FAILED!", "red");
        Report.takeScreenshot(iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Report.logWithColor("Test '" + iTestResult.getName() + "' SKIPPED.", "orange");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
