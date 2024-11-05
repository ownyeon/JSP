package reply_ex.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ReplyDao {
	
	private static ReplyDao instance;

	// DB 연결시  관한 변수 
	// [ 오라클 ]
	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String		dbUser		=	"scott";
	private static final String		dbPass		=	"tiger";
	
	private Connection	 		con;	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static ReplyDao	getInstance() throws ReplyException
	{
		if( instance == null )
		{
			instance = new ReplyDao();
		}
		return instance;
	}
	
	private ReplyDao() throws ReplyException
	{
	
		try{
			
			/********************************************
			1. 오라클 드라이버를 로딩
				( DBCP 연결하면 삭제할 부분 )
		*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new ReplyException("DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	
	
	/************************************************
	 * 함수명 : insert
	 * 역할 :	게시판에 댓글 입력시 DB에 저장하는 메소드 
	 * 인자 :	ReplyVO
	 * 리턴값 : 입력한 행수를 받아서 리턴
	*/
	
	public int insert(ReplyVO vo) throws ReplyException{
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
	 
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			//* sql 문장 만들기
			//	작성자,제목,글내용,비밀번호는 입력값으로 날짜는 오늘날짜, 조회수는 0으로 기본값으로 입력
			String putQuery		= "INSERT INTO reply(reply_id, seq, username, reply)"
					+ " VALUES(seq_reply_id.NEXTVAL,?,?,?)";  

			ps		= con.prepareStatement( putQuery );
			//* sql 문장의 ? 지정하기
			 ps.setInt(1, vo.getSeq()); 
			 ps.setString(2, vo.getUsername());
			 ps.setString(3, vo.getReply()); 
			
			ps.executeUpdate();			
			
			return 0;
		
		
		}catch( Exception ex ){
			throw new ReplyException("게시판 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
