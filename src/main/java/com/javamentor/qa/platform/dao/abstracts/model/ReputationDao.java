package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;

public interface ReputationDao extends ReadWriteDao<Reputation, Long>{
    Reputation getReputationByAnswerId(Long answerId);
}