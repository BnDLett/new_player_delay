package org.lettsn.first_join_delay;

import mindustry.gen.Player;
import mindustry.mod.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends Plugin {
    public static Map<String, Long> firstTimePlayerMap = new HashMap<>();
    public static String pluginMessageName = "[accent][[gray]New Player Action Delay[accent]][]";
//    public static Player pluginDummyPlayer = new PluginDummyPlayer(); // Experimental code.

    @Override
    public void init() {
//        pluginDummyPlayer.name(pluginMessageName);
        Events.load();
    }
}
