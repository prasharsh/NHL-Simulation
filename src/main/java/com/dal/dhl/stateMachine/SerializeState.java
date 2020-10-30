package com.dal.dhl.stateMachine;


import java.io.FileWriter;

import com.google.gson.Gson;

import g4dhl.IGame;

public class SerializeState {

    public void exportGameToJSON(IGame game, String filePath) {
        FileWriter fileWriter = null;
        try {
            Gson gson = new Gson();
            fileWriter = new FileWriter(filePath);
            String gameStateObject = gson.toJson(game);
            fileWriter.write(gameStateObject);
            System.out.println("Successfully exported current state of Game as JSON into a file.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
