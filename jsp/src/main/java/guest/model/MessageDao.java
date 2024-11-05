package guest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDao {

	// Single Pattern 
	private static MessageDao instance;
	
	// DB 연결시  관한 변수 
	// [ 오라클 ]
	private static final String 	dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String		dbUser		=	"scott";
	private static final String		dbPass		=	"tiger";
	
	// [ MySQL / MariaDB ]
	/*
	 * private static final String dbDriver = "com.mysql.cj.jdbc.Driver"; private
	 * static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/basic"; private
	 * static final String dbUser = "scott"; private static final String dbPass =
	 * "tiger";
	 */
	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static MessageDao	getInstance() throws MessageException
	{
		if( instance == null )
		{
			instance = new MessageDao();
		}
		return instance;
	}
	
	private MessageDao() throws MessageException
	{
	
		try{
			
			/********************************************
				1. 오라클 드라이버를 로딩					
			*/
			Class.forName(dbDriver);

		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	
	/*
	 * 메세지를 입력하는 함수
	 */
	public void insert(Message rec) throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		try{

			// 1. 연결객체(Connection) 얻어오기
			// 2. sql 문장 만들기
			// 3. 전송객체 얻어오기
			// 4. 전송하기
	
			//[2] SQL 문장 (****)		
			String sql = "INSERT INTO GUESTTB(MESSAGE_ID, GUEST_NAME, PASSWORD, MESSAGE)  "
					+ " VALUES(seq_guestTb_messageId.NEXTVAL,?,?,?) ";
			System.out.println("[SQL]" + sql);
			
			//[3] 연결객체 얻어오기
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			
			//[4] 전송객체 얻어오기
			/*
			 * 	- Statement 		: 완벽한 SQL 문장 
			 * 	- PreparedStatement : 미완성한 SQL 문장 
			 * 	- CallableStatement : PL-SQL 함수(function/procedure) 호출 
			 */
			ps = con.prepareStatement(sql);
			//미완성 부분을 채우기
			
			  ps.setString(1, rec.getGuestName()); // 첫번째 값에 사번 넣기
			  ps.setString(2, rec.getPassword());
			  ps.setString(3, rec.getMessage()); 
	
			
			//[5] 전송
			int result = ps.executeUpdate(); // **** sql 전송 안함 
			System.out.println(result + "행을 입력"); //executeUpdate는 int값을 리턴해줌. 

			
		
				
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
	
	}

	
	/*
	 * 메세지 목록 전체를 얻어올 때
	 */
	public List<Message> selectList() throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();
		boolean isEmpty = true;
		
		try{

			/*최신버전이 가장 먼저 올라오도록 목록을 가지고 옴*/
			String sql = "SELECT * FROM guesttb ORDER BY message_id DESC";
			System.out.println(sql);
			
			//연결객체 얻어오기
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			
			//전송객체 얻어오기 
			ps = con.prepareStatement(sql);
			//한사람이 글쓴 방명록을 MESSAGE에 담을 거고, 그 ARRAYLIST를 반환 
	
			
			//5 전송
			rs= ps.executeQuery();//rs는 resultSet인거임.
			
			while(rs.next()) {
				isEmpty = false;
				Message m = new Message();
				m.setId(rs.getInt("MESSAGE_ID")); //컬럼명 지정 
				m.setGuestName(rs.getString("GUEST_NAME"));
				m.setMessage(rs.getString("MESSAGE")); //resultset에서 가지고 올 거임 
				
				mList.add(m);
			
			}
			//만약에 비어있으면 빈 LIST를 반환해줌 
			if( isEmpty ) return Collections.emptyList();
			
			
			return mList;
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	

	/* -------------------------------------------------------
	 * 현재 페이지에 보여울 메세지 목록  얻어올 때
	 */
	public List<Message> selectList(int firstRow, int endRow) throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();
		boolean isEmpty = true;
		
		try{
			
			String sql = "SELECT * "
					+ "FROM GUESTTB  "
					+ "WHERE message_id in( "
					+ "			  SELECT message_id "
					+ "			 FROM (  "
					+ "			 		SELECT  rownum AS rnum, message_id "
					+ "			 		 FROM (    "
					+ "			 		 		SELECT rownum, message_id  "
					+ "			 		 		FROM GUESTTB  "
					+ "			 		 		ORDER BY  message_id desc) "
					+ "			 		 		) "
					+ "			 WHERE rnum >=? AND rnum <=? "
					+ "			) "
					+ "ORDER BY message_id DESC";
			System.out.println(sql);
			
			//연결객체 얻어오기
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			
			//전송객체 얻어오기 
			ps = con.prepareStatement(sql);
			
			ps.setInt(1,firstRow);
			ps.setInt(2,endRow);
			
			// 전송
			rs= ps.executeQuery();//rs는 resultSet인거임.
			
			while(rs.next()) {
				isEmpty = false;
				Message m = new Message();
				m.setId(rs.getInt("MESSAGE_ID")); //컬럼명 지정 
				m.setGuestName(rs.getString("GUEST_NAME"));
				m.setMessage(rs.getString("MESSAGE")); //resultset에서 가지고 올 거임 
				
				mList.add(m);
			
			}

			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	
	/* -------------------------------------------------------
	 * 메세지 전체 레코드 수를 검색
	 */
	
	public int getTotalCount() throws MessageException{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try{
			
			String sql = "SELECT COUNT(*) CNT FROM GUESTTB";
			
			
			con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			
			//전송객체 얻어오기 
			ps = con.prepareStatement(sql);
	
			//5 전송
			rs= ps.executeQuery();//rs는 resultSet인거임.
			
			rs.next(); //반드시 한 칸 더 내려와야 함.그렇게 해야 첫번째 레코드 값을 읽으니까ㅏ.
			count = rs.getInt("CNT"); 
			System.out.println(count);
			

			return  count;
			
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}			
	}
	
	
	/*
	 * 메세지 번호와 비밀번호에 의해 메세지 삭제
	 */
	public int delete( int messageId, String password ) throws MessageException
	{
		int result = 0;
		Connection	 		con = null;
		PreparedStatement ps = null;
		try{
			
		String sqlDeleteGUESTTB = "DELETE FROM GUESTTB WHERE message_id = ? and password = ?";
			
		con = DriverManager.getConnection(dbUrl,dbUser,dbPass);

		ps = con.prepareStatement(sqlDeleteGUESTTB);
		ps.setInt(1,messageId);
		ps.setString(2, password);
		
		result = ps.executeUpdate(); //레코드 삭제 실행
		
		
			
			return result;
			
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 삭제시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
}
