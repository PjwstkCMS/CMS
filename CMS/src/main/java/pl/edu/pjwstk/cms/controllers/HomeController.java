/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pjwstk.cms.controllers.general.BaseController;
import pl.edu.pjwstk.cms.dao.FileDao;
import pl.edu.pjwstk.cms.dao.PersonDataDao;
import pl.edu.pjwstk.cms.dao.UserDao;
import pl.edu.pjwstk.cms.dto.UserDto;
import pl.edu.pjwstk.cms.models.File;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.models.User;
import pl.edu.pjwstk.cms.utils.HexConverter;

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
        try {
            byte barray[] = HexConverter.toBytesFromHex(userDto.getPhotoHash());
            //String get_price = rs.getString(5);
            java.io.File someFile = new java.io.File("/image.jpg");
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(barray);
            fos.flush();
            fos.close();
            model.addObject("userImage", someFile);
            System.out.println("photo");
        } catch (IOException io) {
            LOGGER.warning(io.getLocalizedMessage());
        }
        return model;
    }

    @RequestMapping(value = "downloadPhoto")
    public ModelAndView downloadPhoto(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("home");

        return model;
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
            if (!item.getContentType().equals("image/jpeg")) {
                model.addObject("error", "Plik musi posiadać rozszerzenie jpg");
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

    @RequestMapping(value = "/aaaa", method = RequestMethod.POST)
    public ModelAndView uploadPhotoOld(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("home");
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            FileItem item = items.get(0);
            if (item.getSize() == 0) {
                model.addObject("error", "Nie wybrano żadnego pliku");
                return model;
                //System.out.println("no file");
            }
            if (!item.getContentType().equals("image/jpeg")) {
                model.addObject("error", "Plik musi posiadać rozszerzenie jpg");
                return model;
            }
            File r = new File();
            FileDao fileDao = new FileDao();
            UserDto user = (UserDto) request.getSession().getAttribute("user");
            System.out.println(items.get(0).getContentType() + " " + item.getSize());
            PersonDataDao perDao = new PersonDataDao();
            PersonData perData = perDao.selectForId(user.getPersondataId());
            r = fileDao.selectSingleRecord("name", perData.getPesel() + "Photo");
            if (r == null) {
                r = new File();
                r.setName(perData.getPesel() + "Photo");
                r.setMimeType(item.getContentType());
                r.setDescription("Zdjęcie użytkownika " + user.getLogin());
            }
            InputStream input = item.getInputStream();
            byte[] barr = new byte[(int) item.getSize()];
            input.read(barr);
            String s = HexConverter.toHexFromBytes(barr);
            r.setHashCode(s);
            if (r.getId() != null) {
                fileDao.update(r);
            } else {
                fileDao.insert(r);
            }
        } catch (FileUploadException ex) {
            System.err.println("Can't process file\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return model;

    }

}
