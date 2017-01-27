package com.planb.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class DoubleToOneDecimalPoint {
public static void main(String[] args) {
	double s1=92.9888;
	double s2=85;
	
	double s11 = (s1/20);
	double s22 = (s2/20);
			
	DecimalFormat df = new DecimalFormat("#.#");
	//df.setRoundingMode(RoundingMode.CEILING);
	
	
	System.out.println(s11+" "+Double.parseDouble(df.format(s11)));
	System.out.println(s22+" "+Double.parseDouble(df.format(s22)));
	
	/*for (Number n : Arrays.asList(12, 123.12345, 0.23, 0.1, 2341234.212431324)) {
	    Double d = n.doubleValue();
	    System.out.println(df.format(d));
	}*/
}
}

