/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.User;
import pl.edu.pjwstk.cms.utils.HexConverter;
import pl.edu.pjwstk.cms.utils.Utils;

/**
 *
 * @author Macha
 */
@Controller
public class HomeController extends BaseController {

    private final static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    public HomeController() {

    }

    @Override
    @RequestMapping("home")
    protected ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView("home");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        java.io.File file = Utils.getFileFromHash(userDto.getPhotoHash());
        return model;
    }

    @RequestMapping(value = "getUserImage")
    public void getUserImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        if (!userDto.getPhotoHash().isEmpty()) {
            java.io.File file = null;
            try {
                file = Utils.getFileFromHash(userDto.getPhotoHash());
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] buffer = HexConverter.toBytesFromHex(userDto.getPhotoHash());
            response.setContentType("image/jpeg");
            InputStream in1 = new ByteArrayInputStream(buffer);
            IOUtils.copy(in1, response.getOutputStream());
        }
    }

    @RequestMapping(value = "uploadPhoto", method = RequestMethod.POST)
    public ModelAndView uploadPhoto(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("home");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        User user = userDao.selectForId(userDto.getId());
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            FileItem item = items.get(0);
            if (item.getSize() == 0) {
                model.addObject("error", "Nie wybrano żadnego pliku");
                return model;
                //System.out.println("no file");
            }
            if (!item.getContentType().equals("image/jpeg")
                    && !item.getContentType().equals("image/png")
                    && !item.getContentType().equals("image/gif")) {
                model.addObject("error", "Plik musi posiadać rozszerzenie jpg, png lub gif.");
                return model;
            }
            InputStream input = item.getInputStream();
            byte[] barr = new byte[(int) item.getSize()];
            input.read(barr);
            String s = HexConverter.toHexFromBytes(barr);
            user.setPhotoHash(s);
            userDto.setPhotoHash(s);
            userDao.update(user);
        } catch (FileUploadException ex) {
            System.err.println("Can't process file\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        request.getSession().setAttribute("user", userDto);
        return model;
    }

}
