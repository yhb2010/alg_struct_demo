package com.demo.data.struct.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 逆波兰式的举例：
 * 下面以(a+b)*c为例子进行说明：
(a+b)*c的逆波兰式为ab+c*，假设计算机把ab+c*按从左到右的顺序压入栈中，并且按照遇到运算符就把栈顶两个元素出栈，执行运算，得到的结果再入栈的原则来进行处理，那么ab+c*的执行结果如下：
1）a入栈（0位置）
2）b入栈（1位置）
3）遇到运算符“+”，将a和b出栈，执行a+b的操作，得到结果d=a+b，再将d入栈（0位置）
4）c入栈（1位置）
5）遇到运算符“*”，将d和c出栈，执行d*c的操作，得到结果e，再将e入栈（0位置）
经过以上运算，计算机就可以得到(a+b)*c的运算结果e了。

中缀表达式看成一个字符串，从左到右开始扫描中缀表达式；

1.遇到操作数：直接输出（添加到后缀表达式中）
2.栈为空时，遇到运算符，直接入栈
3.遇到左括号：将其入栈
4.遇到右括号：执行出栈操作，并将出栈的元素输出，直到弹出栈的是左括号，括号不输出。
5.遇到其他运算符：加减乘除：弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈
6.最终将栈中的元素依次出栈，输出。
 *
 * @author DELL
 *
 */
public class Calculator {

	// 运算符优先级
	private static Map<String,Integer> opLs;
	// 所有运算符
	private static List<Character> opLlist = null;

	private String src;

	@SuppressWarnings("serial")
	public Calculator(String src) {
		this.src = src;
		if (opLs == null) {
			opLs = new HashMap<String, Integer>(6);
            opLs.put("+", 0);
            opLs.put("-", 0);
            opLs.put("*", 1);
            opLs.put("/", 1);
            opLs.put("%", 1);
            opLs.put(")", 2);
		}
		if(opLlist == null){
			opLlist = new ArrayList<Character>(){{
				add('+');
				add('-');
				add('*');
				add('/');
				add('%');
				add('(');
				add(')');
			}};
		}
	}

	/**将中缀表达式转化为后缀表达式（逆波兰表达式）
	 * @return
	 */
	public Stack<String> toRpn() {
		String[] tmp = split(handle(src));
		// 后缀栈
		Stack<String> rpn = new Stack<String>();
		// 临时栈
		Stack<String> tmpSta = new Stack<String>();

		for (String str : tmp) {
			if (isNum(str)) {
				// 是操作数,直接压入结果栈
				rpn.push(str);
			} else {
				// 是操作符号
				if (tmpSta.isEmpty()) {// 还没有符号
					tmpSta.push(str);
				} else {
					if (isOut(tmpSta.peek(), str)) {
						if (!str.equals(")")) {
							do {
								//把临时栈里的操作符在'('之前的全部出栈
								rpn.push(tmpSta.pop());
							} while (!tmpSta.isEmpty() && !tmpSta.peek().equals("("));
							//往临时栈压入读到的最新操作符
							tmpSta.push(str);
						} else {
							// 是)依次弹出临时栈的数据，直到(为止
							while (!tmpSta.isEmpty() && !tmpSta.peek().equals("(")) {
								rpn.push(tmpSta.pop());
							}
							if ((!tmpSta.empty()) && (tmpSta.peek().equals("("))) {// 弹出(
								tmpSta.pop();
							}
						}
					} else {
						tmpSta.push(str);
					}
				}
			}

		}
		while (!tmpSta.empty()) {// 把栈中剩余的操作符依次弹出
			rpn.push(tmpSta.pop());
		}
		StringBuilder st = new StringBuilder();
		for (String str : rpn) {
			st.append(str);
		}
		System.out.println("后缀表达式" + st);
		return rpn;
	}

	/**处理负数
	 * @param src
	 * @return
	 */
	private String handle(String src){
		if(src.startsWith("-")){
			src = "0" + src;
		}
		return src.replaceAll("\\(-", "\\(0-");
	}

	/**分割(56+4)3*6+2=>(,56,+,4,
	 * @param src
	 * @return
	 */
	private String[] split(String src) {
		StringBuilder sb = new StringBuilder(src.length());
		for (char ch : src.toCharArray()) {
			if(opLlist.contains(ch)){
				sb.append(",");
				sb.append(ch);
				sb.append(",");
			}else{
				sb.append(ch);
			}
		}
		String string = sb.toString().replaceAll(",{2,}", ",");
		String[] arr = string.split(",");
		return Stream.of(arr).filter(s -> s != null && !s.equals("")).toArray(String[]::new);
	}

	/**是否是数字
	 * @param str
	 * @return
	 */
	private boolean isNum(String str) {
		return Pattern.compile("^(-?\\d+)(\\.\\d+)?").matcher(str).find();
	}

	/**判断临时栈里的操作符是否需要出栈
	 * @param pop 临时栈的栈顶元素
	 * @param str 当前读取到的操作符
	 * @return
	 */
	private boolean isOut(String pop, String str) {
		if (str.equals(")"))
			return true;
		if (opLs.get(pop) == null || opLs.get(str) == null)
			return false;
		return opLs.get(pop) >= opLs.get(str);

	}

	/**根据逆波兰表达式计算结果
	 * @return
	 */
	public double getRes() {
		Stack<String> rpn = toRpn();
		Stack<Double> res = new Stack<Double>();
		for (int i = 0; i < rpn.size(); i++) {
			String str = rpn.get(i);
			if(isNum(str)){
				//是数字，直接入栈
				res.push(Double.valueOf(str));
			}else{
				//是表达式，把栈顶两个元素出栈
				Double x = res.pop();
				Double y = res.pop();
				switch (str) {
				case "+":
					res.push(y + x);
					break;
				case "-":
					res.push(y - x);
					break;
				case "*":
					res.push(y * x);
					break;
				case "%":
				case "/":
					if (x != 0) {
						double rsd = str.equals("%") ? y % x : y / x;
						res.push(rsd);
					} else {
						System.out.println("分母为零");
						res.clear();
						return -1;
					}
					break;
				}
			}
		}
		Double result = res.pop();
		res.clear();
		return result;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String src = "-1+1*2-(-2)*((2+4)*(6-2))";
		Calculator analyer = new Calculator(src);
		System.out.println(src + "=" + analyer.getRes());
		String src1 = "-1.33+5*(36+5)-8%3";
		Calculator analyer1 = new Calculator(src1);
		System.out.println(src1 + "=" + analyer1.getRes());
		String src2 = "3+(4+5/5)*6";
		Calculator analyer2 = new Calculator(src2);
		System.out.println(src2 + "=" + analyer2.getRes());
		String src3 = "2*3*(3+5*2+1)";
		Calculator analyer3 = new Calculator(src3);
		System.out.println(src3 + "=" + analyer3.getRes());
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}
