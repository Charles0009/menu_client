
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

public class example_picocli implements Runnable {

    @Parameters
    private List<String> messages;
  
    @Option(names = { "-p", "--prefix" })
    private String prefix;
  
    public void run() {
      if (messages == null || messages.size() < 1) {
        System.out.printf("Hello, Picocli %s", prefix == null ? "" : prefix);
      } else {
        for (String msg : messages) {
          System.out.printf("%s%s%n", prefix == null ? "" : prefix, msg);
        }
      }
    }
  
    public static void main22(String[] args) {
      new CommandLine(new example_picocli()).execute(args);
    }
  
  }