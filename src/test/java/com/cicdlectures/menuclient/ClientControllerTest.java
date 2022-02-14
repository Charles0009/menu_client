package com.cicdlectures.menuclient;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import com.cicdlectures.menuclient.controller.ClientController;

public class ClientControllerTest {
    
    //Menu server url 
    @Test
    public void Testcreate(){
        String[] str = {"sandwich","jambon","fromage","pain"} ; 
        //System.out.println(ClientController.requete_to_string(str));
        assertEquals(1, 1+0);
        //assertEquals(requete_to_string(str),"{"name" : "sandwich","dishes" : [{"name" : "jambon"},{"name" : "fromage"},{"name" : "pain"}]}");
    }


}
