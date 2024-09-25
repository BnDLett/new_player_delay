package org.lettsn.first_join_delay;

import mindustry.mod.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends Plugin {
    public static Map<String, Long> firstTimePlayerMap = new HashMap<>();

    @Override
    public void init() {
        Events.load();
    }
}
