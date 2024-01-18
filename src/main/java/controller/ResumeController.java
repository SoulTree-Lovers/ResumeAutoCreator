package controller;

import com.itextpdf.io.image.ImageData;
import java.io.FileOutputStream;
import java.util.ArrayList;
import model.Career;
import model.Education;
import model.Introduction;
import model.PersonInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.ResumeView;


public class ResumeController {
    private XSSFWorkbook workbook;
    public ResumeController() {
        workbook = new XSSFWorkbook();
    }

    public void createResume(ResumeView resumeView, PersonInfo personInfo, ArrayList<Education> educations, ArrayList<Career> careers, Introduction introduction) {
        resumeView.inputPersonInfo(personInfo);
        resumeView.inputEducation(educations);
        resumeView.inputCareer(careers);
        resumeView.inputIntroduction(introduction);
    }

    public void createResumeSheet(PersonInfo personInfo, ArrayList<Education> educations, ArrayList<Career> careers) {
        try {
            Sheet sheet = workbook.createSheet("이력서");
            Integer row = 0;

            // 개인정보 헤더
            Row headerRow = sheet.createRow(row);
            headerRow.createCell(0).setCellValue("프로필 사진");
            headerRow.createCell(1).setCellValue("이름");
            headerRow.createCell(2).setCellValue("이메일");
            headerRow.createCell(3).setCellValue("주소");
            headerRow.createCell(4).setCellValue("전화번호");
            headerRow.createCell(5).setCellValue("생년월일");
            row++;

            // 개인정보 데이터 생성
            Row personInfoRow = sheet.createRow(row);
            personInfoRow.createCell(0).setCellValue(personInfo.getPhoto());
            personInfoRow.createCell(1).setCellValue(personInfo.getName());
            personInfoRow.createCell(2).setCellValue(personInfo.getEmail());
            personInfoRow.createCell(3).setCellValue(personInfo.getAddress());
            personInfoRow.createCell(4).setCellValue(personInfo.getPhoneNumber());
            personInfoRow.createCell(5).setCellValue(personInfo.getBirthDate());
            row++;

            // 학력 헤더
            Row educationHeaderRow = sheet.createRow(row);
            educationHeaderRow.createCell(0).setCellValue("졸업년도");
            educationHeaderRow.createCell(1).setCellValue("학교명");
            educationHeaderRow.createCell(2).setCellValue("전공");
            educationHeaderRow.createCell(3).setCellValue("졸업여부");
            row++;

            for (int i=0; i< educations.size(); i++) {
                Education education = educations.get(i);
                Row educationRow = sheet.createRow(row);
                educationRow.createCell(0).setCellValue(education.getGraduationYear());
                educationRow.createCell(1).setCellValue(education.getSchoolName());
                educationRow.createCell(2).setCellValue(education.getMajor());
                educationRow.createCell(3).setCellValue(education.getGraduationStatus());
                row++;
            }

            // 경력 헤더
            Row careerHeaderRow = sheet.createRow(row);
            careerHeaderRow.createCell(0).setCellValue("근무처");
            careerHeaderRow.createCell(1).setCellValue("담당업무");
            careerHeaderRow.createCell(2).setCellValue("근무기간");
            careerHeaderRow.createCell(3).setCellValue("근속연수");
            row++;

            for (int i=0; i< careers.size(); i++) {
                Career career = careers.get(i);
                Row careerRow = sheet.createRow(row);
                careerRow.createCell(0).setCellValue(career.getCompanyName());
                careerRow.createCell(1).setCellValue(career.getJobTitle());
                careerRow.createCell(2).setCellValue(career.getEmploymentPeriod());
                careerRow.createCell(3).setCellValue(career.getYearsAtCompany());
                row++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createIntroductionSheet(Introduction introduction) {
        try {
            Sheet sheet = workbook.createSheet("자기소개서");
            Integer row = 0;

            // 자기소개서 헤더
            Row headerRow = sheet.createRow(row);
            headerRow.createCell(0).setCellValue("자기소개서");
            row++;

            // 자기소개서 데이터 생성
            Row personInfoRow = sheet.createRow(row);
            personInfoRow.createCell(0).setCellValue(introduction.getIntroduction());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getWrapCellStyle() {
        // 엑셀에서 보기 좋게 자동 개행 설정
        CellStyle wrapTextStyle = workbook.createCellStyle();
        wrapTextStyle.setWrapText(true);

        // 전체 셀에 셀 스타일 적용
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);

                if (row != null) {
                    for (int k = 0; k < row.getLastCellNum(); k++) {
                        Cell cell = row.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cell.setCellStyle(wrapTextStyle);
                    }
                }
            }
        }
    }

    public void saveWorkbookToFile() {
        try {
            String fileName = "resume.xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            workbook.write(fileOutputStream);
            workbook.close();
            System.out.println("자기소개서 엑셀 파일이 저장되었습니다: " + fileName);
        } catch (Exception e) {
            System.out.println("엑셀 파일 저장 중 오류가 발생하였습니다.");
            e.printStackTrace();
        }

    }
}
