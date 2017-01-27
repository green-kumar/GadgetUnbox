package com.planb.search.dao;

import java.util.HashSet;
import java.util.Set;

public class SearchTokenToSearchProductMap {

	

	String searchToken;
	Set<String> SearchProductDaoIdList;
	
	public SearchTokenToSearchProductMap(){
		SearchProductDaoIdList = new HashSet<String>();
	}

	public String getSearchToken() {
		return searchToken;
	}

	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
	}

	public Set<String> getSearchProductDaoIdList() {
		return SearchProductDaoIdList;
	}

	public void setSearchProductDaoIdList(Set<String> searchProductDaoIdList) {
		SearchProductDaoIdList = searchProductDaoIdList;
	}

	
	@Override
	public String toString() {
		return "SearchTokenToSearchProductMap [searchToken=" + searchToken + ", SearchProductDaoIdList="
				+ SearchProductDaoIdList + "]";
	}
	



}
