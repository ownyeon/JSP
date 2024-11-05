package member.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

	
	// DB 연결시  관한 변수 

	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String		dbUser		=	"scott";
	private static final String		dbPass		=	"tiger";

	//싱글톤패턴
	private static MemberDao memberDao;
	
	public static MemberDao getInstance() throws MemberException
	{
		if( memberDao == null )
		{
			memberDao =  new MemberDao();
		}
		return memberDao;
	}
	

	private MemberDao() throws MemberException
	{
			
		try{
			
			/********************************************
				1. 드라이버를 로딩
							
			*/
			Class.forName(dbDriver);
			
			
		}catch( Exception ex ){
			throw new MemberException("DB 연결시 오류  : " + ex.toString() );	
		}
	}
	
	/*******************************************
	 * * 회원관리테이블 MEMBERTEST 에  회원정보를 입력하는 함수
	 * @param rec
	 * @throws MemberException
	 */
	public void insertMember( Member rec ) throws MemberException
	{
		try {
			 // 0. 연결 객체 얻어오기	
			 // 1. sql 문장 만들기 ( insert문 )
			 // 2. sql 전송 객체 만들기
			 // 3. sql 전송
			 // 4. 객체 닫기
			//[2] SQL 문장 (****)
			// 사용자 입력값을 받는다고 가정 		
			String sql = "INSERT INTO REVIEW_TAB(info_id, info_pw, info_name, info_tel, info_addr)  "
					+ " VALUES(?,?,?,?,?) ";
			System.out.println("[SQL]" + sql);
			
			//[3] 연결객체 얻어오기
			Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			
			//[4] 전송객체 얻어오기
			/*
			 * 	- Statement 		: 완벽한 SQL 문장 
			 * 	- PreparedStatement : 미완성한 SQL 문장 
			 * 	- CallableStatement : PL-SQL 함수(function/procedure) 호출 
			 */
			PreparedStatement ps = con.prepareStatement(sql);
			//미완성 부분을 채우기
			
			  ps.setString(1, rec.getId()); // 첫번째 값에 사번 넣기
			  ps.setString(2, rec.getPassword());
			  ps.setString(3, rec.getName()); 
			  ps.setString(4, rec.getTel());
			  ps.setString(5,rec.getAddr());
			 
			
			//[5] 전송
			int result = ps.executeUpdate(); // **** sql 전송 안함 
			System.out.println(result + "행을 입력"); //executeUpdate는 int값을 리턴해줌. 
			
			//[6] 닫기 
			ps.close();
			con.close();
			 
		} catch ( Exception ex ){
			throw new MemberException("멤버 입력시 오류  : " + ex.toString() );	//일부러 예외를 만듦. 	
		}			
	}
	
	/**********************************************************
	 * * 회원관리테이블 MEMBERTEST에서 기존의 id값과 중복되는지 확인하는 함수
	 * 중복을 확인 하기 위해서 boolean과 return을 사용. 
	 * return을 사용해서 중복된 id가 있는지 확인 
	 */
	public boolean isDuplicatedId( String id ) throws MemberException
	{
		boolean flag = false;
		/* Member vo = new Member(); */
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try{
			//해당하는 id가 있는지 없는지 select문으로 확인하는 거임
			String sql = "SELECT INFO_ID FROM REVIEW_TAB WHERE INFO_ID = ? ";
			
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);

			//4
			ps = con.prepareStatement(sql);
			//미완성 부분을 채우기
			ps.setString(1,id); // 물음표를 넘겨받음


			//5 전송
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) { 
			    flag = true;  // 중복된 ID가 있는 경우
			}
			
			ps.close();
			con.close();
			
		}catch( Exception ex ){
			throw new MemberException("중복아이디 검사시 오류  : " + ex.toString() );			
		}
			
		return flag;
	}
}
