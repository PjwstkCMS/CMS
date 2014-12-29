/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.M;
import pl.edu.pjwstk.cms.models.Message;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.MessageDto;

/**
 *
 * @author sergio
 */
public class MessageDao extends GenericDao<Message> {

    private static final Logger LOGGER = Logger.getLogger(CardDao.class.getName());

    public MessageDao() {
        super(Message.class);
    }

    public List<MessageDto> getMessageDtos(Map<String, List<String>> params) {
        MessageDao msgDao = new MessageDao();
        List<MessageDto> list = new ArrayList<>();
        String query = "SELECT m.id as id, m.from_userid as fromId, m.to_userid "
                + "as toId, m.timestamp as stamp, m.content as content, m.ifread "
                + "as ifread, u.login as fromLogin "
                + "FROM message as m, user as u ";
        query += "WHERE ";
        if (!params.isEmpty()) {            
            query = this.addParamConditions(query, params);
            query += " AND ";
        }
        query += "to_userid=u.id";
        ResultSet set = connectionManager.select(query);
        try {
            while (set.next()) {
                MessageDto dto = new MessageDto();
                dto.setId(set.getLong("id"));
                dto.setContent(set.getString("content"));
                dto.setFromId(set.getString("fromId"));
                dto.setFrom(set.getString("fromLogin"));
                dto.setTimestamp(set.getString("stamp"));
                dto.setToId(set.getString("toId"));
                dto.setRead(set.getString("ifread"));
                list.add(dto);
            }
        } catch (SQLException ex) {
            LOGGER.warning(ex.getLocalizedMessage());
        }
        return list;
    }
}
