import controller.ResumeController;
import java.util.ArrayList;
import model.Career;
import model.Education;
import model.Introduction;
import model.PersonInfo;
import view.ResumeView;

public class Main {
    private ArrayList<Education> educations;
    private ArrayList<Career> careers;

    public static void main(String[] args) {
        // 모델 객체 생성
        ResumeView resumeView = new ResumeView();
        PersonInfo personInfo = new PersonInfo();
        ArrayList<Education> educations = new ArrayList<>();
        ArrayList<Career> careers = new ArrayList<>();
        Introduction introduction = new Introduction();
        ResumeController resumeController = new ResumeController();

        // 모델 데이터 입력
        resumeController.createResume(resumeView, personInfo, educations, careers, introduction);

        // 콘솔에 출력 (디버깅용)
        resumeView.printAll(personInfo, educations, careers, introduction);

        // 엑셀 파일 생성 및 저장
        resumeController.createResumeSheet(personInfo, educations, careers);
        resumeController.createIntroductionSheet(introduction);
        resumeController.saveWorkbookToFile();
    }
}
