package com.planb.test;

import com.planb.search.service.Trie;

public class TrieTest {
	public static void main(String[] args) {
		Trie t =new Trie();
		String str[] = {"to","tea","toy","green","gas","girl","galaxy"}; 
		for(String st : str){
		t.load(st);
		}
		
		System.out.println(t.findCompletions("t"));
		System.out.println(t.findCompletions("g"));
		System.out.println(t.findCompletions("go"));
		System.out.println(t.findCompletions("to"));
		System.out.println(t.findCompletions("ga"));
		
	}

}
