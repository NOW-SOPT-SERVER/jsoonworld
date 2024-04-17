package org.sopt.ofcoursemarket;

import org.sopt.ofcoursemarket.domain.Item;
import org.sopt.ofcoursemarket.domain.TradeType;
import org.sopt.ofcoursemarket.domain.TradingLocation;
import org.sopt.ofcoursemarket.dto.ItemDTO;
import org.sopt.ofcoursemarket.dto.MemberCreateDto;
import org.sopt.ofcoursemarket.service.ItemService;
import org.sopt.ofcoursemarket.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OfcoursemarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfcoursemarketApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MemberService memberService, ItemService itemService) {
		return args -> {
			// 첫 번째 회원 생성 및 상품 등록
			MemberCreateDto firstMemberDto = new MemberCreateDto("1", "권장순", 26);
			Long firstMemberId = memberService.createMember(firstMemberDto);
			ItemDTO firstItemDto = new ItemDTO("Smart TV", "55 inch 4K OLED", 1500000, TradeType.SALE, true, TradingLocation.HAPJEONG, firstMemberId);
			Item firstItem = itemService.createItem(firstItemDto);
			System.out.println("First item created with ID: " + firstItem.getId() + " and title: " + firstItem.getTitle());

			// 두 번째 회원 생성 및 상품 등록
			MemberCreateDto secondMemberDto = new MemberCreateDto("2", "조나단 핑글", 28);
			Long secondMemberId = memberService.createMember(secondMemberDto);
			ItemDTO secondItemDto = new ItemDTO("Mountain Bike", "Carbon fiber frame, 21 speed", 250000, TradeType.DONATION, false, TradingLocation.GWANAK, secondMemberId);
			Item secondItem = itemService.createItem(secondItemDto);
			System.out.println("Second item created with ID: " + secondItem.getId() + " and title: " + secondItem.getTitle());

			// 세 번째 회원 생성 및 상품 등록
			MemberCreateDto thirdMemberDto = new MemberCreateDto("3", "남궁찬", 35);
			Long thirdMemberId = memberService.createMember(thirdMemberDto);
			ItemDTO thirdItemDto = new ItemDTO("Coffee Machine", "Espresso machine with grinder", 600000, TradeType.SALE, true, TradingLocation.SADANG, thirdMemberId);
			Item thirdItem = itemService.createItem(thirdItemDto);
			System.out.println("Third item created with ID: " + thirdItem.getId() + " and title: " + thirdItem.getTitle());
		};
	}

}
