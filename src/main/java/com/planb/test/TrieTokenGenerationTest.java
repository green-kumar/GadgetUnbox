package com.planb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieTokenGenerationTest {

	static List<String> getPowerSet(String str){
		List<String> powerSet= new ArrayList<String>(); 
	  int len = str.toString().length();
		int m = (int)Math.pow(2, len);
		char[] c= str.toString().toCharArray();
		
		for(int j =0 ;j< m ;j++){
			 StringBuffer sb= new StringBuffer();
			  for(int k=0;k<len;k++){
				  if((j & (1<<k)) > 0){
					  sb.append(c[k]);
					  
				  }
				 
			  }
			  
			  if(sb.toString().length()>0)
				  powerSet.add(sb.toString());
			  
			  
}
		return powerSet;	
	
	
	}

	private static void permutation(String prefix, String str, List<String> result) {
		int n = str.length();
		if (n == 0)
			result.add(prefix);
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), result);
		}
	}

	public static void main(String[] args) {

		List<String> finalResult = new ArrayList<String>();
		String str[] = { "samsung", "galaxy", "s7" };
		List<String> l = Arrays.asList(str);
		Map<Integer, String> map = new HashMap<Integer, String>();
		int i = 1;
		StringBuffer sb = new StringBuffer();
		for (String str1 : l) {
			map.put(i, str1);
			sb.append(i++);
		}

		
		List<String> powerSet = getPowerSet(sb.toString());
		
		for (String set : powerSet) {
			List<String> result;
			if (set.length() > 1) {
				result = new ArrayList<String>();
				permutation("", set, result);

				for (String s : result) {
					StringBuffer sbs = new StringBuffer();
					for (int x = 0; x < s.length(); x++) {
						sbs.append(map.get(Integer.parseInt(String.valueOf(s.charAt(x))))).append(" ");
					}
					finalResult.add(sbs.toString().substring(0, sbs.toString().length() - 1));
				}
			}else{
				finalResult.add(map.get(Integer.parseInt(set)));
			}

			
		}
		
		System.out.println(finalResult);

	}

}
