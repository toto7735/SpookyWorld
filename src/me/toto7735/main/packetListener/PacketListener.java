package me.toto7735.main.packetListener;

import io.netty.channel.*;
import me.toto7735.main.SpookyWorld;
import me.toto7735.main.sounds.SpookySounds;
import net.minecraft.network.protocol.game.PacketPlayOutNamedSoundEffect;
import net.minecraft.sounds.SoundEffects;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Field;

public class PacketListener implements Listener {

    public PacketListener() {
        Bukkit.getPluginManager().registerEvents(this, SpookyWorld.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.basicInject(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.unBasicInject(event.getPlayer());
    }


    private void basicInject(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                if (packet instanceof PacketPlayOutNamedSoundEffect) {
                    PacketPlayOutNamedSoundEffect clazz = ((PacketPlayOutNamedSoundEffect) packet);
                    Field field = PacketPlayOutNamedSoundEffect.class.getDeclaredField("b");
                    field.setAccessible(true);
                    if (field.get(clazz).equals(SoundEffects.vH)) {
                        new SpookySounds(player).playSpookyZombieSound();
                        return;
                    }
                    // todo add more sounds; toto7735 will do it
                }
                super.write(channelHandlerContext, packet, channelPromise);
            }
        };
        ChannelPipeline channelPipeline = ((CraftPlayer) player).getHandle().b.a().k.pipeline();
        channelPipeline.addBefore("packet_handler", "Spooky" + player.getName(), channelDuplexHandler);
    }

    private void unBasicInject(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().b.a().k;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove("Spooky" + player.getName());
            return null;
        });
    }
}
