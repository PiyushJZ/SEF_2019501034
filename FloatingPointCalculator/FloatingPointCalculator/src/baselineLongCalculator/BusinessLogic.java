package baselineLongCalculator;

/**
 * BusinessLogic CLass.
 * The business logic for this JavaFX application to establish the
 * business logic associated with all the operations of the calculator.
 * 
 * @author Piyush Jain
 *
 */
public class BusinessLogic {
	private CalculatorValue operand1;
	private CalculatorValue operand2;
	private CalculatorValue result;

	public CalculatorValue getOperand1() {
		return this.operand1;
	}

	public CalculatorValue getOperand2() {
		return this.operand2;
	}

	public CalculatorValue getResult() {
		return this.result;
	}

	public void convertOperand1Logic(String str) {
		operand1 = new CalculatorValue(str);
	}
	
	public void convertOperand2Logic(String str) {
		operand2 = new CalculatorValue(str);
	}

	public String addOperandsLogic() {
		result = new CalculatorValue(operand1);
		result.add(operand2);
		if ("Infinity".equals(String.valueOf(result.measuredValue))) {
			return "Infinity";
		}
		return result.toString();
	}
	
	public String subOperandsLogic() {
		result = new CalculatorValue(operand1);
		result.sub(operand2);
		if ("Infinity".equals(String.valueOf(result.measuredValue))) {
			return "Infinity";
		}
		return result.toString();
	}

	public String mpyOperandsLogic() {
		result = new CalculatorValue(operand1);
		result.mpy(operand2);
		if ("Infinity".equals(String.valueOf(result.measuredValue))) {
			return "Infinity";
		}
		return result.toString();
	}

	public String divOperandsLogic() {
		if (operand2.measuredValue == 0) {
		return "zeroDivision";
		}
		result = new CalculatorValue(operand1);
		if ("Infinity".equals(String.valueOf(result.measuredValue))) {
			return "Infinity";
		}
		result.div(operand2);
		return result.toString();
	}

	public String sqrtOperandsLogic() {
		if(operand1.measuredValue < 0) {
			return"Negitive values not allowed.";
		}
		result = new CalculatorValue(operand1);
		result.sqrt();
		if ("Infinity".equals(String.valueOf(result.measuredValue))) {
			return "Infinity";
		}
		return result.toString();
	}
}
