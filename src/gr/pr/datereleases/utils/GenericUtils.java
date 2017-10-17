package gr.pr.datereleases.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericUtils {

    public static File uploadFile(Part filePart, File saveDir,String filePrefix)
            throws IOException, ServletException {
        String fileName = extractFileName(filePart);
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
            int read = 0;
            byte[] bytes = new byte[1024];

            while((read = fileContent.read(bytes)) != -1){
                out.write(bytes,0,read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadedFile;
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
