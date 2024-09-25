package org.lettsn.first_join_delay;

import mindustry.game.EventType;
import mindustry.net.Administration;

import static mindustry.Vars.netServer;

public class Events {
    /**
     * Loads all events for the plugin.
     */
    public static void load() {
        arc.Events.on(EventType.PlayerConnect.class, event -> checkFirstJoin(event.player.uuid()));
        netServer.admins.addActionFilter(Events::checkAction);
    }

    /**
     * Checks if the player is a first time player.
     * @param event The player connect event.
     * @return A boolean value. `true` if the player is new, and `false` if the player isn't new.
     */
    public static boolean checkFirstJoin(String uuid) {
        if (Main.firstTimePlayerMap.containsKey(uuid)) {
            Long firstJoinTime = Main.firstTimePlayerMap.get(uuid);

            return (System.currentTimeMillis() - firstJoinTime) < (5 * 6000); // TODO: make this a config.
        }

        if (netServer.admins.getInfoOptional(uuid) != null) {
            return false;
        }

        Main.firstTimePlayerMap.put(uuid, System.currentTimeMillis());
        return true;
    }

    public static boolean checkAction(Administration.PlayerAction action) {
        if (!checkFirstJoin(action.player.uuid())) {
            return true;
        }

        return !(action.type == Administration.ActionType.placeBlock ||
                action.type == Administration.ActionType.breakBlock ||
                action.type == Administration.ActionType.command
        );
    }
}
