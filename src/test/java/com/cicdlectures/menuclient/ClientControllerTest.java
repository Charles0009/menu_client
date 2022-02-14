package com.cicdlectures.menuclient;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;

import org.json.JSONArray;
import org.json.JSONObject;
import com.cicdlectures.menuclient.controller.ClientController;

public class ClientControllerTest {
    

    CommandLine cmd;


    @Test
    @DisplayName("delete a menu")
    public void Testdelete() {
        int exitCode = cmd.execute("delete-menu", "1");
        assertEquals(200, exitCode);
    }



}
