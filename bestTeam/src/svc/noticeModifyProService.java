package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.noticeDAO;
import vo.noticeBean;

public class noticeModifyProService {
	
//	// 게시물 작성자 일치 여부 확인을 위해 게시물 번호와 패스워드를 전달받아 확인 작업 수행
//	public boolean isArticleWriter(int notice_num, String pass) throws Exception {
//		System.out.println("noticeModifyProService - isArticleWriter()");
//		boolean isArticleWriter = false;
//		
//		// Connection 객체 가져오기
//		Connection con = getConnection();
//		
//		// noticeDAO 인스턴스 얻어오기 => setConnection() 메서드를 호출하여 Connection 객체 전달
//		noticeDAO NoticeDAO = noticeDAO.getInstance();
//		NoticeDAO.setConnection(con);
//
//		isArticleWriter = NoticeDAO.isArticlenoticeWriter(notice_num, pass); // 본인 확인(패스워드 일치여부 판별)
//		    
//		close(con);
//		
//		return isArticleWriter;
//	}

	public boolean modifyArticle(noticeBean article) {
		boolean isModifySuccess = false;
		
		// DB 작업 전 DB 접속을 위해 JdbcUtil 클래스의 static 메서드 getConnection() 를 호출하여 DB 접속
		Connection con = getConnection();
		
		// 싱글톤 디자인 패턴으로 생성된 noticeDAO 인스턴스를 얻어오기
		noticeDAO NoticeDAO = noticeDAO.getInstance();
		NoticeDAO.setConnection(con); // Connection 객체를 noticeDAO 객체에 전달
		int updateCount = NoticeDAO.updateArticle(article); // 글 수정 처리(결과를 int형으로 전달받음)
		
		// insertCount 가 0보다 크면 트랜잭션 Commit, 아니면 트랜잭션 Rollback 수행
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true; // 성공 표시
		} else {
			rollback(con);
		}
		
		// DB 접속 해제(Connection 자원 반환)
		close(con);
		
		return isModifySuccess;
	}

	public boolean isArticleWriter(int notice_num, String parameter) {
		// TODO Auto-generated method stub
		return false;
	}
	
}






















