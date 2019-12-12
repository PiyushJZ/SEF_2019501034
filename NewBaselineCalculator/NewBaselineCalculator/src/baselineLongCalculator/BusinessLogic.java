package baselineLongCalculator;

public class BusinessLogic {
	public CalculatorValue addOperandsLogic(CalculatorValue result, CalculatorValue operand1, CalculatorValue operand2) {
		result = new CalculatorValue(operand1);
		result.add(operand2);
		return result;
	}
}
