package board_ex.model;



import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDao
{
	
	// Single Pattern 
	private static BoardDao instance;
	
	// DB 연결시  관한 변수 
	// [ 오라클 ]
	private static final String 		dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String		dbUser		=	"scott";
	private static final String		dbPass		=	"tiger";
	
	// [ MySQL / MariaDB ]
//	private static final String 	dbDriver	=	"com.mysql.cj.jdbc.Driver";
//	private static final String		dbUrl		=	"jdbc:mysql://127.0.0.1:3306/basic";
//	private static final String		dbUser		=	"scott";
//	private static final String		dbPass		=	"tiger";
//	
	
	private Connection	 		con;	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static BoardDao	getInstance() throws BoardException
	{
		if( instance == null )
		{
			instance = new BoardDao();
		}
		return instance;
	}
	
	private BoardDao() throws BoardException
	{
	
		try{
			
			/********************************************
			1. 오라클 드라이버를 로딩
				( DBCP 연결하면 삭제할 부분 )
		*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new BoardException("DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	/************************************************
	 * 함수명 : insert
	 * 역할 :	게시판에 글을 입력시 DB에 저장하는 메소드 
	 * 인자 :	BoardVO
	 * 리턴값 : 입력한 행수를 받아서 리턴
	*/
	public int insert( BoardVO rec ) throws BoardException
	{

		ResultSet rs = null;
		Statement stmt	= null;
		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			//* sql 문장 만들기
			//	작성자,제목,글내용,비밀번호는 입력값으로 날짜는 오늘날짜, 조회수는 0으로 기본값으로 입력
			String putQuery		= "INSERT INTO board(seq, title, writer, content, pass,regdate,cnt)"
					+ " VALUES(board_seq.NEXTVAL,?,?,?,?,sysdate,0)";  

			ps		= con.prepareStatement( putQuery );
			//* sql 문장의 ? 지정하기
			 ps.setString(1, rec.getTitle()); 
			 ps.setString(2, rec.getWriter());
			 ps.setString(3, rec.getContent()); 
			 ps.setString(4, rec.getPass());
			
			ps.executeUpdate();			

			//입력된 글번호를 해당 시퀀스에서 얻어오기
			String getSeqNum = "SELECT board_seq.currval AS curr_seq FROM dual";
			
			PreparedStatement ps2		= con.prepareStatement( getSeqNum );
			rs = ps2.executeQuery();
			
			rs.next();//무조건 처음에는 next()해줘야지 값을 읽어옴
			//목적: 입력할때 seq를 해서 값을 올려줬는데, 입력했을 때 몇 번 값으로 들어갔는지 알수 없으니 받아온거임. 
			return rs.getInt("curr_seq");
		
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( stmt != null ) { try{ stmt.close(); } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}


	/************************************************
	 * 함수명 : selectList
	 * 역할 :	전체 레코드를 검색하는 함수
	 * 인자 :	없음
	 * 리턴값 : 테이블의 한 레코드를 BoardVO 지정하고 그것을 ArrayList에 추가한 값
	*/

	public List<BoardVO> selectList() throws BoardException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardVO> mList = new ArrayList<BoardVO>();
		boolean isEmpty = true;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			// * sql 문장만들기
			//		( 글번호, 제목, 작성자, 조회수, 작성일을 검색 )
			String sql = "SELECT seq, title, writer,cnt, regdate FROM BOARD";
			
			// * 전송객체 얻어오기
			ps = con.prepareStatement(sql);

			// * 전송하기
			rs= ps.executeQuery();//rs는 resultSet인거임.
			// * 결과 받아 List<BoardVO> 변수 mList에 지정하기
			while(rs.next()) {
				isEmpty = false;
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("SEQ"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setWriter(rs.getString("WRITER"));
				vo.setCnt(rs.getInt("CNT"));
				vo.setRegdate(rs.getString("regdate"));
				
				mList.add(vo);
			}
			
			
			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	//--------------------------------------------
	//#####	 게시글번호에 의한 레코드 검색하는 함수
	public BoardVO selectById(int seq) throws BoardException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		BoardVO rec = new BoardVO();
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			// * sql 문장만들기
			// * 전송객체 얻어오기
			// * 전송하기
			// * 결과 받아 BoardVO변수 rec에 지정하기
			String sql = "SELECT * FROM BOARD where seq=?";
			
			// * 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, seq);
			// * 전송하기
			rs= ps.executeQuery();//rs는 resultSet인거임.
			// * 결과 받아 List<BoardVO> 변수 mList에 지정하기
			while(rs.next()) {
			
				rec.setSeq(rs.getInt("SEQ"));
				rec.setTitle(rs.getString("TITLE"));
				rec.setWriter(rs.getString("WRITER"));
				rec.setCnt(rs.getInt("CNT"));
				rec.setRegdate(rs.getString("regdate"));
				rec.setContent(rs.getString("CONTENT"));

			}

			
			return rec;
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 글번호에 의한 레코드 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}

	
	//--------------------------------------------
	//#####	 게시글 보여줄 때 조회수 1 증가
	public void increaseReadCount( int seq ) throws BoardException
	{

		PreparedStatement ps = null;
		ResultSet rs = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			// * sql 문장만들기
			String sql = "UPDATE BOARD SET CNT=CNT+1 where seq=?";
			
			// * 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, seq);
			
			// * 전송하기
			ps.executeUpdate();
		
			
			
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 볼 때 조회수 증가시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	//--------------------------------------------
	//#####	 게시글 수정할 때
	public int update( BoardVO rec ) throws BoardException
	{

		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			// * sql 문장만들기
			// * 전송객체 얻어오기

			String putQuery		= "update board set title = ?, content=? where seq=? and pass = ?";  

			ps		= con.prepareStatement( putQuery );
			//* sql 문장의 ? 지정하기
			 ps.setString(1, rec.getTitle()); 
			 ps.setString(2, rec.getContent()); 
			 ps.setInt(3, rec.getSeq());
			 ps.setString(4, rec.getPass());
			
			 System.out.println(ps.executeUpdate());
			return ps.executeUpdate();
		
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	
	
	//--------------------------------------------
	//#####	 게시글 삭제할 때
	public int delete( int seq, String pass ) throws BoardException
	{

		Connection	 		con = null;
		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
		
			// * sql 문장만들기
			String sql = "DELETE FROM board WHERE seq = ? and pass = ?";
			
		
			// * 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setInt(1,seq);
			ps.setString(2, pass);	
			
			return ps.executeUpdate();
			
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	
	
}