package guest.model;

public class Message {

	private int id; // 메세지 번호 
	//insertMessage.jsp에 있는 name과 동일해야 함. 
	private String guestName; // 사용자명
	private String password; //비밀번호 
	private String message;  //메세지
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", guestName=" + guestName + ", password=" + password + ", message=" + message
				+ "]";
	}

	
	
	
	
}
