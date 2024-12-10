package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.PlayerDao;
import com.cdac.dao.playerDaoImpl;
import com.cdac.entities.Players;

public class DeletePlayer {

	public static void main(String[] args) {
		try (SessionFactory sf=getSessionFactory();
				Scanner sc=new Scanner(System.in);){
					PlayerDao pdao=new playerDaoImpl();
					System.out.println("Enter player details : \n");
					System.out.print("Player Id      : ");
					long playerId=sc.nextInt();
					System.out.print("Team Id        :");
					long teamId=sc.nextInt();
					System.out.print(pdao.deletePlayer(teamId,playerId));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
