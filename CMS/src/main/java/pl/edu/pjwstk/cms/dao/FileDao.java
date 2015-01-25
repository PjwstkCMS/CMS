package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.FileDto;
import pl.edu.pjwstk.cms.models.File;


public class FileDao extends GenericDao<File> {

    private final static Logger LOGGER = Logger.getLogger(FileDao.class.getName());

    public FileDao() {
        super(File.class);
    }

    public List<FileDto> getReportDtos() {
        String query = "SELECT f.id as id, f.name as name, f.description as description, f.mimeType as mimeType "
                + "FROM file as f";
        List<FileDto> dtos = new ArrayList<>();
        ResultSet set = connectionManager.select(query);
        try {
            while (set.next()) {
                FileDto dto = new FileDto();
                dto.setId(set.getLong("id"));
                dto.setDescription(set.getString("description"));
                dto.setMimeType(set.getString("mimeType"));
                dto.setName(set.getString("name"));
                dtos.add(dto);
            }

        } catch (SQLException sqlEx) {
            LOGGER.warning(sqlEx.getLocalizedMessage());
        }
        return dtos ;
    }
    
}


