package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.PositionDto;
import pl.edu.pjwstk.cms.models.Position;


public class PositionDao extends GenericDao<Position>{
    
    private final static Logger LOGGER = Logger.getLogger(PositionDao.class.getName()); 

    public PositionDao() {
        super(Position.class);
    }
    public List<PositionDto> getPositionDtoList() {
        return getPositionDtoList(new HashMap<String, List<String>>());
    }
    
    public List<PositionDto> getPositionDtoList(Map<String, List<String>> params) {
        String query = "SELECT pos.name as name, pos.description as description, pos.id as id ";
        query += "FROM position as pos ";
        if(!params.isEmpty()) {
            query += "WHERE";
            query = this.addParamConditions(query, params);
        }
        ResultSet set = this.connectionManager.select(query);
        List<PositionDto> posDtos = new ArrayList<>();
        try {
            while(set.next()) {
                PositionDto dto = new PositionDto();
                dto.setId(set.getLong("id"));
                dto.setName(set.getString("name"));
                dto.setDescription(set.getString("description"));
                posDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return posDtos;
    }
}
