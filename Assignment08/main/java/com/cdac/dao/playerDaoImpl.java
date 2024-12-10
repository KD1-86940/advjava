package com.cdac.dao;
import static com.cdac.utils.HibernateUtils.getSessionFactory;
import org.hibernate.*;
import com.cdac.entities.Players;
import com.cdac.entities.Team;


public class playerDaoImpl implements PlayerDao{

	@Override
	public String addPlayer(Players p,long teamId) {
		Session session=getSessionFactory().getCurrentSession();
		String msg="Insertion failed";
		Transaction tx=session.beginTransaction();
		try {
			Team team=session.get(Team.class, teamId);
			if(team!=null)
			{
				team.addPlayer(p);
				session.persist(p);
				tx.commit();
			}
			
			msg="Insertion Successfull";
		}catch(RuntimeException e){
			if(tx==null)
			{
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}

	@Override
	public String deletePlayer(long teamId, long playerId) {
		Session session=getSessionFactory().getCurrentSession();
		String msg="Deletion failed";
		Transaction tx=session.beginTransaction();
		try {
			Team team=session.get(Team.class, teamId);
			Players p=session.get(Players.class, playerId);
			if(team!=null && p!=null)
			{
				team.removePlayer(p);
				session.persist(team);
				tx.commit();
			}
			
			msg="Deletion Successfull";
		}catch(RuntimeException e){
			if(tx==null)
			{
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}

}
