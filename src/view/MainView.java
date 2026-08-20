package view;
import java.io.IOException;
import java.util.Scanner;

import dto.ProFileDto;
import exception.ProfileAlreadyExistsException;
import exception.ProfileNotFoundException;
import service.ProFileServiceImpl;
import service.ProFileService;

public class MainView {

	public static void main(String[] args) {
		ProFileService service = new ProFileServiceImpl();
		int i;
		Scanner sc = new Scanner(System.in);
		loop:
		while(true) {System.out.println("******프로필 관리 프로그램******");
		System.out.println("1. 프로필 저장");
		System.out.println("2. 프로필 불러오기");
		System.out.println("3. 종료");
		System.out.println("메뉴를 선택 하세요 >");
		
		i = sc.nextInt();
		
		switch(i) {
		
		case 1 : 
			System.out.println("이름 >");
			String name = sc.next();
			System.out.println("몸무게 >");
			int weight = sc.nextInt();
			System.out.println("비밀번호 >");
			int password = sc.nextInt();
			ProFileDto profiledto = new ProFileDto(name, weight, password);
			try{
				service.saveprofile(profiledto);
			}
			catch (ProfileAlreadyExistsException e) {
				e.getMessage();
				System.out.println(e.getMessage());
			}
			catch (IOException e) {
				System.out.println("프로필 저장중 오류가 발생했습니다");
			}
			break;
		case 2 :
			 System.out.println("이름 >");
			    String searchName = sc.next();

			    try {
			        ProFileDto dto = service.loadprofile(searchName);

			        System.out.println(
			            dto.getName() + "님 몸무게는 "
			            + dto.getWeight() + "kg이고 비밀번호는 "
			            + dto.getPassword() + "입니다."
			        );

			    } catch (ProfileNotFoundException e) {
			        System.out.println(e.getMessage());

			    } catch (IOException e) {
			        System.out.println("프로필 불러오기 중 오류가 발생했습니다.");
			        System.out.println(e.getMessage());
			    }

			break;
		case 3 : System.out.println("이용해 주셔서 감사합니다");
			break loop;
		}
		

	}

 }
}