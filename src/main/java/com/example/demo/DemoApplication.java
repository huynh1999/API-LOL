package com.example.demo;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		SpringApplication.run(DemoApplication.class, args);
//		ThreadAPI api=new ThreadAPI();
//		Thread thread=new Thread(api);
//		thread.start();
//		OpenFile.GetPath();
//		OpenFile.SetINF();
//		API.GetChampion();
//		System.out.print(API.ChampionId.get("yasuo"));

//		CloseableHttpClient client=Client.Create();
//		RequestBuilder requestBuilder=RequestBuilder.post();
//		requestBuilder.setUri("https://dkhp.uit.edu.vn/");
//		requestBuilder.addParameter("name","17520599").addParameter("pass","09051302").addParameter("form_id","user_login");
//		HttpUriRequest request=requestBuilder.build();
//		HttpResponse response=client.execute(request);
//		System.out.println(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
//		HttpGet get=new HttpGet("https://dkhp.uit.edu.vn/sinhvien/hocphan/dangky");
//		response=client.execute(get);
//		System.out.println(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

	}
}
