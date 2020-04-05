package com.example.demo;

import java.io.*;

public class OpenFile {
    static public String port;
    static public String path;
    static public String token;
    static public String link;
    static {
        path="";
    }
    static public String ReadFile() throws IOException {
        File file = new File(path);
        String text = "";
        if (file.exists()) {
            FileReader fr = new FileReader(file);
            int i;
            while ((i = fr.read()) != -1) {
                text+=(char) i;
            }
            fr.close();
        }
        return text;
    }
    static void GetPath()
    {
        try{
            String command = "powershell.exe  Get-Process LeagueClientUx | ForEach-Object {$_.Path}";
            // Executing the command
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            // Getting the results
            powerShellProcess.getOutputStream().close();
            String line;
            String s="";
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                s+=line;
                break;
            }
            path=s.replace("LeagueClientUx.exe","")+"lockfile";
        } catch (IOException e) {
            System.out.println("Can't connect to Client");
        }
    }
    static void SetINF() throws IOException {
        String text=ReadFile();
        port=text.split(":")[2];
        token=text.split(":")[3];
        link="https://riot:"+token+"@127.0.0.1:"+port;
    }
}