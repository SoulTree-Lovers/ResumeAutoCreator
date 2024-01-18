package view;

import java.util.Scanner;
import model.Career;
import model.Education;
import model.Introduction;
import model.PersonInfo;

public class ResumeView {
    private Scanner scanner;

    public ResumeView() {
        this.scanner = new Scanner(System.in);
    }

    public PersonInfo inputPersonInfo() {
        System.out.println("<개인정보 입력>");

        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("이메일 주소를 입력하세요: ");
        String email = scanner.nextLine();
        System.out.print("집 주소를 입력하세요: ");
        String address = scanner.nextLine();
        System.out.print("전화번호를 입력하세요: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("생년월일을 입력하세요: ");
        String birthDate = scanner.nextLine();
        System.out.print("사진 링크 주소를 입력하세요: ");
        String photo = scanner.nextLine();
        return new PersonInfo(photo, name, email, address, phoneNumber, birthDate);
    }

    public Education inputEducation() {
        System.out.println("<학력 정보 입력>");

        System.out.print("학교명을 입력하세요: ");
        String schoolName = scanner.nextLine();
        System.out.print("전공을 입력하세요: ");
        String major = scanner.nextLine();
        System.out.print("졸업여부를 입력하세요: ");
        String graduationStatus = scanner.nextLine();
        System.out.print("졸업년도를 입력하세요: ");
        String graduationYear = scanner.nextLine();

        return new Education(graduationYear, schoolName, major, graduationStatus);
    }

    public Career inputCareer() {
        System.out.println("<경력 정보 입력>");

        System.out.print("회사명을 입력하세요: ");
        String companyName = scanner.nextLine();
        System.out.print("담당업무를 입력하세요: ");
        String jobTitle = scanner.nextLine();
        System.out.print("근무기간을 입력하세요: ");
        Integer employmentPeriod = scanner.nextInt();
        scanner.nextLine();
        System.out.print("근속연수를 입력하세요: ");
        Integer yearsAtCompany = scanner.nextInt();
        scanner.nextLine();

        return new Career(companyName, jobTitle, employmentPeriod, yearsAtCompany);
    }

    public Introduction inputIntroduction() {
        System.out.println("<자기소개서> (종료 시 엔터 키 입력)");
        Introduction introduction = new Introduction();

        while (true) {
            String curr = scanner.nextLine();

            if (curr.equals("\n")) {
                System.out.println("자기소개서 입력을 종료합니다.");
                break;
            }

            introduction.addIntroduction(curr);
            introduction.addIntroduction("\n");
        }

        return introduction;
    }
}
