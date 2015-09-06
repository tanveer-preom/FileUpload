

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.jboss.weld.Container.available;
import sun.misc.BASE64Decoder;

/**
 *
 * @author tanveer
 */
public class Upload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("img_name");
        String encodedImage = req.getParameter("encoded_image");
        System.out.println(name);
        System.out.println(encodedImage);
        PrintWriter writer = resp.getWriter();
        writer.println("Image Uploaded Successfully");
        
        
    }
    
}
