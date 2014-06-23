package com.darlen.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description:
 * User: Darlen liu
 * Date: 14-6-23
 * Time: 下午9:57
 */
public class UploadImgServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UploadImgServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 1. 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //           设置上传文件时用于临时存放文件的内存大小,这里是2K.多于的部分将临时存在硬盘
        factory.setSizeThreshold(1024*2);
//      设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录
        factory.setRepository(new File("c:\\file"));//临时文件

//          以上两项可通过DiskFileItemFactory构参来创建
//          DiskFileItemFactory factory = new DiskFileItemFactory(yourMaxMemorySize, yourTempDirectory);
        // 2. 创建FileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
       // 设置最大上传大小 10M
        upload.setSizeMax(1024*1024*10);

        // 3. 判断是否是上传表单
        boolean b = upload.isMultipartContent(request);

        if(!b) {
            //不是文件上传
            request.setAttribute("message","对不起，不是文件上传表单！");
            logger.info("对不起，不是文件上传表单！");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }


        try {
            // 是文件上传表单
            // 4. 解析request，获得FileItem项
            List<FileItem> fileItems = upload.parseRequest(request);

            // 5. 遍历集合
            for(FileItem item : fileItems) {
                // //  判断是否是表单元素(<input type="text" />单选，多选等)
                if(item.isFormField()) {
                    //得到文件的名称
                    String name = item.getFieldName();
                    String value = item.getString();
                    //手工的转换
                    value = new String(value.getBytes("iso-8859-1"),"utf-8");
                    logger.info(name + "=" + value);

                }else {
                    // 文件上传字段
                    // 获得文件名
                    String filename = item.getName();
                    logger.info("filename:"+filename);
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    logger.info("filename:"+filename);
                    // 创建文件
                    ServletContext context = getServletContext();
                    String dir = context.getRealPath("WEN-INF/upload");
                    File file = new File(dir,filename);
                    file.createNewFile();

                    //获得流，读取数据写入文件
                    InputStream in = item.getInputStream();
                    FileOutputStream fos = new FileOutputStream(file);

                    int len ;
                    byte[] buffer = new byte[1024];
                    while ((len = in.read(buffer)) > 0) {
                        fos.write(buffer,0,len);
                    }
                    fos.close();
                    in.close();
                    item.delete();
                }
            }

        } catch (Exception e) {
           logger.error(e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doPost(request,response);
    }
}
