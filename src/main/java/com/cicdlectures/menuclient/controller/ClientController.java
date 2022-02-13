
package com.cicdlectures.menuclient.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

@Command(name = "menucli", mixinStandardHelpOptions = true, version = "menucli 1.0",
        description = "")
public class ClientController implements Runnable {

        @Option(names = { "list-menus" })
        private String liste_menus;

        public void run() {
                System.out.println(liste_menus);  
              }
        public static void main(String[] args) {
                new CommandLine(new ClientController()).execute(args);
              }

//         public static void main(String args[])  //static method  
//         {  
//         System.out.println("Static method");  
//         System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");  

// }  

}

