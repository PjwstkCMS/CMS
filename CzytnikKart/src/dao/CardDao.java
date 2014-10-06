/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.logging.Logger;
import model.Card;

/**
 *
 * @author sergio
 */
public class CardDao extends GenericDao<Card> {

    private static final Logger LOGGER = Logger.getLogger(CardDao.class.getName());

    public CardDao() {
        super(Card.class);
    }
}
