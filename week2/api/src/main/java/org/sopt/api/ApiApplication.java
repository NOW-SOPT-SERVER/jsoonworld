package org.sopt.api;

import org.sopt.api.domain.Member;
import org.sopt.api.domain.Part;
import org.sopt.api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Autowired
	private MemberRepository memberRepository;

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			// 멤버 생성 및 저장
			memberRepository.save(new Member("남궁찬", Part.DESIGN, 30));
			memberRepository.save(new Member("강현욱", Part.IOS, 21));
			memberRepository.save(new Member("양대한", Part.ANDROID, 27));

			// 모든 멤버 정보 조회
			String url = "http://localhost:8080/api/v1/members/all";
			String response = restTemplate.getForObject(url, String.class);
			System.out.println("Response from API: " + response);
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
