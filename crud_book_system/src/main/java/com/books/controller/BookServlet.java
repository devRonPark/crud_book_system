package com.books.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location="C:\\Users\\WD\\Desktop\\project\\thumbnail")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, BookController> controllerMap = new HashMap<>();
       
    public BookServlet() {
        controllerMap.put("/books/list", new ListController());
        controllerMap.put("/books/add", new AddController());
        controllerMap.put("/books/edit", new EditController());
        controllerMap.put("/books/delete", new DeleteController());
        controllerMap.put("/books/view", new ViewController());
        controllerMap.put("/books/search", new SearchController());
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 요청 인코딩을 UTF-8로 설정합니다.
        req.setCharacterEncoding("utf-8");
        
        // 요청 URI를 가져온다.
		String requestURI = req.getRequestURI();
		
		// 요청 URI에 매핑된 컨트롤러를 가져온다.
		BookController controller = controllerMap.get(requestURI);
		
		// 요청 URI와 매핑된 컨트롤러가 없다면, 404 상태 코드를 설정.
		if (controller == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// 매핑된 컨트롤러의 process 메서드를 호출하여 요청을 처리.
		try {
			controller.process(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
