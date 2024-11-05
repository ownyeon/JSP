package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpDAO {

	// 0. 필요한 변수 선언 
	static String driver	= "oracle.jdbc.driver.OracleDriver";
	static String url		= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	static String user		= "scott";
	static String pass		= "tiger";

	//Singleton pattern = 객체를 한 번!! 메모리에 올려놓고, 그것만 사용하도록 하는것. 
	//생성자 함수를 private으로 설정해서 객체를 생성하지 못하게 함. 
	private EmpDAO() throws Exception{
		// [1] 드라이버를 메모리 로딩
		Class.forName(driver);

	}
	
	//접근 못하게 함. 
	private static EmpDAO instance = null; 
	
	//얘를 통해서 instance를 return 시켜줘야 함. 
	public static EmpDAO getInstance() throws Exception{
		if(instance == null) {
			//객체 생성
			instance = new EmpDAO();
		}
		return instance;
	}
	
	//로그인 확인 
	public boolean loginCheck(EmpVO vo) throws Exception{
		boolean result = false; 
		Connection con = null;
		PreparedStatement ps = null;
	
		//해당하는 사번(비밀번호)과 사원명(아이디)이 동일한 사원이 있으면 result = true; 
		String sql = "select * from emp"
				+ " where empno=? and ename=?";
		
	    // 3. 연결 객체 얻어오기
        con = DriverManager.getConnection(url, user, pass);
        
        // 4. PreparedStatement 생성
        ps = con.prepareStatement(sql);
        //물음표 세팅 
		ps.setInt(1, vo.getEmpno()); // 첫번째 값에 사번 넣기
		ps.setString(2, vo.getEname());
        
        // 5. 전송 
        ResultSet rs = ps.executeQuery();
        
        //값이 있는지 없는지 궁금한 거임
        if(rs.next()) {
        	result = true;
        }
        
        //닫기 
        rs.close();
        ps.close();
		con.close();
        
		return result;
	}
	
	
	
	//사용자 입력값을 디비에 입력
	public void insertEmp(EmpVO vo) throws Exception{
	
		
		//[2] SQL 문장 (****)
		// 사용자 입력값을 받는다고 가정 		
		String sql = "INSERT INTO emp(empno, ename, deptno, job, sal)  "
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
		ps.setInt(1, vo.getEmpno()); // 첫번째 값에 사번 넣기
		ps.setString(2, vo.getEname());
		ps.setInt(3, vo.getDeptno());
		ps.setString(4, vo.getJob());
		ps.setInt(5, vo.getSal());
		
		//[5] 전송
		int result = ps.executeUpdate(); // **** sql 전송 안함 
		System.out.println(result + "행을 입력"); //executeUpdate는 int값을 리턴해줌. 
		
		//[6] 닫기 
		ps.close();
		con.close();
		
	}
	

	public ArrayList<EmpVO> selectEmp()  throws Exception{
		// [2] SQL 문장 (*****)			
		String sql = "SELECT empno, ename, job, mgr, hiredate "
				+ "FROM emp";
		
		// [3] 연결객체 얻어오기
		Connection con = DriverManager.getConnection(url, user, pass);

		// [4] 전송객체 얻어오기		
		PreparedStatement ps = con.prepareStatement(sql);
		
		// [5] 전송		
		// 여러 집합을 리절트셋으로 받아줘야함. (여러 집합들을)
		ResultSet rs = ps.executeQuery();
		//한사람에 대한 정보를 빼서 리스트에 담고 있어야 함. = ArrayList
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		
		while(rs.next()) {
			//한사람에 대한 정보를 empno에 담을 것임
			EmpVO vo = new EmpVO();
			vo.setEmpno(rs.getInt("EMPNO"));
			vo.setEname(rs.getString("ENAME"));
			vo.setJob(rs.getString("JOB"));
			vo.setMgr(rs.getInt("MGR"));
			vo.setHiredate(rs.getString("HIREDATE"));
			list.add(vo); //리스트에 담아주기. 
			
		}
		
		// [6] 닫기
		ps.close();
		rs.close();
		con.close();
		
		//나를 부른 곳으로 넘겨주자. 
		return list;
	}



}
