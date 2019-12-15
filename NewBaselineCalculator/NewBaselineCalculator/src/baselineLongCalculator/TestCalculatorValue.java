package baselineLongCalculator;

/**
 * Testline for the claculatorValue class
 * 
 * @author Piyush Jain
 *
 */
public class TestCalculatorValue {
	public static void main(String[] args) {
		int noPassed = 0;
		int noFailed = 0;

		// TestCase 1.
		CalculatorValue operand1 = new CalculatorValue("+365");
		CalculatorValue operand2 = new CalculatorValue("-365");
		CalculatorValue result = new CalculatorValue(operand1);
		result.add(operand2);
		result.sub(operand1);
		result.mpy(new CalculatorValue(1));
		result.div(operand1);
		if (result.measuredValue == -1) {
			++noPassed;
			System.out.println("Result:\t" + result.toString());
			System.out.println("======================================================");
		} else {
			System.out.println("TestCase 1");
			System.out.println(result.toString());
			System.out.println("======================================================");
			++noFailed;
		}

		// TestCase 2.
		operand1 = new CalculatorValue(1000);
		operand2 = new CalculatorValue("add");
		result = new CalculatorValue(operand1);
		result.sub(operand2);
		if (result.measuredValue == 1000 && "***Error*** Invalid value".equals(operand2.getErrorMessage())) {
			++noPassed;
			System.out.println("Result:\t" + result.toString());
			System.out.println("======================================================");
		} else {
			System.out.println("TestCase 2");
			System.out.println("Result:\t" + result.toString());
			System.out.println("Error Message:\t" + operand2.errorMessage);
			System.out.println("======================================================");
			++noFailed;
		}

		// TestCase 3.
		operand1 = new CalculatorValue("1545");
		operand2 = new CalculatorValue("");
		result = new CalculatorValue(operand1);
		result.mpy(operand2);
		if (result.measuredValue == 0 && "***Error*** Input is empty".equals(operand2.errorMessage)) {
			++noPassed;
			System.out.println("Result:\t" + result.toString());
			System.out.println("======================================================");
		} else {
			System.out.println("TestCase 3");
			System.out.println("Result:\t" + result.toString());
			System.out.println("Error Message:\t" + operand2.errorMessage);
			System.out.println("======================================================");
			++noFailed;
		}

		// TestCase 4.
		operand1 = new CalculatorValue("12323 3434");
		operand2 = new CalculatorValue(243324325);
		operand2.setValue(545454);
		result = new CalculatorValue();
		result.setValue(operand2);
		if (result.measuredValue == 545454 && "***Error*** Excess data".equals(operand1.errorMessage)) {
			++noPassed;
			System.out.println("Result:\t" + result.toString());
			System.out.println("======================================================");
		} else {
			System.out.println("TestCase 4");
			System.out.println("Result:\t" + result.toString());
			System.out.println("Error Message:\t" + operand2.errorMessage);
			System.out.println("======================================================");
			++noFailed;
		}

		// TestCase 5.
		operand1 = new CalculatorValue(100,2,1);
		operand2 = new CalculatorValue(90,2,1);
		result = new CalculatorValue();
		result.setValue(operand2);
		if (result.measuredValue == 90) {
			++noPassed;
			System.out.println("Result:\t" + result.toString());
			System.out.println("======================================================");
		} else {
			System.out.println("TestCase 5");
			System.out.println("Result:\t" + result.toString());
			System.out.println("======================================================");
			++noFailed;
		}

		if (noFailed == 0) System.out.println("All TestCases Passed");

	}
}
