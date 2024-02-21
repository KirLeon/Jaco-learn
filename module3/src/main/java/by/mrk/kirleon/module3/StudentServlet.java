package by.mrk.kirleon.module3;

import by.mrk.kirleon.module3.entity.Student;
import by.mrk.kirleon.module3.service.StudentService;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/students/*")
public class StudentServlet extends HttpServlet {
	@EJB
	private StudentService studentService;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		String pathInfo = req.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().println("Please provide a valid endpoint.");
			return;
		}
		
		String[] pathParts = pathInfo.split("/");
		if (pathParts.length != 2) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().println("Invalid endpoint format.");
			return;
		}
		
		String operation = pathParts[1];
		String parameter = req.getParameter("value");
		
		
		switch (operation) {
			case "lastName":
				handleLastName(resp, parameter);
				break;
			case "firstName":
				handleFirstName(resp, parameter);
				break;
			case "group":
				handleGroup(resp, parameter);
				break;
			case "course":
				handleCourse(resp, parameter);
				break;
			case "birthYear":
				handleBirthYear(resp, parameter);
				break;
			default:
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().println("Invalid operation.");
			
		}
	}
	
	public void handleLastName(HttpServletResponse resp, String parameter) throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public void handleFirstName(HttpServletResponse resp, String parameter) throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public void handleGroup(HttpServletResponse resp, String parameter) throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public void handleCourse(HttpServletResponse resp, String parameter) throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public void handleBirthYear(HttpServletResponse resp, String parameter) throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public void printResponse(PrintWriter writer, List<Student> students) {
		throw new UnsupportedOperationException();
	}
}
