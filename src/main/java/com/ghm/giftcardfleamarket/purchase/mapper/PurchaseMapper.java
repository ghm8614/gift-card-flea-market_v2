package com.ghm.giftcardfleamarket.purchase.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.ghm.giftcardfleamarket.purchase.domain.Purchase;

@Mapper
public interface PurchaseMapper {

	void insertPurchaseGiftCard(Purchase purchase);

	List<Purchase> selectMyAvailableGiftCards(Map<String, Object> userIdAndPageInfo);

	Optional<Purchase> selectMyAvailableGiftCardDetails(Map<String, Object> userIdAndPurchaseId);
}