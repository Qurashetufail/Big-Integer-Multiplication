package BigInteger;

import LinkedList.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public String multiply(String firstNumber, String secondNumber) {
		// Checking whether any of the two numbers is zero
		if (firstNumber.matches("^[0]+(\\.[0]+)?$") || secondNumber.matches("^[0]+(\\.[0]+)?$")) {
			return "0";
		}
		// signCheck is used to check the sign of the number decimalIndexFirst
		// and decimalIndexSecond stores the index of the decimal in number
		int signCheck = 0, decimalIndexFirst = 0, decimalIndexSecond = 0;

		// to check if the number contains the decimal
		boolean check = false;

		// Used to calculate the total number of digits are decimal
		int trailingDigitsAfterDecimalFirst = 0, trailingDigitsAfterDecimalSecond = 0;

		// preResult is used to store the result after multiplication
		String preResult = "";

		// product stores the result after decimal assignment
		String product = "";

		// regular expression to check if the string contains real numbers only
		String pattern = "^(\\-|\\+)?[0-9]+(\\.[0-9]+)?$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(firstNumber);
		Matcher m1 = r.matcher(secondNumber);

		// checking if any one of the two numbers are not real
		if (!m.matches() || !m1.matches()) {
			return "Please enter numbers only";
		} else {
			// removing the negative sign before the numbers if both of them
			// negative
			if (firstNumber.contains("-") && secondNumber.contains("-")) {
				firstNumber = firstNumber.replace("-", "");
				secondNumber = secondNumber.replace("-", "");
				signCheck = 2;

				// removing the negative sign from one of the numbers
			} else if (firstNumber.contains("-") || secondNumber.contains("-")) {
				firstNumber = firstNumber.replace("-", "");
				secondNumber = secondNumber.replace("-", "");
				signCheck = 1;
			}

			// removing the trailing zeros in non fractional first number
			if (!firstNumber.contains(".")) {
				firstNumber = removeTrailingZeros(firstNumber);

			}

			// removing the trailing zeros in non fractional second number
			if (!secondNumber.contains(".")) {
				secondNumber = removeTrailingZeros(secondNumber);
			}

			/*
			 * checking if the first number is decimal if it is true then
			 * removing the trailing zeros first storing the index of the
			 * decimal point after the removal of trailing zeros then removing
			 * the decimal point, checking the length after decimal removal and
			 * calculating the number of digits after the decimal
			 */
			if (firstNumber.contains(".")) {
				check = true;
				firstNumber = removeTrailingZeros(firstNumber);
				decimalIndexFirst = firstNumber.indexOf(".");
				firstNumber = firstNumber.replace(".", "");
				trailingDigitsAfterDecimalFirst = firstNumber.length() - decimalIndexFirst;
			}

			/*
			 * checking if the second number is decimal if it is true then
			 * removing the trailing zeros first storing the index of the
			 * decimal point after the removal of trailing zeros then removing
			 * the decimal point, checking the length after decimal removal and
			 * calculating the number of digits after the decimal
			 */
			if (secondNumber.contains(".")) {
				check = true;
				secondNumber = removeTrailingZeros(secondNumber);
				decimalIndexSecond = secondNumber.indexOf(".");
				secondNumber = secondNumber.replace(".", "");
				trailingDigitsAfterDecimalSecond = secondNumber.length() - decimalIndexSecond;
			}

			// trailingDigits used to calculate sum of the trailing digits of
			// both numbers
			int trailingDigits = trailingDigitsAfterDecimalFirst + trailingDigitsAfterDecimalSecond;

			// storing the length of two numbers respectively
			int firstNumLen = firstNumber.length();
			int secondNumLen = secondNumber.length();

			// creating an array of the same length as the length of number
			// respectively
			int[] numInt1 = new int[firstNumLen];
			int[] numInt2 = new int[secondNumLen];

			// is used to place the zeros before number if need;
			String zeros = "";

			// is used to store the position of the final decimal point
			int decimalPosition = 0;

			// converting the input second number from string to integer array
			// format
			for (int i = 0; i < firstNumLen; i++) {
				numInt1[i] = Integer.parseInt(String.valueOf(firstNumber.charAt(i)));
			}

			// converting the input second number from string to integer array
			// format
			for (int i = 0; i < secondNumLen; i++) {
				numInt2[i] = Integer.parseInt(String.valueOf(secondNumber.charAt(i)));
			}

			preResult = mainMultiply(secondNumLen, firstNumLen, numInt2, numInt1);
			String actualResult = preResult;

			// calculating the length of the preResult
			int len = preResult.length();

			// calculating the final decimal position
			decimalPosition = len - trailingDigits;
			// check for the fractional numbers only

			// here the decimal point is placed after multiplication and zeros are prefixed when needed
			if (decimalPosition <= 0) {
				decimalPosition = Math.abs(decimalPosition);
				for (int i = 0; i < decimalPosition; i++) {
					zeros = zeros + "0";
				}
				actualResult = "0." + zeros + actualResult;
				if ((signCheck == 2) || (signCheck == 0))
					product = actualResult;
				else {
					product = "-" + actualResult;
				}
			} else {
				StringBuffer buffer = new StringBuffer(preResult);
				if (decimalPosition == 0) {
					decimalPosition = 1;
					if ((signCheck == 2) || (signCheck == 0))
						product = buffer.insert(decimalPosition - 1, "0.").toString();
					else {
						product = "-" + buffer.insert(decimalPosition - 1, "0.").toString();
					}
				} else {
					if ((signCheck == 2) || (signCheck == 0))
						product = buffer.insert(decimalPosition, ".").toString();
					else {
						product = "-" + buffer.insert(decimalPosition, ".").toString();
					}
				}
			}
		}
		
		//replaces the decimal point in the product when it is not followed by integer number
		if (check)
			return product;
		else
			return product.replace(".", "");
		// }
	}

	private String mainMultiply(int firstNumLen, int secondNumLen, int numInt1[], int numInt2[]) {

		LinkedList newList = new LinkedList();
		String temp = "";
		int carry = 0, tempProduct = 0, counter = 0;
		for (int i = firstNumLen - 1; i >= 0; i--, counter++) {
			temp = "";
			for (int j = secondNumLen - 1; j >= 0; j--) {
				tempProduct = carry + (numInt1[i] * numInt2[j]);
				carry = 0;
				if (j == 0) {
					temp = tempProduct + temp;
					break;
				}
				if (tempProduct > 9) {
					if (i > 0 || j > 0) {
						carry = tempProduct / 10;
						tempProduct = tempProduct % 10;
						temp = tempProduct + temp;
					} else {
						temp = tempProduct + temp;
					}
				} else {
					temp = tempProduct + temp;
				}
			}
			for (int addZero = 0; addZero < counter; addZero++) {
				temp += "0";
			}
			newList.add(temp);
		}
		return newList.addNodesData();
	}

	private String removeTrailingZeros(String number) {
		int pos = 0;
		int len = number.length();
		while ((pos < len - 1) && (number.charAt(pos) == '0'))
			pos++;
		number = number.substring(pos);
		return number;
	}
}
