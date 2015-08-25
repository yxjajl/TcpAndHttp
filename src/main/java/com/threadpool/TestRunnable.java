package com.threadpool;

public class TestRunnable implements Runnable {
	private TestRunVO testRunVO;

	public TestRunnable(TestRunVO testRunVO) {
		this.testRunVO = testRunVO;
	}

	@Override
	public void run() {
		testRunVO.setName(testRunVO.getName()+"1");
	}

	public TestRunVO getTestRunVO() {
		return testRunVO;
	}

	public void setTestRunVO(TestRunVO testRunVO) {
		this.testRunVO = testRunVO;
	}

}
