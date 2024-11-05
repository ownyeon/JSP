package board_ex.service;

import java.util.List;

import board_ex.model.*;

public class ListArticleService {
	private static ListArticleService instance;
	
	public static ListArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ListArticleService(); //생성자 함수 private로 만들어주기.
		}
		return instance;
	}
	
	public List <BoardVO> getArticleList() throws BoardException
	{
		 List <BoardVO> mList = BoardDao.getInstance().selectList(); //2. 서비스가 호출되면 selectList 함수를 호출 			
		return mList;
	}
		
}
