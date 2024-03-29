package model;

public class Introduction {
    private String introduction; // 자기소개서

    public Introduction() {
        this.introduction = "";
    }

    @Override
    public String toString() {
        return "Introduction{" +
                "introduction='" + introduction + '\'' +
                '}';
    }

    public String getIntroduction() {
        return introduction;
    }

    public void addIntroduction(String introduction) {
        this.introduction += introduction;
    }
}
