package com.mcp.host.mcp_host.tools;

import com.mcp.host.mcp_host.model.MCPResponse;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class FileManager {

    // function to dispatch the tool according to action
    public static MCPResponse handleAction(String action, Map<String,String> parameters){
        switch (action){
            case "listFiles":
                return listFiles(parameters.get("path"));
            case "createFile":
                return createFile(parameters.get("path"),parameters.get("fileName"));
            case "deleteFiles":
                return deleteFile(parameters.get("path"));
            case "openFile":
                return openFile(parameters.get("path"));
            default:
                return new MCPResponse("error","Unknown File Manager Action");
        }
    }

    //tool to create the file
    private static MCPResponse createFile(String path,String fileName){
        try {
            File folder=new File(path);
            if(!folder.exists() || !folder.isDirectory()){
                return new MCPResponse("error","no such path exists");
            }
            File file=new File(path,fileName);
            if(file.exists()){
                return new MCPResponse("error","File Already exists");
            }
            if(file.createNewFile()){
                return new MCPResponse("success","File creation Successful");
            }
            else return new MCPResponse("error","File Creation Failed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // tool to the open the file
    private static MCPResponse openFile(String path){
        try {
            File file=new File(path);
            if(!file.exists()){
                return new MCPResponse("error","No file found");
            }
            if(!Desktop.isDesktopSupported()){
                return new MCPResponse("error","desktop operations are not supported");
            }
            Desktop desktop=Desktop.getDesktop();
            if(!desktop.isSupported(Desktop.Action.OPEN)){
                return new MCPResponse("error","fileopening is not supported");
            }
            desktop.open(file);
            return new MCPResponse("success","File Launch Successful ");
        } catch (IOException e) {
            return new MCPResponse("error","File launch failed with "+e.getMessage());
        }

    }

    // tool the list all the files in the given directory
    private static MCPResponse listFiles(String path){
        File folder=new File(path);
        if(!folder.exists() || !folder.isDirectory()){
            return new MCPResponse("error","Directory not found");
        }
        String[] files= folder.list();
        return new MCPResponse("success", Arrays.toString(files));
    }

    // tool to delete the requested file path
    private static MCPResponse deleteFile(String path){
        File file=new File(path);
        if(!file.exists()){
            return new MCPResponse("error","File doesn't exists");
        }
        if(file.delete()){
            return new MCPResponse("success","File deletion Successful");
        }
        else{
            return new MCPResponse("error","File deletion failed");
        }
    }
}
