package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;

public class uploadPicture extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONArray array = new JSONArray();
        Map map = new HashMap();
        try {

            Map pic = new HashMap();
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);

            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                try {
                    List<FileItem> items = upload.parseRequest(request);
                    for (FileItem fileItem : items) {
                        if (!fileItem.isFormField()) {
                            String name = new File(fileItem.getName()).getName();
                            String saveName = "img\\profile\\" + System.currentTimeMillis() + "_" + name;
                            fileItem.write(new File(request.getServletContext().getRealPath("/") + saveName));
                            pic.put("pic_url", saveName);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("File Upload Failed !");
                }

                map.put("status", "success");
                map.put("pic", pic);
            }
        } catch (Exception e) {
            map.put("status", "error");
            e.printStackTrace();
        }

        array.add(map);
        out.write(array.toJSONString());
    }
}
