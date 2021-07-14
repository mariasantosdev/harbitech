package br.com.harbitech.school.servlet;

import org.apache.http.client.fluent.Request;

public class ClienteWebServiceCategory {
    public static void main(String[] args) throws Exception{
        String content = Request
                .Get("http://localhost:8080/harbitech/api/todascategorias")
                .addHeader("Accept","application/json")
                .execute()
                .returnContent()
                .asString();
        System.out.println(content);
    }
}