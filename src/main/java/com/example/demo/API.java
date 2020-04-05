package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class API {
    static CloseableHttpClient client;
    static Map<String,String>ChampionId;
    static String ID;
    static
    {
        try {
            client=Client.Create();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
    static void GetChampion() throws IOException {
        HttpGet httpGet=new HttpGet(OpenFile.link+"/lol-champions/v1/owned-champions-minimal");
        HttpResponse response=client.execute(httpGet);
        String content=Client.GetContent(response);
        ObjectMapper mapper=new ObjectMapper();
        JsonNode jsonNodes=mapper.readTree(content);
        ChampionId=new HashMap<String, String>();
        for (JsonNode node:jsonNodes) {
            ChampionId.put(node.get("alias").asText().toLowerCase(), node.get("id").asText());
        }
    }
    static String GetChampionId(String Champion)
    {
        return ChampionId.getOrDefault(Champion, "-1");
    }
    static boolean CheckMatch() throws IOException {
        HttpGet httpGet=new HttpGet(OpenFile.link+"/lol-matchmaking/v1/ready-check");
        HttpResponse response=client.execute(httpGet);
        String content=Client.GetContent(response);
        Object object= JSONValue.parse(content);
        JSONObject js=(JSONObject)object;
        try{
            if(js.get("state").equals("InProgress"))return true;
        }
        catch (Exception e)
        {
            System.out.print(".");
        }
        return false;
    }
    static void AcceptMatch() throws IOException {
        HttpPost post=new HttpPost(OpenFile.link+"/lol-matchmaking/v1/ready-check/accept");
        HttpResponse response=client.execute(post);
    }
    static String Session() throws IOException {
        HttpGet get=new HttpGet(OpenFile.link+"/lol-champ-select/v1/session");
        HttpResponse response=client.execute(get);
        return Client.GetContent(response);
    }
    static String GetId() throws IOException {
        try{
        String json =Session();
        ObjectMapper mapper=new ObjectMapper();
        JsonNode node=mapper.readTree(json);
        String Local=node.get("localPlayerCellId").asText();
        JsonNode arr=node.get("actions");
        if(arr.isArray())
        {
            for (JsonNode arrnode:arr) {
                for (JsonNode arr1:arrnode
                ) {
                    if(arr1.get("actorCellId").asText().equals(Local))
                    return arr1.get("id").asText();
                }
            }
        }
        }
        catch (Exception e)
        {
            return "-1";
        }
        return "-1";
    }
    static boolean Pick() throws IOException {
        try{
        HttpPatch patch=new HttpPatch(OpenFile.link+"/lol-champ-select/v1/session/actions/"+GetId());
        StringEntity entity=new StringEntity("{\"championId\" : \""+ID+"\"}\n");
        patch.setEntity(entity);
        patch.setHeader("Content-type","application/json; charset=UTF-8");
        HttpResponse response=client.execute(patch);
        String text=Client.GetContent(response);
        if(text.equals(""))return true;
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }
    static void Lock() throws IOException {
        HttpPost post=new HttpPost(OpenFile.link+"/lol-champ-select/v1/session/actions/"+GetId()+"/complete");
        HttpResponse response=client.execute(post);
    }
}
