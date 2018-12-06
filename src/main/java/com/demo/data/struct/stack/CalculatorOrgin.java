package com.demo.data.struct.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 原始例子
 *
 * @author DELL
 *
 */
public class CalculatorOrgin {

	//运算符优先级
    private static HashMap<String,Integer> opLs;

    private String src;

    public CalculatorOrgin(String src) {
        this.src = src;
        if(opLs==null)
        {
            opLs = new HashMap<String,Integer>(6);
            opLs.put("+",0);
            opLs.put("-",0);
            opLs.put("*",1);
            opLs.put("/",1);
            opLs.put("%",1);
            opLs.put(")",2);
        }
    }

    //将中缀表达式转化为后缀表达式
    public String toRpn()
    {
        String[] tmp = split(src);
        // 后缀栈
        Stack<String> rpn = new Stack<String>();
        // 临时栈
        Stack<String> tmpSta = new Stack<String>();

        for (String str : tmp) {
            if (isNum(str)) {
                //是操作数,直接压入结果栈
                rpn.push('('+str+')');
            }else{
                //是操作符号
                if(tmpSta.isEmpty())
                {//还没有符号
                    tmpSta.push(str);
                }else{
                 //判读当前符号和临时栈栈顶符号的优先级
                    if(isHigh(tmpSta.peek(),str))
                    {
                        if(!str.equals(")"))
                        {
                            do{
                                //1在临时栈中找出小于当前优先级的操作符
                                //2压入当前读入操作符
                                rpn.push(tmpSta.peek());
                                tmpSta.pop();
                            }while(!tmpSta.isEmpty()&&(isHigh(tmpSta.peek(),str)));

                            tmpSta.push(str);
                        }else{
                            //是)依次弹出临时栈的数据，直到(为止
                            while(!tmpSta.isEmpty()&&!tmpSta.peek().equals("("))
                            {
                                rpn.push(tmpSta.pop());
                            }
                            if((!tmpSta.empty())&&(tmpSta.peek().equals("(")))
                            {//弹出(
                                tmpSta.pop();
                            }
                        }
                    }else if(!isHigh(tmpSta.peek(),str)){
                        tmpSta.push(str);
                    }
                }
            }

        }
        while(!tmpSta.empty())
        {//把栈中剩余的操作符依次弹出
            rpn.push(tmpSta.pop());
        }
        StringBuilder st = new StringBuilder();
        for (String str : rpn) {
                st.append(str);
        }
        rpn.clear();
        return st.toString();
    }

    //分割(56+4)3*6+2=>(,56,+,4,
    private String[] split(String src) {
        StringBuilder sb = new StringBuilder(src.length());
        for(char ch:src.toCharArray())
        {
            if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')
            {
                sb.append(",");
                sb.append(ch);
                sb.append(",");
            }else{
                sb.append(ch);
            }
        }
        String string = sb.toString().replaceAll(",{2,}", ",");
        return string.split(",");
    }

    //比较操作符的优先级
    private boolean isHigh(String pop, String str) {
        if(str.equals(")"))
            return true;
        if(opLs.get(pop)==null||opLs.get(str)==null)
          return false;
        return opLs.get(pop)>=opLs.get(str);

    }

    //是否是数字
    public boolean isNum(String str) {
        for (char ch : str.toCharArray()) {
            if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')
                return false;
        }
        return true;
    }

    //得到结果
    public double getRes() {
        String rpn = toRpn();
        Stack<Double> res = new Stack<Double>();
        StringBuilder sb = new StringBuilder();
        for(char ch:rpn.toCharArray())
        {
            if(ch=='(')
            {
                continue;
            }else if(ch>='0'&&ch<='9'||ch=='.'){
                sb.append(ch);
            }else if(ch==')')
            {
                res.push(Double.valueOf(sb.toString()));
                sb = new StringBuilder();
            }else{
                 if(!res.empty())
                 {
                     Double x = res.pop();
                     Double y = res.pop();
                     switch (ch) {
                    case '+':
                         res.push(y+x);
                        break;
                    case '-':
                        res.push(y-x);
                        break;
                    case '*':
                        res.push(y*x);
                        break;
                    case '%':
                    case '/':
                        if(x!=0)
                        {
                            double rsd = ch=='%'?y%x:y/x;
                            res.push(rsd);
                        }else{
                             System.out.println("分母为零");
                             res.clear();
                             return -1;
                        }
                        break;
                    }
            }
            }
        }
        Double result = res.pop();
        res.clear();
        return result;
    }

    public static void main(String[] args) {
    	long start = System.currentTimeMillis();
    	String src = "1-2*((2+4)*(6-2))";
    	CalculatorOrgin analyer = new CalculatorOrgin(src);
    	System.out.println(src + "=" + analyer.toRpn());
    	System.out.println(src + "=" + analyer.getRes());
    	String src1 = "1.33+5*(36+5)-8%3";
    	CalculatorOrgin analyer1 = new CalculatorOrgin(src1);
		System.out.println(src1 + "=" + analyer1.toRpn());
		System.out.println(src1 + "=" + analyer1.getRes());
		String src2 = "3+(4+5/5)*6";
		CalculatorOrgin analyer2 = new CalculatorOrgin(src2);
		System.out.println(src2 + "=" + analyer2.toRpn());
		System.out.println(src2 + "=" + analyer2.getRes());
		String src3 = "2*3*(3+5*2+1)";
		CalculatorOrgin analyer3 = new CalculatorOrgin(src3);
		System.out.println(src3 + "=" + analyer3.toRpn());
		System.out.println(src3 + "=" + analyer3.getRes());
		long end = System.currentTimeMillis();
		System.out.println(end - start);
    }

}
