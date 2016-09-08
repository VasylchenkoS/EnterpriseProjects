package com.vasylchenko.java.servlets;

import com.vasylchenko.java.bean.TodoItemBean;
import com.vasylchenko.java.model.TodoItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "Todo", urlPatterns = "/todoServlet")
public class TodoServlet extends HttpServlet {

	private List<TodoItem> todoItemList = new TodoItemBean().getItemList();


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setStatus(200);
		response.setContentType("text/plain");
		Enumeration<String> checkButton = request.getParameterNames();

		while (checkButton.hasMoreElements()) {
			String currentButton = checkButton.nextElement();
			if (Objects.equals(currentButton, "updateButton"))
				updateList(request);
			else if (Objects.equals(currentButton, "addTask"))
				addTask(request);
			else if (Objects.equals(currentButton, "deleteTask"))
				deleteTask();
		}
		List<TodoItem> items = todoItemList;
		HttpSession session = request.getSession();
		session.setAttribute("itemList", items);
		response.sendRedirect("/index.jsp");
	}

	private void deleteTask() {
		for (TodoItem item : todoItemList) {
			if (item.isState())
				todoItemList.remove(item);
		}
	}

	private void updateList(HttpServletRequest request) {
		for (TodoItem item : todoItemList) {
			String state = String.valueOf(request.getParameter(item.getName() + "_state"));
			boolean curState = state.equals("on");
			if (curState != item.isState())
				item.setState(curState);
		}
	}

	private void addTask(HttpServletRequest request) {
		String category = request.getParameter("inputItemCategory");
		String name = request.getParameter("inputItemName");
		if (!(category.isEmpty() & name.isEmpty()))
			todoItemList.add(new TodoItem(category, name, false));
	}
}
