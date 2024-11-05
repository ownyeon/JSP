package info.beans;

public class InfoBean {

	// 화면 폼의 요소의 name값과 동일하게 변수명 지정하기 
	private String name;
	private String id;
	
	public String getGender() {
		String sex = "";
		char sexNum = id.charAt(7);
		if(sexNum == '1' | sexNum == '3' | sexNum == '5' ) {
			sex = "남성";
		}else {
			sex = "여성";
		}
		return sex; 
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
