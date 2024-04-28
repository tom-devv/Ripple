package dev.tom.ripple.example;

import dev.tom.ripple.bukkit.Ripple;
import dev.tom.ripple.core.animation.AbstractAnimation;
import dev.tom.ripple.core.utils.BlockUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class ExampleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(commandSender instanceof Player){

            Player player = (Player) commandSender;
            Set<Location> locations = BlockUtils.getNearbyLocations(player.getLocation(), 2);
            if(locations.isEmpty()) return false;
            AbstractAnimation animation = Ripple.getAnimationFactory().initializeExplodeAnimation(0.25F, 80, 76, Particle.EXPLOSION_HUGE, 100, player.getWorld(), locations);
            animation.play();
        }

        return true;
    }

}

