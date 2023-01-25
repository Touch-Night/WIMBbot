package com.touchnight;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;

import java.io.IOException;

public final class WhereIsMyBrain extends JavaPlugin {
    public static final WhereIsMyBrain INSTANCE = new WhereIsMyBrain();

    private WhereIsMyBrain() {
        super(new JvmPluginDescriptionBuilder("com.touchnight.wimb", "0.1.0")
                .name("Where Is My Brain")
                .info("给选手加减分")
                .author("TouchNight")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
        try {
            GlobalEventChannel.INSTANCE.registerListenerHost(new WIMBListener());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}