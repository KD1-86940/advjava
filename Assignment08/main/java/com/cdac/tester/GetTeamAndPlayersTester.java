package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getSessionFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.PlayerDao;
import com.cdac.dao.TeamDao;
import com.cdac.dao.TeamDaoImpl;
import com.cdac.dao.playerDaoImpl;
import com.cdac.entities.Team;

public class GetTeamAndPlayersTester {

	public static void main(String[] args) {
		try (SessionFactory sf=getSessionFactory();
				Scanner sc=new Scanner(System.in);){
					TeamDao tdao=new TeamDaoImpl();
					System.out.print("Team Id        :");
					long teamId=sc.nextInt();
					Team team=tdao.getTeamsAndPlayers(teamId);
					System.out.println(team);
                    team.getPlayers().
					forEach(System.out::println);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
