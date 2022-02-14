
package com.cicdlectures.menuclient.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.ArrayList;
import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.annotation.JsonTypeInfo.None;



@Command(name = "menucli", mixinStandardHelpOptions = true, version = "menucli 1.0",
        description = "")
public class ClientController implements Callable {

        //Menu server url 
        private String url_server = "https://menuserverheroku.herokuapp.com/menus";

        @Option(names = "list-menus", description = "Display all menus")
        private String liste_menus;

        // Create a new menu 
        @Option(names = "add-menu" , description = "Menu to add",split=",")   
        private String[] adding;

        // Delete a menu 
        @Option(names = "delete-menu" , description = "Menu to delete")   
        private int deleting = 0;

        // Create a new menu 
        @Option(names = "--server-url" , description = "Menu to add")   
        private String new_url;


        // Transform the input in a json string wich can be understood by httprequest module (function CreateMenu)
        public static String requete_to_string(String[] adding ){

                ArrayList<String> ingredients = new ArrayList<String>(); 
                for (int i =1 ; i<adding.length ; i++) {
                        String ing = new JSONObject().put("name", adding[i]).toString();
                        ingredients.add(ing);
                }

                String menu = new JSONObject().put("name", adding[0]).put("dishes",ingredients).toString();
                for (int i=2;i<menu.length()-2;i++){

                        char c = menu.charAt(i);char c1 = menu.charAt(i+1);char cm1 = menu.charAt(i-1);
                        if (c == '\\' || c1 == '{' || cm1 == '}'){
                                menu = menu.substring(0, i) +  menu.substring(i+1); 
                        }
                }
                System.out.println(menu);
                return menu;
        }
       
        public void createMenu () throws Exception {
                String requete = requete_to_string(adding);
                var client = HttpClient.newHttpClient();
                var request = HttpRequest.newBuilder(URI.create(this.url_server))
                        .POST(HttpRequest.BodyPublishers.ofString(requete))
                        .header("Content-type", "application/json")
                        .build();
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 201) {System.out.println("Mauvaise requête ! Format de la requete expliquée dans le README.md ");}
                else {System.out.println("Menu Ajouté !");}   
        }

        public void deleteMenu () throws Exception {
                //String menu = requete_to_string(adding);
                int id_menu2delete = this.deleting;
                System.out.println(id_menu2delete);
                var client = HttpClient.newHttpClient();
                var request = HttpRequest.newBuilder(URI.create(this.url_server + "/" + id_menu2delete))
                        .DELETE()
                        .build();
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                
                if (response.statusCode() != 200) {System.out.println("Mauvaise requête ! Format de la requete expliquée dans le README.md ");}
                else {System.out.println("Menu Retiré !");}   
        }




        public void listeMenus () throws Exception {
              
                var client = HttpClient.newHttpClient();
        
                var request = HttpRequest.newBuilder(URI.create(this.url_server ))
                        .GET()
                        .header("Content-type", "application/json")
                        .build();
             
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JSONArray array = new JSONArray(response.body());
                
                char res = liste_menus.charAt(1);
                if (res == 'a') {
                        for (int i = 0; i <  array.length(); i++) {

                                JSONObject plat = array.getJSONObject(i);
                                System.out.println( plat.getInt("id") + " - "+ plat.getString("name"));
                                JSONArray dishes = plat.getJSONArray("dishes");

                                for (int j = 0; j <  dishes.length(); j++){
                                        System.out.println( " - " + dishes.getJSONObject(j).getString("name") );
                                }
                                System.out.println( "\n ------------------ \n ");
                        }
                }
                else{ 
                        System.out.println("You have to run 'list-menus -a' to display the menus !");
                }
                
        }

        public void changeURL(){
                this.url_server = new_url ; 
                
        }

        @Override
        public Integer call() throws Exception {
                if (new_url != null){
                        this.changeURL();
                }
        
                else if (adding != null){
                        this.createMenu();
                }
                else if(deleting != 0){
                        this.deleteMenu();
                }

                else if (liste_menus != null){
                        this.listeMenus();
                }
                
                return null;
        }

        public static void main(String[] args) {
                new CommandLine(new ClientController()).execute(args);
              }

}
