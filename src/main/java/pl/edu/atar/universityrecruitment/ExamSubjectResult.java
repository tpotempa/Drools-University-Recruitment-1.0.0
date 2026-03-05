package pl.edu.atar.universityrecruitment;

public class ExamSubjectResult {
	private String examSubject;
	private String examLevel;
	private Double examResult;

	public ExamSubjectResult() {
	}

	public ExamSubjectResult(String examSubject, String examLevel, Double examResult) {
		this.examSubject = examSubject;
		this.examLevel = examLevel;
		this.examResult = examResult;
	}

	public String getExamSubject() {
		return examSubject;
	}

	public void setExamSubject(String examSubject) {
		this.examSubject = examSubject;
	}

	public String getExamLevel() {
		return examLevel;
	}

	public void setExamLevel(String examLevel) {
		this.examLevel = examLevel;
	}

	public Double getExamResult() {
		return examResult;
	}

	public void setExamResult(Double examResult) {
		this.examResult = examResult;
	}
}