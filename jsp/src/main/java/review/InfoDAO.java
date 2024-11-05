package review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InfoDAO {

	// 0. 필요한 변수 선언 
	static String driver	= "oracle.jdbc.driver.OracleDriver";
	static String url		= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	static String user		= "scott";
	static String pass		= "tiger";

	//Singleton pattern = 객체를 한 번!! 메모리에 올려놓고, 그것만 사용하도록 하는것. 
	//생성자 함수를 private으로 설정해서 객체를 생성하지 못하게 함. 
	private InfoDAO() throws Exception{
		// [1] 드라이버를 메모리 로딩
		Class.forName(driver);

	}
	
	//접근 못하게 함. 
	private static InfoDAO instance = null; 
	
	//얘를 통해서 instance를 return 시켜줘야 함. 
	public static InfoDAO getInstance() throws Exception{
		if(instance == null) {
			//객체 생성
			instance = new InfoDAO();
		}
		return instance;
	}
	
	
	//사용자 입력값을 디비에 입력
	public void insertInfo(InfoVO vo) throws Exception{
	
		
		//[2] SQL 문장 (****)
		// 사용자 입력값을 받는다고 가정 		
		String sql = "INSERT INTO REVIEW_TAB(info_id, info_pw, info_name, info_tel, info_addr)  "
				+ " VALUES(?,?,?,?,?) ";
		System.out.println("[SQL]" + sql);
		
		//[3] 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url,user,pass);
		
		//[4] 전송객체 얻어오기
		/*
		 * 	- Statement 		: 완벽한 SQL 문장 
		 * 	- PreparedStatement : 미완성한 SQL 문장 
		 * 	- CallableStatement : PL-SQL 함수(function/procedure) 호출 
		 */
		PreparedStatement ps = con.prepareStatement(sql);
		//미완성 부분을 채우기
		
		  ps.setString(1, vo.getInfo_id()); // 첫번째 값에 사번 넣기
		  ps.setString(2, vo.getInfo_pw());
		  ps.setString(3, vo.getInfo_name()); 
		  ps.setString(4, vo.getInfo_tel());
		  ps.setString(5,vo.getInfo_addr());
		 
		
		//[5] 전송
		int result = ps.executeUpdate(); // **** sql 전송 안함 
		System.out.println(result + "행을 입력"); //executeUpdate는 int값을 리턴해줌. 
		
		//[6] 닫기 
		ps.close();
		con.close();
		
	}
	




}
