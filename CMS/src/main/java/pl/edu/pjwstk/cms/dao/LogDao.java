package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.LogDto;
import pl.edu.pjwstk.cms.models.Log;

/**
 *
 * @author Macha
 */
public class LogDao extends GenericDao<Log>{
    
    private final static Logger LOGGER = Logger.getLogger(LogDao.class.getName()); 

    public LogDao() {
        super(Log.class);
    }
    
    public List<LogDto> getLogDtoList() {
        return getLogDtoList(null);
    }
    
    public List<LogDto> getLogDtoList(Long empId) {
        String query = "SELECT per.forename as forename, per.surname as surname, "
                + "log.timestamp as timestamp, emp.id as empId, log.id as id, log.terminalId as terminalId, ter.description as terminalDesc ";
        query+= "FROM employee as emp, persondata as per, log as log, terminal as ter ";
        query+="WHERE emp.id=log.employeeId AND emp.persondataId=per.id AND ter.id=log.terminalId ";
        if(empId!=null) {
            query+="AND emp.id="+empId;
        }
        List<LogDto> dtos = new ArrayList<>();
        ResultSet set = this.connectionManager.select(query);
        try {
            while (set.next()) {
                LogDto dto = new LogDto();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date d = set.getDate("timestamp");
                dto.setDate(sdf.format(set.getTimestamp("timestamp")));
                dto.setEmpName(set.getString("forename"));
                dto.setEmpSurname(set.getString("surname"));
                dto.setEmployeeId(set.getLong("empId"));
                dto.setTerminalId(set.getLong("terminalId"));
                dto.setTerminalDesc(set.getString("terminalDesc"));
                dto.setId(set.getLong("id"));
                dtos.add(dto);
            }
        } catch (Exception e) {
            LOGGER.warning(e.getLocalizedMessage());
        }
        
        return dtos;
    }

}
