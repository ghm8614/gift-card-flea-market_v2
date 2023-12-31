package com.ghm.giftcardfleamarket.domain.item.service;

import static com.ghm.giftcardfleamarket.global.util.PriceCalculationUtil.*;
import static com.ghm.giftcardfleamarket.global.util.constants.PageSize.*;
import static com.ghm.giftcardfleamarket.global.util.constants.PriceRate.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import com.ghm.giftcardfleamarket.domain.item.domain.Item;
import com.ghm.giftcardfleamarket.domain.item.dto.response.ItemListResponse;
import com.ghm.giftcardfleamarket.domain.item.dto.response.ItemResponse;
import com.ghm.giftcardfleamarket.domain.item.mapper.ItemMapper;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

	@Mock
	private ItemMapper itemMapper;

	@InjectMocks
	private ItemService itemService;

	private Map<String, Object> brandIdAndPageInfo;

	private List<Item> itemList;

	private List<ItemResponse> itemResponseList;

	@BeforeEach
	void setUp() {

		brandIdAndPageInfo = Map.ofEntries(
			Map.entry("id", 1L),
			Map.entry("pageSize", ITEM.getValue()),
			Map.entry("offset", PageRequest.of(0, ITEM.getValue()).getOffset()));

		itemList = Arrays.asList(
			new Item("카페아메리카노", 4500),
			new Item("카페라떼", 5000),
			new Item("콜드브루", 4900),
			new Item("자바칩 프라푸치노", 6300));

		itemResponseList = Arrays.asList(
			ItemResponse.builder()
				.name("카페아메리카노")
				.price(4500)
				.discountPrice(calculatePrice(4500, STANDARD_DISCOUNT))
				.build(),

			ItemResponse.builder()
				.name("카페라떼")
				.price(5000)
				.discountPrice(calculatePrice(5000, STANDARD_DISCOUNT))
				.build(),

			ItemResponse.builder()
				.name("콜드브루")
				.price(4900)
				.discountPrice(calculatePrice(4900, STANDARD_DISCOUNT))
				.build(),

			ItemResponse.builder()
				.name("자바칩 프라푸치노")
				.price(6300)
				.discountPrice(calculatePrice(6300, STANDARD_DISCOUNT))
				.build());
	}

	@Test
	@DisplayName("스타벅스 브랜드에 해당하는 아이템 목록을 조회하는데 성공한다.")
	void getItemsByBrandSuccess() {
		given(itemMapper.selectItemsByBrand(brandIdAndPageInfo)).willReturn(itemList);

		ItemListResponse result = itemService.getItemsByBrand(1L, 0);

		then(itemMapper).should().selectItemsByBrand(brandIdAndPageInfo);
		assertEquals(result.getItemResponseList().get(0).getDiscountPrice(),
			itemResponseList.get(0).getDiscountPrice());
	}
}