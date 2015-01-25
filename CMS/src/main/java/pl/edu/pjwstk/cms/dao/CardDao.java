package pl.edu.pjwstk.cms.dao;

import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.models.Card;


public class CardDao extends GenericDao<Card> {

    private static final Logger LOGGER = Logger.getLogger(CardDao.class.getName());

    public CardDao() {
        super(Card.class);
    }
}
