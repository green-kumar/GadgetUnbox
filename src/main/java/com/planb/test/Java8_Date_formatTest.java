package com.planb.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;

import com.planb.dao.mobile.Mobile;
import com.planb.metadata.ProductMetaData;
import com.planb.product.util.EUtil;


public class Java8_Date_formatTest extends HashSet{
	
	void t(int i){
		System.out.println("int");
	}
	void t(long l){
		System.out.println("ling");
	}
	void t(short i){
		System.out.println("short");
	}
	
	static Object obj[]={
			
			new Boolean(true),
			new Boolean(true),
		new Boolean(true)	
	};
	
	
	public  synchronized void test(Object o){
		synchronized(String.class){
			
		}
		
	}
  
	public static void main(String[] args) throws Exception {
		
		
		
		List<ProductMetaData> pmd = new ArrayList<ProductMetaData>();
		ProductMetaData p1=new ProductMetaData();
		ProductMetaData p2=new ProductMetaData();
		ProductMetaData p3=new ProductMetaData();
		p1.setReleaseDate("20 may,2016");
		p2.setReleaseDate("21 may,2016");
		p3.setReleaseDate("22 may,2016");
		pmd.add(p1);
		pmd.add(p2);
		pmd.add(p3);
		Collections.sort(pmd);
		for(ProductMetaData pmd1 : pmd)
		System.out.println(pmd1.getReleaseDate());
		
		
		/*String releaseDate = "20 may,2016(official)";
		
		if(releaseDate.contains("(")){
	    	 releaseDate = releaseDate.substring(0,releaseDate.indexOf("("));
	     }
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		System.out.println(dateFormat.parse(releaseDate));
		String str="green"+UUID.randomUUID().toString().substring(0, 10);
		System.out.println(str.substring(0,str.length()-10));
		
		
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat1.format(new Date()));*/
	
		/*
		
		int sum=0;
		int p,q;
		
		while(true){
			if(sum == 0){
				if(true){
					break;
				}
			}
		}
		
		System.out.println("break");
		
		for(p=0,q=0; p<5& q<5;++p,q=p+1){
			sum+=p;
			
		}
		System.out.println(sum);
		int t11=3;
		if(t11<4)
			if(t11==2)
				System.out.println("");
		else
				System.out.println("sd");
		System.out.println("jsk");
		
		short s=1;
		int s1=4;
		
	
		
		Test t=new Test();
		t.t(s1);
		t.t(s);
		System.out.println("asda"+t.size());
				
		
		Runnable r=new Runnable() {
			
			@Override
			public void run() {
				System.out.println("foo");// TODO Auto-generated method stub
				
			}
		};
		
		Thread t1=new Thread(r);
		t1.start();
		
		Arrays.sort(obj);
		for(Object obj1:obj){
			System.out.println(obj1.toString());
		}
		
		
		HashSet hs=new HashSet();
		hs.add(new String("abc"));
		hs.add(new String("abd"));
          System.out.println(hs);		
		
		
		Integer i=new Integer(4)+new Integer(6);
		System.out.println(i.intValue());
		
		TreeMap<Integer,String> reverseSorted=new TreeMap<Integer,String>(new Comparator<Integer>(
				) {

					@Override
					public int compare(Integer o1, Integer o2) {
						return o2>o1?1:(o1>o2?-1:0);
					}
		});
		
		
		reverseSorted.put(500, "5");
		reverseSorted.put(600, "6");
		reverseSorted.put(700, "7");
		
		System.out.println(reverseSorted.firstEntry().getValue());
		//Op:  7
		
		TreeMap<Integer,String> sorted=new TreeMap<Integer,String>();
		sorted.put(500, "5");
		sorted.put(600, "6");
		sorted.put(700, "7");
		
		System.out.println(sorted.firstEntry().getValue());
		//op: 5
		
		
		
		
		
		
		
		
		//Collections.sort(hm,);
		
		
		
		
		class temp{
			int a;
		    String str;
			public int getA() {
				return a;
			}
			public void setA(int a) {
				this.a = a;
			}
			public String getStr() {
				return str;
			}
			public void setStr(String str) {
				this.str = str;
			}
			public temp(int a, String str) {
				super();
				this.a = a;
				this.str = str;
			}
		    
		}    
		temp t1=new temp(1, "ajay");
		temp t2=new temp(3, "ajay");
		temp t3=new temp(2, "1ajay");
		temp t4=new temp(4, "1ajay");
		 List<temp> list=new ArrayList<temp>();
		 list.add(t1);
		 list.add(t2);
		 list.add(t3);
		 list.add(t4);
		 
		 
		 List<String> f=list.stream().filter(l->l.getA()==2 && l.getStr().equals("1ajay")).map(temp::getStr).collect(Collectors.toList());
		 System.out.println(f);//[1ajay, 1ajay]
		 Map<Integer,String> f1=list.stream().filter(l->l.getA()==2 && l.getStr().equals("1ajay")).collect(Collectors.toMap(temp::getA, temp::getStr));
		
		System.out.println(f1);//{2=1ajay}
		
		Map<Integer,temp> m=list.stream().filter(l->l.getA()==2 && l.getStr().equals("1ajay")).collect(Collectors.toMap(temp::getA, Function.identity()));
		
		System.out.println(m);//{2=com.planb.controller.impl.Test$1temp@378bf509}
		
		Map<Integer,Integer> abc=new HashMap<Integer,Integer>();
		abc.put(1, 1);
		abc.put(1, 2);
		Mobile m=new Mobile();
		System.out.println(m.getOverView());
		//System.out.println(m.getOverView().getDescription());
		System.out.println(PropertyUtils.getProperty(m, "overView.description"));
		
	*/}
}
