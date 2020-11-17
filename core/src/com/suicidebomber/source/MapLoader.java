package com.suicidebomber.source;

import com.suicidebomber.source.sourceelement.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class MapLoader {

    public HashMap<String, Map> mapList = new HashMap<>();

    public MapLoader() {
        importMap("SANDSTORM", "core/assets/map/Sandstorm.dll");
    }

    public Map loadMap(String mapName) {
        if (mapList.containsKey(mapName)) {
            return mapList.get(mapName);
        } else {
            return null;
        }
    }

    private void importMap(String mapName, String path) {
        try {
            Map map = new Map();
            Scanner scan = new Scanner(new File(path));
            int width = scan.nextInt();
            int height = scan.nextInt();
            map.setMap(width, height);
            scan.nextLine();
            for (int j = 0; j < width; j++) {
                String[] line = scan.nextLine().split(" ");
                for (int i = 0; i < height; i++) {
                    map.setBlock(i, j, line[i]);
                }
            }
            scan.close();
            mapList.put(mapName, map);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
    }
}


