package org.lettsn.first_join_delay.server;

import mindustry.net.Administration;

public class Configs {
    Administration.Config delayMinutes;

    public Configs() {
        delayMinutes = new Administration.Config("delay-minutes", "The spawn delay for new players" +
                " in minutes.", 5);
    }

    public Integer getDelayMinutes() {
        return this.delayMinutes.num();
    }
}
