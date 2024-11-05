package reply_ex.model;

public class ReplyVO {

	//데이터베이스 컬럼명과 동일하게. 
	private int reply_id;
	private int seq; 
	private String username;
	private String reply;
	
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	@Override
	public String toString() {
		return "ReplyVO [reply_id=" + reply_id + ", seq=" + seq + ", username=" + username + ", reply=" + reply + "]";
	}
	
	
	
	
	
	
}
