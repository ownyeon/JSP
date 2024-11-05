package reply_ex.service;

import reply_ex.model.*;

public class InsertReplyService {
	
	private static InsertReplyService instance;
	
	public static InsertReplyService getInstance()  throws ReplyException{
		
		if( instance == null )
		{
			instance = new InsertReplyService();
		}
		return instance;
		
	}

	public int insert( ReplyVO vo ) throws ReplyException{
		
		ReplyDao dao = ReplyDao.getInstance(); 
	
		return dao.insert(vo); 
		
	}
	
	
}
