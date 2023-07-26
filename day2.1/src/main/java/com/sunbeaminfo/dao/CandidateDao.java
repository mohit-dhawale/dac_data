package com.sunbeaminfo.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import com.sunbeaminfo.pojos;

public interface CandidateDao {
//add a method to get list of all candidates
	List<CandidateDao> getAllCandidates() throws SQLException;
	//add a method to increment chosen candidate votes
	String updateVotes(int candidateId) throws SQLException;
	//add a method to get top 2 candidates
	List<CandidateDao> getTop2Candidates() throws SQLException;
	//add a method to get party wise votes
	LinkedHashMap<String, Integer> getPartywiseVotes() throws SQLException;
}
