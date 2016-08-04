package com.bargadss.Propozal.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bargadss.Propozal.bean.ImageUpload;
import com.bargadss.Propozal.util.HibernateUtil;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet extends HttpServlet {

	// ***************************************************************************************//
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}

	// ***************************************************************************************//
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		boolean flag = false;

		try {

			String path = null;

			PrintWriter out1 = response.getWriter();

			HttpSession session = request.getSession();

			String email = request.getParameter("email");
			String check = request.getParameter("profile");

			String profilePic = "profile";
			String otherPic = "other";

			System.out.println("email: " + email);
			System.out.println("check: " + check);

			Part filePart = request.getPart("filecover");

			String photo = "";
			// _________________________________________________________________________commentedOut
			path="../webapps/Propozal/propzer/imageUpload";

			//path = "E:\\ProjectWorkspace\\Propozal\\WebContent\\propzer\\UploadedPhoto\\" + email;	\
			//path = "E:\\ProjectWorkspace\\Propozal\\WebContent\\propzer\\imageUpload\\";	
			
//			if (check == null) {
//				path = "E:\\ProjectWorkspace\\Propozal\\WebContent\\propzer\\UploadedPhoto\\" + email+"Other";
//			} else {
//				path = "E:\\ProjectWorkspace\\Propozal\\WebContent\\propzer\\UploadedPhoto\\" + email+"Profile";
//			}
			
			File file = new File(path);
			file.mkdir();
			String fileName = getFileName(filePart);

			String imagePath = getFileName(filePart);

			System.out.println("fileName" + fileName);
			System.out.println("imagePath" + imagePath);

			OutputStream out = null;

			InputStream filecontent = null;

			PrintWriter writer = response.getWriter();
			try {
				out = new FileOutputStream(new File(path + File.separator
						+ fileName));

				filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);

					photo = path + "/" + fileName;
				}

				out.close();

				SessionFactory factory = new HibernateUtil()
						.createSessionFactory();

				Session hibernateSession = factory.openSession();

				ImageUpload upload = new ImageUpload();

				upload.setEmail(email);
				System.out.println("imagePath" + imagePath);
				upload.setImagePath(imagePath);

				if (check == null) {
					upload.setImageType(otherPic);
				} else {
					upload.setImageType(profilePic);
				}

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String d = formatter.format(date);
				Date uploadDate = formatter.parse(d);

				upload.setUploadDate(uploadDate);

				Transaction transaction = hibernateSession.beginTransaction();
				hibernateSession.save(upload);
				transaction.commit();

				hibernateSession.close();

				flag = true;

				if (flag) {
					out1.println("<html><body><script>alert('Sucessfully Saved!');</script></body></html>");
				} else {
					out1.println("<html><body><script>alert('Please try again!');</script></body></html>");
				}

			} catch (Exception e) {

			}
			// out1.println("<html><body><script>alert('Sucessfully Saved! Check the database and the path!');</script></body></html>");

			//response.sendRedirect("http://192.168.0.51:8081/Propozal/index.html#/myProfile");
			response.sendRedirect("http://54.169.251.109:8080/Propozal/index.html#/myProfile");
		} catch (Exception ex) {

		}
	}

	// ***************************************************************************************//

	private String getFileName(final Part part) {

		// System.out.println("--------------Part-----------------: "+part);

		final String partHeader = part.getHeader("content-disposition");

		// System.out.println("--------------partHeader-----------------: "+partHeader);

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
	// ***************************************************************************************//
}