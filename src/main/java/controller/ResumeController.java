package controller;

import com.itextpdf.io.image.ImageData;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import model.Career;
import model.Education;
import model.Introduction;
import model.PersonInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
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
//            personInfoRow.createCell(0).setCellValue(personInfo.getPhoto());
            String photoFilename = personInfo.getPhoto();
            try (InputStream photoStream = new FileInputStream(photoFilename)) {
                // 사진 파일을 읽어들입니다.
                BufferedImage originalImage = ImageIO.read(photoStream);

                // 증명사진 크기로 이미지를 조절합니다. (가로 35mm, 세로 45mm)
                int newWidth = (int) (35 * 2.83465); // mm 단위를 픽셀 단위로 변환합니다 (1mm = 2.83465px).
                int newHeight = (int) (45 * 2.83465); // mm 단위를 픽셀 단위로 변환합니다 (1mm = 2.83465px).
                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                BufferedImage resizedBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g2d = resizedBufferedImage.createGraphics();
                g2d.drawImage(resizedImage, 0, 0, null);
                g2d.dispose();

                // 조절된 이미지를 바이트 배열로 변환합니다.
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(resizedBufferedImage, "png", baos);
                byte[] imageBytes = baos.toByteArray();
                int imageIndex = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

                // Drawing 객체를 생성하고 이미지를 삽입합니다.
                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, 0, 1, 1, 2);
                drawing.createPicture(anchor, imageIndex);

                // 이미지가 삽입된 행의 높이와 열의 너비를 조정합니다.
                // 96은 화면의 DPI(Dots Per Inch, 인치당 도트 수)
                // Excel에서 셀의 높이는 포인트(point) 단위로 표시(1 포인트는 1/72 인치입니다)
                personInfoRow.setHeightInPoints(newHeight*72/96); // 픽셀을 point로변경
                // 8이란 값은, 엑셀에서 사용되는 기본 문자 폭의 값
                // 엑셀에서는 한 개의 문자가 차지하는 너비를 1/256 단위로 계산
                int columnWidth = (int) Math.floor(((float) newWidth / (float) 8) * 256);
                sheet.setColumnWidth(0, columnWidth);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

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
            Row introductionRow = sheet.createRow(row);
            Cell introductionCell = introductionRow.createCell(0);
            introductionCell.setCellStyle(this.getWrapCellStyle());
            introductionCell.setCellValue(new XSSFRichTextString(introduction.getIntroduction().replaceAll("\n", String.valueOf((char) 10))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XSSFCellStyle getWrapCellStyle() {
        // 엑셀에서 보기 좋게 자동 개행 설정
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setWrapText(true);
        return style;
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
