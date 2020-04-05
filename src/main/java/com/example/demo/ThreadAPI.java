package com.example.demo;

import lombok.SneakyThrows;
import java.util.Scanner;

public class ThreadAPI implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        while (true)
        {
            OpenFile.GetPath();
            if(OpenFile.path.equals("lockfile"))
            {
                System.out.println("LeagueClient not opened.... ");
                Thread.sleep(4000);
            }
            else {
                OpenFile.SetINF();
                System.out.println("Connected....");
                API.GetChampion();
                break;
            }
        }
        while (true)
        {
            System.out.println("Nhap ten tuong :");
            Scanner sc = new Scanner(System.in);
            String Champion=sc.next();
            Champion= Champion.toLowerCase();
            if(!API.GetChampionId(Champion).equals("-1"))
            {
                API.ID=API.GetChampionId(Champion);
                System.out.println("ID cua tuong :"+API.ID);
                break;
            }
            System.out.println("Khong co tuong hoac nhap sai ten");
        }
        while(true)
        {
                if(API.CheckMatch()){
                    API.AcceptMatch();
                }
                else if(!API.GetId().equals("-1"))
                {
                    if(API.Pick())
                    {
                        API.Lock();
                        System.out.println("END....");
                        break;
                    }
                }
                java.lang.Thread.sleep(500);
        }
    }
}
