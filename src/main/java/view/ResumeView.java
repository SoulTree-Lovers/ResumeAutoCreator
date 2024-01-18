package view;

import java.util.Scanner;
import model.Career;
import model.Education;
import model.Introduction;
import model.PersonInfo;

public class ResumeView {
    private Scanner scanner;
    private Career career;
    private Education education;
    private Introduction introduction;
    private PersonInfo personInfo;

    public ResumeView(Career career, Education education, Introduction introduction, PersonInfo personInfo) {
        this.scanner = new Scanner(System.in);
        this.career = career;
        this.education = education;
        this.introduction = introduction;
        this.personInfo = personInfo;
    }

    public void inputPersonInfo() {
        System.out.println("<개인정보 입력>");

        System.out.print("이름을 입력하세요: ");
        personInfo.setName(scanner.nextLine());
        System.out.print("이메일 주소를 입력하세요: ");
        personInfo.setEmail(scanner.nextLine());
        System.out.print("집 주소를 입력하세요: ");
        personInfo.setAddress(scanner.nextLine());
        System.out.print("전화번호를 입력하세요: ");
        personInfo.setPhoneNumber(scanner.nextLine());
        System.out.print("생년월일을 입력하세요: ");
        personInfo.setBirthDate(scanner.nextLine());
        System.out.print("사진 링크 주소를 입력하세요: ");
        personInfo.setPhoto(scanner.nextLine());
    }

    public void inputEducation() {
        System.out.println("<학력 정보 입력>");

        System.out.print("학교명을 입력하세요: ");
        education.setSchoolName(scanner.nextLine());
        System.out.print("전공을 입력하세요: ");
        education.setMajor(scanner.nextLine());
        System.out.print("졸업여부를 입력하세요: ");
        education.setGraduationStatus(scanner.nextLine());
        System.out.print("졸업년도를 입력하세요: ");
        education.setGraduationYear(scanner.nextLine());
    }

    public void inputCareer() {
        System.out.println("<경력 정보 입력>");

        System.out.print("회사명을 입력하세요: ");
        career.setCompanyName(scanner.nextLine());
        System.out.print("담당업무를 입력하세요: ");
        career.setJobTitle(scanner.nextLine());
        System.out.print("근무기간을 입력하세요: ");
        career.setEmploymentPeriod(scanner.nextInt());
        scanner.nextLine();
        System.out.print("근속연수를 입력하세요: ");
        career.setYearsAtCompany(scanner.nextInt());
        scanner.nextLine();
    }

    public void inputIntroduction() {
        System.out.println("<자기소개서> (종료 시 엔터 키 입력)");

        while (true) {
            String curr = scanner.nextLine();

            if (curr.equals("\n")) {
                System.out.println("자기소개서 입력을 종료합니다.");
                break;
            }

            introduction.addIntroduction(curr);
            introduction.addIntroduction("\n");
        }
    }
}
