package com.planb.care.customImplementation;

import java.util.ArrayList;
import java.util.Iterator;

public class OS extends ArrayList<String>
{
    public boolean contains(Object o)
    {
        if(!(o instanceof String))
        {
            return false;
        }
        String s = (String)o;
        Iterator<String> iter = iterator();
        while(iter.hasNext())
        {
            String iStr = iter.next();
            if (iStr.contains(s))
            {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
		OS os =new OS();
		os.add("Android jelly");
		System.out.println(os.contains("android"));
	}
}
