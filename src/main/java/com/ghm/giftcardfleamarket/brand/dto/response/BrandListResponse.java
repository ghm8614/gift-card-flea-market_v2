package com.ghm.giftcardfleamarket.brand.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BrandListResponse {
	private List<BrandResponse> brandResponseList;
}