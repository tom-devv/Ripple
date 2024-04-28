package dev.tom.ripple.core.animation;

import dev.tom.ripple.core.RippleCore;
import dev.tom.ripple.core.utils.BlockUtils;
import lombok.NonNull;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Set;

public class ExplodeAnimation extends AbstractAnimation{

    private final Particle particle;
    private final long expandTick;
    private final int particleNumber;
    private final float expand;

    public ExplodeAnimation(float expand, long duration, long expandTick, Particle particle, int particleNumber, @NonNull World world, @NonNull Set<Location> locations) {
        super(duration, 1, world, locations);
        this.particle = particle;
        this.expandTick = expandTick;
        this.particleNumber = particleNumber;
        this.expand = expand;
    }

    @Override
    public void play() {
        setEntities(BlockUtils.locationToEntity(world, locations));
        startInternal();
        for (BlockDisplay display : entities) {
            display.setGlowing(true);
            display.setGlowColorOverride(Color.RED);
        }
        BukkitTask task = new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                // Explode
                if(i >= getDuration()){
                    for (BlockDisplay entity : entities) {
                        world.spawnParticle(particle, entity.getLocation(), particleNumber, 0.5, 0.5, 0.5, 0.1);
                    }
                    endInternal();
                    end();
                    this.cancel();
                }
                if(i >= expandTick){
                    for (BlockDisplay display : entities) {
                        if(i % 3 == 0){
                            display.setBlock(Material.SNOW_BLOCK.createBlockData());
                        } else {
                            display.setBlock(Material.STONE.createBlockData());
                        }
                        Vector3f scale = display.getTransformation().getScale().add(new Vector3f(expand, expand, expand));
                        Vector3f translate = display.getTransformation().getTranslation().add(new Vector3f(-(expand / 2), -(expand/4), -(expand / 2)));
                        Transformation transformation = new Transformation(translate, new Quaternionf(), scale,  new Quaternionf());
                        display.setTransformation(transformation);
                    }
                } else {
                    for (BlockDisplay display : entities) {
                        if(i % 10 == 0 || i % 10 == 1 || i % 10 == 2 || i % 10 == 3 || i % 10 == 4){
                            display.setBlock(Material.SNOW_BLOCK.createBlockData());
                        } else {
                            display.setBlock(Material.STONE.createBlockData());
                        }
                    }
                }
                i++;

            }
        }.runTaskTimer(RippleCore.getInstance(), 0, 1);
        setAnimationTask(task);
    }

    @Override
    public void end() {
        for (Entity entity : entities) {
            entity.remove();
        }
    }

}
