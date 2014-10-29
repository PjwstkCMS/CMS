package pl.edu.pjwstk.cms.sevlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.EmployeeDao;
import pl.edu.pjwstk.cms.dao.FileDao;
import pl.edu.pjwstk.cms.dao.PersonDataDao;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.File;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.utils.HexConverter;

public class PhotoShowServlet extends HttpServlet {

    private Logger LOGGER; 
    private static final long serialVersionUID = 1L;

    public PhotoShowServlet() {
        super();
        LOGGER = Logger.getLogger(this.getClass().getCanonicalName());
    }
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String pesel = request.getParameter("pesel");
        String empId = request.getParameter("empId");
        FileDao fileDao = new FileDao();
        try {
            File photo = null;
            if (pesel != null) {
                photo = fileDao.selectRecordsWithFieldValues("name", pesel + "Photo").get(0);
            } else if (empId != null){
                EmployeeDao employeeDao = new EmployeeDao();           
                Employee emp = employeeDao.selectForId(empId);
                PersonDataDao personDao = new PersonDataDao();
                PersonData perData = personDao.selectSingleRecord("id", emp.getPersondataId());
                photo = fileDao.selectSingleRecord("name",perData.getPesel() + "Photo");
            }

            byte barray[] = HexConverter.toBytesFromHex(photo.getHashCode());
            //String get_price = rs.getString(5);
            response.setContentType("image/gif");
            response.setContentLength(barray.length);
            response.getOutputStream().write(barray);
            System.out.println("photo");
            //out.println("Price in Rs. " + get_price);

            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (NullPointerException nullEx) {
            LOGGER.warning(nullEx.getLocalizedMessage());
        } catch (IndexOutOfBoundsException indexEx) {
            LOGGER.warning(indexEx.getLocalizedMessage());
        }
    }

}
