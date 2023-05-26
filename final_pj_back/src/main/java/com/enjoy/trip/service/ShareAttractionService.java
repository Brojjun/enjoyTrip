package com.enjoy.trip.service;


import java.util.List;

import com.enjoy.trip.dto.ShareAttraction;

public interface ShareAttractionService {


	void writeShareAttraction(ShareAttraction param) throws Exception;

	List<ShareAttraction> selectShareAttraction() throws Exception;

	void deleteShareAttraction(int shareNo) throws Exception;


}
