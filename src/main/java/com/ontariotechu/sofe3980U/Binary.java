package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'

	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.
	*        Leading zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	}

	/**
	* Return the binary value of the variable
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}

	/**
	* Adding two binary variables.
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of num1+num2.
	*/
	public static Binary add(Binary num1, Binary num2)
	{
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;

		int carry = 0;
		String num3 = "";  // the binary value of the sum

		while (ind1 >= 0 || ind2 >= 0 || carry != 0)
		{
			int sum = carry;

			if (ind1 >= 0) {
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}
			if (ind2 >= 0) {
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}

			carry = sum / 2;
			sum = sum % 2;

			num3 = ((sum == 0) ? "0" : "1") + num3;
		}

		return new Binary(num3);
	}

	/**
	 * Bitwise OR over two binary variables.
	 * @param num1 First binary operand
	 * @param num2 Second binary operand
	 * @return A binary variable with value (num1 OR num2)
	 */
	public static Binary or(Binary num1, Binary num2)
	{
		String a = num1.number;
		String b = num2.number;

		int maxLen = Math.max(a.length(), b.length());
		a = padLeftWithZeros(a, maxLen);
		b = padLeftWithZeros(b, maxLen);

		String result = "";
		for (int i = 0; i < maxLen; i++) {
			char ra = a.charAt(i);
			char rb = b.charAt(i);
			result += (ra == '1' || rb == '1') ? '1' : '0';
		}

		return new Binary(result);
	}

	/**
	 * Bitwise AND over two binary variables.
	 * @param num1 First binary operand
	 * @param num2 Second binary operand
	 * @return A binary variable with value (num1 AND num2)
	 */
	public static Binary and(Binary num1, Binary num2)
	{
		String a = num1.number;
		String b = num2.number;

		int maxLen = Math.max(a.length(), b.length());
		a = padLeftWithZeros(a, maxLen);
		b = padLeftWithZeros(b, maxLen);

		String result = "";
		for (int i = 0; i < maxLen; i++) {
			char ra = a.charAt(i);
			char rb = b.charAt(i);
			result += (ra == '1' && rb == '1') ? '1' : '0';
		}

		return new Binary(result);
	}

	/**
	 * Multiply two binary variables (unsigned).
	 * @param num1 Multiplicand
	 * @param num2 Multiplier
	 * @return A binary variable with value (num1 * num2)
	 */
	public static Binary multiply(Binary num1, Binary num2)
	{
		// Quick zero check
		if (num1.number.equals("0") || num2.number.equals("0")) {
			return new Binary("0");
		}

		Binary result = new Binary("0");

		// Multiply using shift-and-add (like long multiplication)
		String multiplier = num2.number;
		int shift = 0;

		for (int i = multiplier.length() - 1; i >= 0; i--) { // from LSB to MSB
			if (multiplier.charAt(i) == '1') {
				Binary shifted = shiftLeft(num1, shift);
				result = add(result, shifted);
			}
			shift++;
		}

		return result;
	}


	private static String padLeftWithZeros(String s, int length)
	{
		if (s.length() >= length) return s;
		String padded = "";
		for (int i = 0; i < length - s.length(); i++) {
			padded += "0";
		}
		return padded + s;
	}

	private static Binary shiftLeft(Binary num, int positions)
	{
		if (positions <= 0) return new Binary(num.number);
		if (num.number.equals("0")) return new Binary("0");

		String shifted = num.number;
		for (int i = 0; i < positions; i++) {
			shifted += "0";
		}
		return new Binary(shifted);
	}
}
