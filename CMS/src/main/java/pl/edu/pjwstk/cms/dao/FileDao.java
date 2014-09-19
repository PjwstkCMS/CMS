package pl.edu.pjwstk.cms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.FileDto;
import pl.edu.pjwstk.cms.models.File;


/**
 *
 * @author Macha
 */
public class FileDao extends GenericDao<File>{
    
    private final static Logger LOGGER = Logger.getLogger(FileDao.class.getName()); 

    public FileDao() {
        super(File.class);
    }
    
    public List<FileDto> getReportDtos() {
        List<String> params = new ArrayList<>();
        params.add("name");
        params.add("description");
        params.add("mimeType");
        List<File> reports = this.selectAll();
        List<FileDto> dtos = new ArrayList<>();
        for (File r : reports) {
            FileDto dto = new FileDto();
            dto.setId(r.getId());
            dto.setDescription(r.getDescription());
            dto.setName(r.getName());
            dto.setMimeType(r.getMimeType());
            dtos.add(dto);
        }
        return dtos;
    }

}
