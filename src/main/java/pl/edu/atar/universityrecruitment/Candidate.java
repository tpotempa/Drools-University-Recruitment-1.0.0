package pl.edu.atar.universityrecruitment;

import java.util.ArrayList;
import java.util.List;

@org.kie.api.definition.type.PropertyReactive
public class Candidate implements java.io.Serializable
{
   private static final long serialVersionUID = 1L;
   private Long id;

   private String firstName;
   private String lastName;
   private String sex;
   private String schoolType;
   private Double examResult;
   private List<ExamSubjectResult> examSubjectResult = new ArrayList<>();
   private Boolean admission = Boolean.FALSE;
   private String fieldOfStudy;
   private Boolean olympicFinalist;
   private String qualificationType = "None";  
   private String logger = "";   
   private Integer counter = 0;
   
public Candidate()
   {
   }

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getFirstName()
   {
      return this.firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return this.lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public Double getExamResult()
   {
      return this.examResult;
   }

   public void setExamResult(Double examResult)
   {
      this.examResult = examResult;
   }

   public List<ExamSubjectResult> getExamSubjectResult() {
      return new ArrayList<>(examSubjectResult);
   }
   public void setExamSubjectResult(List<ExamSubjectResult> examSubjectResult) {
      this.examSubjectResult = examSubjectResult;
   }

   public Boolean getAdmission()
   {
      return this.admission;
   }

   public void setAdmission(Boolean admission)
   {
      this.admission = admission;
   }
   
   public String getFieldOfStudy()
   {
      return this.fieldOfStudy;
   }

   public void setFieldOfStudy(String fieldOfStudy)
   {
      this.fieldOfStudy = fieldOfStudy;
   }

   public Boolean getOlympicFinalist()
   {
      return this.olympicFinalist;
   }

   public void setOlympicFinalist(Boolean olympicFinalist)
   {
      this.olympicFinalist = olympicFinalist;
   }

   public String getQualificationType()
   {
      return this.qualificationType;
   }

   public void setQualificationType(String qualificationType)
   {
      this.qualificationType = qualificationType;
   }
   
   public String getLogger()
   {
      return this.logger;
   }

   public void setLogger(String logger)
   {
      this.logger = logger;
   }

   public void appendLogger(String logger) {
      if (logger != null && !logger.isEmpty()) {
         this.logger += "\n" + logger;
      }
   }

   public Integer getCounter() {
	   return counter;
   }

   public void setCounter(Integer counter) {
	   this.counter = counter;
   }
   
   public void incrementCounter() {
	   this.counter++;
   }
   
   public String getSex() {
		return sex;
   }

   public void setSex(String sex) {
		this.sex = sex;
	}

   public String getSchoolType() { return schoolType; }

   public void setSchoolType(String schoolType) { this.schoolType = schoolType; }

   public String getCandidateInformation()
   {
      return  "First & last name: " + this.firstName + " " + this.lastName +
              "\nField of study: " + this.fieldOfStudy + " (" + this.examResult + " points, OlympicFinalist: " + this.olympicFinalist + ", SchoolType: " + this.schoolType + ")" +
              "\n⮕ Admission: " + this.admission + ". Qualification type: " + this.qualificationType +
              "\nObject reference: " + Integer.toHexString(System.identityHashCode(this)) + "\n" + this.logger;
   }

   public String getCandidateInformationLogger()
   {
      String reasoningLogger = (this.logger.isEmpty()) ? "" : "\n⭍ RULES FIRED WITH EXECUTION ORDER:" + this.logger;

      return  "⭍ CANDIDATE:" +
              "\nFirst & last name: " + this.firstName + " " + this.lastName +
              "\nField of study: " + this.fieldOfStudy + " (" + this.examResult + " points, OlympicFinalist: " + this.olympicFinalist + ", SchoolType: " + this.schoolType + ")" +
              "\nAdmission: " + this.admission +
              "\nQualification type: " + this.qualificationType +
              "\nObject reference: " + Integer.toHexString(System.identityHashCode(this)) +
              reasoningLogger;
   }

   public String getCandidateSubjectsInformation()
   {
       StringBuilder information = new StringBuilder("CANDIDATE'S SUBJECTS:\n");

       for (ExamSubjectResult esr : this.examSubjectResult) {
           information.append("— ").append(esr.getExamSubject()).append(" : ").append(esr.getExamLevel()).append(" : ").append(esr.getExamResult()).append("\n");
       }
       return information.toString();
   }

   public Candidate(Long id, String firstName,
                    String lastName, Double examResult,
                    Boolean admission, String fieldOfStudy,
                    Boolean olympicFinalist, String gender, String schoolType)
	   {
	      this.id = id;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.examResult = examResult;
	      this.admission = admission;
	      this.fieldOfStudy = fieldOfStudy;
	      this.olympicFinalist = olympicFinalist;
	      this.sex = gender;
          this.schoolType = schoolType;
	   }
   
   public Candidate(Long id, String firstName,
                    String lastName, List<ExamSubjectResult> examSubjectResult,
                    Boolean admission, String fieldOfStudy,
                    Boolean olympicFinalist, String gender, String schoolType)
	   {
	      this.id = id;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.examResult = 0.0D;
	      this.examSubjectResult = examSubjectResult;
	      this.admission = admission;
	      this.fieldOfStudy = fieldOfStudy;
	      this.olympicFinalist = olympicFinalist;
	      this.sex = gender;
          this.schoolType = schoolType;
	   }
}