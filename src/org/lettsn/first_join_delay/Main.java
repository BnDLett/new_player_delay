package org.lettsn.first_join_delay;

import mindustry.mod.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends Plugin {
    public static Map<String, Long> firstTimePlayerMap = new HashMap<>();
    public static String pluginMessageName = "[accent][[gray]New Player Action Delay[accent]]";

    @Override
    public void init() {
        Events.load();
    }
}
