package com.demo.data.struct.string;

import java.util.Arrays;

/**而在KMP算法中，对于每一个模式串我们会事先计算出模式串的内部匹配信息，在匹配失败时最大的移动模式串，以减少匹配次数。
比如，在简单的一次匹配失败后，我们会想将模式串尽量的右移和主串进行匹配。右移的距离在KMP算法中是如此计算的：在已经匹配的模式串子串中，找出最长的相同的前缀和后缀，然后移动使它们重叠。
前缀开始永远从模式串的第一个字符开始，后缀从失配的字符的前一个字符结束。

next数组的求解方法是：第一位的next值为0，第二位的next值为1，后面求解每一位的next值时，根据前一位进行比较。首先将前一位与其next值对应的内容进行比较，如果相等，则该位的next值就是前一位的next值加上1；如果不等，向前继续寻找next值对应的内容来与前一位进行比较，直到找到某个位上内容的next值对应的内容与前一位相等为止，则这个位对应的值加上1即为需求的next值；如果找到第一位都没有找到与前一位相等的内容，那么需求的位上的next值即为1。
       看起来很令人费解，利用上面的例子具体运算一遍。
       str: abaabcab
       next:01122312
       1.前两位必定为0和1。
       2.计算第三位的时候，看第二位b的next值，为1，则把b和1对应的a进行比较，不同，则第三位a的next的值为1，因为一直比到最前一位，都没有发生比较相同的现象。
       3.计算第四位的时候，看第三位a的next值，为1，则把a和1对应的a进行比较，相同，则第四位a的next的值为第三位a的next值加上1。为2。因为是在第三位实现了其next值对应的值与第三位的值相同。
       4.计算第五位的时候，看第四位a的next值，为2，则把a和2对应的b进行比较，不同，则再将b对应的next值1对应的a与第四位的a进行比较，相同，则第五位的next值为第二位b的next值加上1，为2。因为是在第二位实现了其next值对应的值与第四位的值相同。
       5.计算第六位的时候，看第五位b的next值，为2，则把b和2对应的b进行比较，相同，则第六位c的next值为第五位b的next值加上1，为3，因为是在第五位实现了其next值对应的值与第五位相同。
       6.计算第七位的时候，看第六位c的next值，为3，则把c和3对应的a进行比较，不同，则再把第3位a的next值1对应的a与第六位c比较，仍然不同，则第七位的next值为1。
       7.计算第八位的时候，看第七位a的next值，为1，则把a和1对应的a进行比较，相同，则第八位c的next值为第七位a的next值加上1，为2，因为是在第七位和实现了其next值对应的值与第七位相同。

	另一个算法：
	   str: abaabcab
       next:01122312
       1.前两位必定为0和1。
       2.计算第三位的时候，从开始到第二位的串是ab，前缀与后缀不相等，next为1
       3.计算第四位的时候，从开始到第三位的串是aba，前缀a与后缀a相等，next为相等字符的个数+1为2
       4.计算第五位的时候，从开始到第四位的串是abaa，前缀a与后缀a相等，next为相等字符的个数+1为2
       4.计算第六位的时候，从开始到第五位的串是abaab，前缀ab与后缀ab相等，next为相等字符的个数+1为3
       4.计算第七位的时候，从开始到第六位的串是abaabc，前缀与后缀不相等，next为1
       4.计算第八位的时候，从开始到第七位的串是abaabca，前缀a与后缀a相等，next为2
       在计算nextval之前要先弄明白，nextval是为了弥补next函数在某些情况下的缺陷而产生的，例如主串为“aaabaaaab”、模式串为“aaaab”那么，比较的时候就会发生一些浪费的情况：比较到主串以及模式串的第四位时，发现其值并不相等，据我们观察，我们可以直接从主串的第五位开始与模式串进行比较，而事实上，却进行了几次多余的比较。使用nextval可以去除那些不必要的比较次数。
 * @author DELL
 *
 */
public class KMPDemo {

	public static int[] kmpnext(String dest){
        int[] next = new int[dest.length()];
        //第一个写死为0，第二个写死0
        next[0] = 0;
        next[1] = 0;
        int i = 1,j = 0;
        while(i < dest.length() - 1){
        	if(j == 0 && dest.charAt(i) != dest.charAt(j)){
        		i++;
                next[i] = 0;
        	}else{
	            if(dest.charAt(i) == dest.charAt(j)){
	                j++;
	                i++;
	                next[i] = j;
	            }else{
	            	j = next[j];
	            }
        	}
        }
        return next;
    }

	public static void search(String original, String find, int next[]) {
	    int j = 0;
	    for (int i = 0; i < original.length(); i++) {
	        while (j > 0 && original.charAt(i) != find.charAt(j))
	            j = next[j];
	        if (original.charAt(i) == find.charAt(j))
	            j++;
	        if (j == find.length()) {
	            System.out.println("find at position " + (i - j));
	            System.out.println(original.subSequence(i - j + 1, i + 1));
	            j = 0;
	        }
	    }
	}

	public static void main(String[] args) {
		String find = "aba";
		int[] next = kmpnext(find);
		Arrays.stream(next).forEach(num -> System.out.print(num + ", "));
		System.out.println("=================");
		search("friend my abccbac name syncabaproble", find, next);
	}

}
