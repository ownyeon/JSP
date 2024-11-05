package guest.service;

import guest.model.Message;
import guest.model.MessageDao;
import guest.model.MessageException;

import java.util.List;

public class ListMessageService {

	//-------------------------------------------------------------------
	private int totalRecCount;		// 전체 레코드 수	
	private int pageTotalCount;		// 전체 페이지 수
	private int countPerPage = 3;	// 한페이지당 레코드 수 (3개씩만 보이게 할 것임)
	
	private static ListMessageService instance;
	
	public static ListMessageService	getInstance() throws MessageException
	{
		if( instance == null )
		{
			instance = new ListMessageService();
		}
		return instance;
	}
	
	private ListMessageService()
	{
		
	}
	
	public List <Message> getMessageList(String pNum) throws MessageException
	{
	
		
		//1. 맨 처음에는 페이지 번호가 url에 없음. 
		//2. 이때 pNum은 null이 떨어짐
		//3. null이 아닌 경우에만 형변환을 시켜줄 거임. 
		
		int pageNum =1;
		if(pNum != null) {
			pageNum = Integer.parseInt(pNum);
		}
		
		/*
		 * 페이지번호 시작레코드번호	끝레코드번호
		 * 	1 		1				3
		 *  2 		4				6
		 *  3		7				9
		 */
		
		int startRow = (pageNum-1) * countPerPage + 1;
		int endRow = pageNum * countPerPage;
		
//		System.out.println("[startrow]" + startRow);
//		System.out.println("[endRow]" + endRow);
		// 전체 레코드를 검색해 온다면
		List <Message> mList = MessageDao.getInstance().selectList(startRow,endRow);			
		
		
		return mList;
	}
	
	//전체 메세지 레코드(DB) 수를 얻어와서 전체 페이지(화면)를 넘겨주는 함수 
	public int getTotalPage() throws MessageException{
		
		totalRecCount = MessageDao.getInstance().getTotalCount();
		//totalRecCount는 db에서 받아가지고 옴.
		
		
		/*규칙: 3개씩 돌고 넘어가면 pageTotalCount+1 
		 * 
		 * countPerPage = 3; 
		 * 
		 *  totalRecCount     pageTotalCount
		 * 		6					2
		 * 		7					3
		 * 		8					3
		 * 		9					3
		 * 		10					4
		 */
		
		//2 = 6 / 3 => 전체 2page
		//3 = 10/3 > pageTotalCount => 페이지수++ , 4페이지
		pageTotalCount = totalRecCount / countPerPage;
		if(totalRecCount % countPerPage > 0) {
			pageTotalCount++; //나머지가 0보다 크면 ++, 만약에 6,9가 나오면 나누어 떨어지니까 count를 해주지 않아도 됨.
		}
		//축약형 pageTotalCount = (int)(Math.ceil( totalRecCount / (double) countPerPage);
		
		return pageTotalCount; //전체 레코드 수 리턴
		
	}
	
	
}
