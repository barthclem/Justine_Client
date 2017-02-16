package com.test;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aanu.oyeyemi on 1/6/17.
 * Project name -> GamePlayer
 */

public class Player {


    private static String ACCESS_TOKEN="";
    private static final String USERNAME="gamer";
    private static final String PASSWORD="gamer";
    private static final String GRANT_TYPE="password";
    private static final String AUTH_SERVER="http://localhost:8080/oauth/token";
    private static final String REST_SERVER="http://localhost:8080";
    private static final String AUTH_ACCESS="?grant_type=password&username=gamer&password=gamer";
    private static final String REST_ACCESS="?access_token=";

    private static String PLAYER_ID="";
    private static String RESPONSE="";
    private static List<Door> doors=Arrays.asList(new Door(1),new Door(2),new Door(3));

    private static Message game_report;


    private static HttpHeaders getHttpHeader(){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private static HttpHeaders getAuthHttpHeader(){
        String plainUserCredentials="clients:game";
        String encodedCredentials= Base64.encodeBase64String(plainUserCredentials.getBytes());
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Basic "+encodedCredentials);
        return headers;
    }

    private static AuthTokenInfo connect(){

        RestTemplate restTemplate=new RestTemplate();

        HttpEntity<String> request=new HttpEntity<>(getAuthHttpHeader());
        ResponseEntity<Object> entity=restTemplate.exchange(AUTH_SERVER+AUTH_ACCESS, HttpMethod.POST,request,Object.class);
        LinkedHashMap<String,Object> authMap=(LinkedHashMap<String,Object>)entity.getBody();

        AuthTokenInfo authTokenInfo=null;
        if(authMap!=null){
            authTokenInfo=new AuthTokenInfo();
            authTokenInfo.setAccess_token((String)authMap.get("access_token"));
            authTokenInfo.setExpires_in((Integer)authMap.get("expires_in"));
            authTokenInfo.setToken_type((String)authMap.get("grant_types"));
            authTokenInfo.setRefresh_token((String)authMap.get("refresh_token"));
            authTokenInfo.setScopes((String)authMap.get("scopes"));
        }

        ACCESS_TOKEN=authTokenInfo.getAccess_token();
        System.out.println("\n\t\t\t\t SyMSY is Connected\n\n");
        //System.out.println("\n\n\n AuthTokenInfo"+authTokenInfo+"\n\n\n");
        return authTokenInfo;
    }

    private static  void startGame(){
        RestTemplate restTemplate=new RestTemplate();

        HttpEntity<String> request=new HttpEntity<>(getHttpHeader());

        ResponseEntity<Message> responseEntity=restTemplate.exchange(REST_SERVER+"/game/"+REST_ACCESS+ACCESS_TOKEN,HttpMethod.POST
                               ,request,Message.class);

        Message messageBody=responseEntity.getBody();


        PLAYER_ID=messageBody.getLink();

        RESPONSE=messageBody.getMessage();

        game_report=messageBody;

        System.out.println("\n\n\n Message Body -> "+messageBody+"\n\n\n");

        System.out.println("\n\n\n Response -> "+RESPONSE+"\n\n\n");



    }

    private static void gotoPlayer() {
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<String>  request=new HttpEntity<>(getHttpHeader());

        ResponseEntity<Message> responseEntity=restTemplate.exchange(REST_SERVER+"/game/"+PLAYER_ID+REST_ACCESS+ACCESS_TOKEN,HttpMethod.GET
                               ,request,Message.class);


        Message messageBody=responseEntity.getBody();

        RESPONSE=messageBody.getMessage();
        game_report=messageBody;

        System.out.println("\n\n\n Message Body -> "+messageBody+"\n\n\n");
        System.out.println("\n\n\n Response -> "+RESPONSE+"\n\n\n");
    }

    private static void pickDoor(){
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<String>  request=new HttpEntity<>(getHttpHeader());
        int selectedDoor=selectDoor();
        ResponseEntity<Message> responseEntity=restTemplate.exchange(REST_SERVER+"/game/"+PLAYER_ID+"/pick/"+selectedDoor+REST_ACCESS+ACCESS_TOKEN,HttpMethod.GET
                ,request,Message.class);


        Message messageBody=responseEntity.getBody();

        RESPONSE=messageBody.getMessage()+"\n"+messageBody.getMessage1();
        game_report=messageBody;

        System.out.println("\n\n\n Message Body -> "+messageBody+"\n\n\n");
        System.out.println("\n\n\n Response -> "+RESPONSE+"\n\n\n");
    }

    private static int selectDoor(){
        System.out.print("Please choose one out of the three doors\n\n The Door Are labelled " + doors.toString()+"\t->\t");
        int playersChoice=0;
        boolean verify = true;
        while (verify) {
            try {

                playersChoice = new Scanner(System.in).nextInt();
                if(verifyChoice(playersChoice))
                {
                    deleteChoice(playersChoice);
                    verify=false;
                }
                else{
                    System.out.print("Please ensure you choose only one out of the three doors\n\n The Door Are labelled " + doors.toString()+"\t->\t");

                }
            }
            catch(IllegalArgumentException ex){
                System.out.println("Please try again with one of these "+doors.toString());
                ex.printStackTrace();
            }
        }
        return playersChoice;
    }

    private static boolean verifyChoice(int choice){
        for(Door door:doors)
        {
            if(choice==door.getDoor())
                return true;
        }
        return false;
    }

    private static void deleteChoice(int choice){
        for(Door door:doors)
        {
            if(choice==door.getDoor())
                doors.remove(door);
            return;
        }
    }

    private static void showWelcomeString(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t****SIMSY GAME****\n\n\n");
        System.out.print("\t\t\t\t\tSIMSY IS CONNECTING TO THE SERVER");

        for(int i=0;i<10;i++){

            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static  void handleResponse(){
        switch (RESPONSE){

        }
    }

    public static void main(){
        showWelcomeString();
        connect();
        startGame();
        gotoPlayer();
        pickDoor();
        if(game_report.isCONTINUES()){
            pickDoor();
        }
    }

}
