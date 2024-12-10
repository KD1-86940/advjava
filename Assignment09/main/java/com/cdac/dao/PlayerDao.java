package com.cdac.dao;

import com.cdac.entities.Players;

public interface PlayerDao {

	String addPlayer(Players p,long teamId);

	String deletePlayer(long teamId, long playerId);
   
}
