package gr.pr.datereleases.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericUtils {

    public static File uploadFile(HttpServletRequest request) throws IOException, ServletException {
        java.util.Date currDate = new java.util.Date();
        Part filePart = request.getPart("uploadXlsx");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy.MM.dd_hh-mm-ss");
        String fileName = extractFileName(filePart);
        System.out.println(fileName);
        System.out.println(getFileNameWithoutExtension(fileName));
        System.out.println(getFileExtension(fileName));
        fileName = getFileNameWithoutExtension(fileName) + sdf.format(now) + getFileExtension(fileName);
        System.out.println(fileName);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String saveDir = "C:" + File.separator + "JavaTools" + File.separator + "uploadedFiles" +
                File.separator + sdf.format(currDate);
        File uploadedFile = new File(saveDir + File.separator + fileName);


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

    public static String getFileNameWithoutExtension(String fullFileName){
        return fullFileName.substring(0,fullFileName.indexOf("."));
    }

    public static String getFileExtension(String fullFileName){
        return fullFileName.substring(fullFileName.indexOf("."));
    }
}
