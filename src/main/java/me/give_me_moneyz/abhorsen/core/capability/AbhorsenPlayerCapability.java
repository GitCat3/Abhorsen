package me.give_me_moneyz.abhorsen.core.capability;

import me.give_me_moneyz.abhorsen.Abhorsen;
import me.give_me_moneyz.abhorsen.core.enums.PlayerPossibleCapabilities;
import me.give_me_moneyz.abhorsen.core.interfaces.IAbhorsenCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AbhorsenPlayerCapability implements IAbhorsenCapability {
    @CapabilityInject(AbhorsenPlayerCapability.class)
    public static Capability<AbhorsenPlayerCapability> CAPABILITY = null;

    private PlayerPossibleCapabilities abilities = PlayerPossibleCapabilities.NONE;
    private PlayerEntity player;

    public AbhorsenPlayerCapability() {
    }

    public void setPlayer(PlayerEntity playerIn)
    {
        this.player = playerIn;
    }

    public static void register()
    {
        CapabilityManager.INSTANCE.register(AbhorsenPlayerCapability.class, new Capability.IStorage<AbhorsenPlayerCapability>()
        {
            @Nullable
            @Override
            public INBT writeNBT(Capability<AbhorsenPlayerCapability> capability, AbhorsenPlayerCapability instance, Direction side)
            {
                throw new IllegalStateException("This capability is not serializable.");
            }

            @Override
            public void readNBT(Capability<AbhorsenPlayerCapability> capability, AbhorsenPlayerCapability instance, Direction side, INBT nbt)
            {
                throw new IllegalStateException("This capability is not serializable.");
            }
        }, AbhorsenPlayerCapability::new);

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, AbhorsenPlayerCapability::attachCapability);
    }

    private static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(Abhorsen.MOD_ID, "abhorsencap"), new ICapabilityProvider() {
                final LazyOptional<AbhorsenPlayerCapability> supplier = LazyOptional.of(() -> {
                    AbhorsenPlayerCapability inst = new AbhorsenPlayerCapability();
                    inst.setPlayer((PlayerEntity) event.getObject());
                    return inst;
                });

                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                    if(cap == CAPABILITY) {
                        return supplier.cast();
                    }
                    return LazyOptional.empty();
                }
            });
        }
    }

    @Override
    public PlayerPossibleCapabilities getAbilities() {
        return this.abilities;
    }

    @Override
    public void setAbilities(PlayerPossibleCapabilities ability) {
        this.abilities = ability;
    }
}
