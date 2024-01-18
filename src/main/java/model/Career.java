package model;

public class Career {
    private String companyName; // 근무처
    private String jobTitle; // 담당업무
    private String employmentPeriod; // 근무기간
    private Integer yearsAtCompany; // 근속연수

    public Career(String companyName, String jobTitle, String employmentPeriod, Integer yearsAtCompany) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.employmentPeriod = employmentPeriod;
        this.yearsAtCompany = yearsAtCompany;
    }

    @Override
    public String toString() {
        return "Career{" +
                "companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", employmentPeriod=" + employmentPeriod +
                ", yearsAtCompany=" + yearsAtCompany +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmploymentPeriod() {
        return employmentPeriod;
    }

    public void setEmploymentPeriod(String employmentPeriod) {
        this.employmentPeriod = employmentPeriod;
    }

    public Integer getYearsAtCompany() {
        return yearsAtCompany;
    }

    public void setYearsAtCompany(Integer yearsAtCompany) {
        this.yearsAtCompany = yearsAtCompany;
    }
}
