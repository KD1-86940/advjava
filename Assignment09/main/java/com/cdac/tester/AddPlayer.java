package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getSessionFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.PlayerDao;
import com.cdac.dao.TeamDao;
import com.cdac.dao.TeamDaoImpl;
import com.cdac.dao.playerDaoImpl;
import com.cdac.entities.Players;
import com.cdac.entities.Team;

public class AddPlayer {
	public static void main(String[] args) {
		try (SessionFactory sf=getSessionFactory();
				Scanner sc=new Scanner(System.in);){
					PlayerDao pdao=new playerDaoImpl();
					System.out.println("Enter player details : \n");
					System.out.print("first name       : ");
					String name=sc.next();
					System.out.print("last name        : ");
					String abbravation=sc.next();
					System.out.print("Date of birth    :");
					LocalDate dob=LocalDate.parse(sc.next());
					System.out.print("Batting Average:");
					double avg=sc.nextDouble();
					System.out.print("Wickets        :");
					int wkt=sc.nextInt();
					System.out.print("Team Id        :");
					long teamId=sc.nextInt();
					Players p=new Players(name,abbravation,dob,avg,wkt);
					System.out.print(pdao.addPlayer(p,teamId));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
