package hii;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test case Execution  " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case pass " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test case Fail " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test case Skip " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test case Finish");
	}

}
