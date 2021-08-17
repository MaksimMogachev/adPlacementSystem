package com.senlaCourses.adPlacementSystem.dao;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IChatDao;
import com.senlaCourses.adPlacementSystem.domain.model.Chat;
import org.springframework.stereotype.Repository;

/**
 * Class for data access Chat.class object.
 */
@Repository
public class ChatDao extends AbstractHibernateDao<Chat> implements IChatDao {

  public ChatDao() {
    super(Chat.class);
  }
}