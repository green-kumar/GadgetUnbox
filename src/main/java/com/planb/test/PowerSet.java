package com.planb.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.entity.mime.content.StringBody;

public class PowerSet {
  public static void main(String[] args) {
	  
	  String str ="abc";
      List<String> powerSet= new ArrayList<String>(); 
	  int len = str.toString().length();
		int m = (int)Math.pow(2, len);
		char[] c= str.toString().toCharArray();
		
		for(int j =0 ;j< m ;j++){
			 StringBuffer sb= new StringBuffer();
			  for(int k=0;k<len;k++){
				  if((j & (1<<k)) > 0){
					  sb.append(c[k]);
					 // System.out.print(c[k]);
					  
				  }
				 
			  }
			  
			  if(sb.toString().length()>0)
				  powerSet.add(sb.toString());
			  
}
	
		System.out.println(powerSet);
}
}

