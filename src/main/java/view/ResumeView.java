package view;

import java.util.ArrayList;
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

    public void inputPersonInfo(PersonInfo personInfo) {
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

    public void inputEducation(ArrayList<Education> educations) {
        System.out.println("<학력 정보 입력>");

        while (true) {
            System.out.print("학교명을 입력하세요: (종료 시 'q' 입력)");
            String schoolName = scanner.nextLine();
            if (schoolName.equals("q")) {
                break;
            }
            System.out.print("전공을 입력하세요: ");
            String major = scanner.nextLine();
            System.out.print("졸업여부를 입력하세요: ");
            String graduationStatus = scanner.nextLine();
            System.out.print("졸업년도를 입력하세요: ");
            String graduationYear = scanner.nextLine();

            educations.add(new Education(graduationYear, schoolName, major, graduationStatus));
        }
    }

    public void inputCareer(ArrayList<Career> careers) {
        System.out.println("<경력 정보 입력>");

        while (true) {
            System.out.print("회사명을 입력하세요: (종료 시 'q' 입력)");
            String companyName = scanner.nextLine();
            if (companyName.equals("q")) {
                break;
            }
            System.out.print("담당업무를 입력하세요: ");
            String jobTitle = scanner.nextLine();
            System.out.print("근무기간을 입력하세요: ");
            String employmentPeriod = scanner.nextLine();
            System.out.print("근속연수를 입력하세요: ");
            Integer yearsAtCompany = scanner.nextInt();
            scanner.nextLine();

            careers.add(new Career(companyName, jobTitle, employmentPeriod, yearsAtCompany));
        }
    }

    public void inputIntroduction(Introduction introduction) {
        System.out.println("<자기소개서> (종료 시 엔터 키 입력)");

        while (true) {
            String curr = scanner.nextLine();

            if (curr.isEmpty()) {
                System.out.println("자기소개서 입력을 종료합니다.");
                break;
            }

            introduction.addIntroduction(curr);
            introduction.addIntroduction("\n");
        }
    }

    public void printAll(PersonInfo personInfo, ArrayList<Education> educations, ArrayList<Career> careers, Introduction introduction) {
        System.out.println("<현재 저장된 모든 모델 정보>");
        System.out.println(personInfo);
        for (Education education : educations) {
            System.out.println(education);
        }
        for (Career career : careers) {
            System.out.println(career);
        }
        System.out.println(introduction);
    }
}
