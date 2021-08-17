package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IMessageDao;
import com.senlaCourses.adPlacementSystem.domain.model.Message;
import org.springframework.stereotype.Repository;

/**
 * Class for data access Chat.class object.
 */
@Repository
public class MessageDao extends AbstractHibernateDao<Message> implements IMessageDao {

  public MessageDao() {
    super(Message.class);
  }
}