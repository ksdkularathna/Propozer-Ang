package com.bargadss.Propozal.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bargadss.Propozal.bean.ImageProcessing;

public class ImageProcessingDao {
	
	Session session;

	public void saveImage(byte[] images,SessionFactory factory) {
		
		session=factory.openSession();
		
		session.beginTransaction();

		// save image into database
		//File file = new File("C:\\mavan-hibernate-image-mysql.gif");
		
//		File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
//		
//		byte[] bFile = new byte[(int) file.length()];
//
//		try {
//			FileInputStream fileInputStream = new FileInputStream(file);
//			// convert file into array of bytes
//			fileInputStream.read(bFile);
//			fileInputStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		ImageProcessing image=new ImageProcessing();
		image.setImage(images);
		
		//byte[] encoded = Base64.getEncoder().encode(images);
//		String im=new String(encoded);
//		
//		System.out.println(im);
//		
//		image.setImageString(im);

		session.save(image);
		//session.save(arg0)
		
		System.out.println("Saved ..");
		
		session.getTransaction().commit();
	}

}
