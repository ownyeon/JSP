package board_ex.service;

import java.util.List;

import board_ex.model.*;


public class ViewArticleService {
	private static ViewArticleService instance;
	public static ViewArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ViewArticleService();
		}
		return instance;
	}
	
	public BoardVO getArticleById(String id) throws BoardException
	{
		int article_id = 0;
		if( id != null ) article_id = Integer.parseInt(id); //글번호를 넘겨받음. 
		
		BoardDao dao = BoardDao.getInstance();
		
		//1.selectById로 seq를 얻어옴 
		BoardVO rec = dao.selectById(article_id);		
		dao.increaseReadCount(article_id);
		return rec;
	}
		
}

