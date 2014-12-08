/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.models.Message;
import pl.edu.pjwstk.cms.dao.general.GenericDao;

/**
 *
 * @author sergio
 */
public class MessageDao extends GenericDao<Message> {

    private static final Logger LOGGER = Logger.getLogger(CardDao.class.getName());

    public MessageDao() {
        super(Message.class);
    }
}
