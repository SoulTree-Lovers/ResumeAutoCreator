# ResumeAutoCreator

## 프로젝트 개요
- 이력 정보와 자기소개서를 키보드로부터 입력 받아 Excel 파일을 생성하는 프로그램

## 프로젝트 기능 정의
1. MVC 패턴으로 구성
2. View 클래스에서 Scanner를 통해 사용자로부터 입력을 받음
3. 입력받은 정보를 Model 클래스에 저장
4. Controller 클래스에서 Model에 저장된 정보를 이용하여 엑셀 파일을 생성
5. 엑셀 파일을 생성할 때, Apache POI 라이브러리를 사용
6. 엑셀 파일 생성이 완료되면 View 클래스에서 “이력서가 생성되었습니다.”라는 메시지 출력
7. 자기소개서의 경우 Scanner.nextLine() 메소드를 통해 여러 줄에 걸쳐 입력을 받을 수 있도록 하고, 엑셀 파일에 저장할 때는 “\n”을 이용하여 줄바꿈을 유지하도록 함
8. PNG 형식의 이미지를 엑셀 파일에 저장하기 위해, Apache POI 라이브러리를 사용하여 이미지를 삽입

## 클래스 설계

- Career: 경력 정보를 저장
    - workPeriod
    - companyName
    - jobTitle
- Education: 학력 정보를 저장
    - graduationYear
    - schoolName
    - major
- PersonInfo: 개인 정보를 저장
    - photo
    - name
    - email
    - address
    - phoneNumber
- ResumeView: 이력서 작성에 필요한 정보를 입력받는 역할
    - inputPersonInfo()
    - inputEducationList()
    - inputCareerList()
    - inputSelfIntroduction()
- ResumeController(메인):  입력받은 정보를 바탕으로 이력서를 생성하는 역할
    - createResume()
    - createResumeSheet()
    - createSelfIntroductionSheet()
    - getWrapCellStyle() → 엑셀에서 자동 개행 설정을 위함
    - saveWorkbookToFile()
