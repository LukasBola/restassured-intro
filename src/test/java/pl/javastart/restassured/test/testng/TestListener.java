package pl.javastart.restassured.test.testng;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    String startSpace = "\n\n-----------------";
    String finishSpace = "-----------------\n\n";

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(startSpace);
        System.out.println("Starting test: " + result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Finished successfully test: " + result.getMethod().getMethodName());
        System.out.println(finishSpace);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test: " + result.getMethod().getMethodName() + " has failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skipping test " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finishing: " + context.getName());
    }

}