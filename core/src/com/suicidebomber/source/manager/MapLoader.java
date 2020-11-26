package com.suicidebomber.source.manager;

import com.badlogic.gdx.Gdx;
import com.suicidebomber.source.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class MapLoader {

    private static MapLoader mapLoader = null;
    private HashMap<String, Map> mapList = new HashMap<>();

    private MapLoader() {

    }

    public static MapLoader instance() {
        if (mapLoader == null) {
            mapLoader = new MapLoader();
        }
        return mapLoader;
    }

    public void create() {
        importMap("SANDSTORM", "map/Sandstorm.dll");
        importMap("LEVIATHAN", "map/Leviathan.dll");
    }

    public Map loadMap(String mapName) {
        if (mapList.containsKey(mapName)) {
            return mapList.get(mapName);
        } else {
            return null;
        }
    }

    public Map loadMap() {
        ArrayList<String> map = new ArrayList<>();
        for (String mapName : mapList.keySet()) {
            map.add(mapName);
        }
        return mapList.get(map.get(GameHelper.instance().random.nextInt(map.size())));
    }

    private void importMap(String mapName, String path) {
        try {
            Map map = new Map();
            if (!Gdx.files.internal(path).exists()) {
                System.out.println("File not found: " + path);
                return;
            }
            Scanner scan = new Scanner(Gdx.files.internal(path).read());
            int width = scan.nextInt();
            int height = scan.nextInt();
            map.setMap(width, height);
            scan.nextLine();
            for (int j = 0; j < width; j++) {
                String[] line = scan.nextLine().split(" ");
                for (int i = 0; i < height; i++) {
                    map.setBlock(i, width - 1 - j, line[i]);
                }
            }
            scan.close();
            mapList.put(mapName, map);
        } catch (Exception e) {
            System.out.println("File not found: " + path);
        }
    }
}


