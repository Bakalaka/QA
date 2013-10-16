package basic;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Log extends TestListenerAdapter {
	
	@Override
	public void onTestStart(ITestResult ts){
	System.out.println("--------------------------------------");
	System.out.println(ts.getMethod().getDescription());
	System.out.println("STARTED: Class = "+ts.getTestClass().getRealClass().getSimpleName()+" Method = "+ts.getName());
	}

}
