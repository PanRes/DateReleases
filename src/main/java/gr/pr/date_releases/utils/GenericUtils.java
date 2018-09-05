package gr.pr.date_releases.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.*;

public class GenericUtils {

	public static String uploadFile(MultipartFile filePart, String saveDir, String filePrefix)
			throws IOException {
		String fileName = filePart.getName();
		File uploadedFile = new File(saveDir + File.separator + filePrefix + fileName);

		if(!uploadedFile.getParentFile().exists()){
			uploadedFile.getParentFile().mkdirs();
		}

		try {
			uploadedFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			OutputStream out = new FileOutputStream(uploadedFile);
			InputStream fileContent = filePart.getInputStream();
			int read;
			byte[] bytes = new byte[1024];

			while((read = fileContent.read(bytes)) != -1){
				out.write(bytes,0,read);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		return uploadedFile.getAbsolutePath();
	}


	public static String extractFileName(Part part){
		String contextDisp = part.getHeader("content-disposition");
		String[] items = contextDisp.split(";");
		for (String s : items) {
			if(s.trim().startsWith("filename")){
				return s.substring(s.indexOf("=") + 1).trim().replace("\"","");
			}
		}
		return "";
	}
}
