package me.give_me_moneyz.abhorsen.core.interfaces;

import me.give_me_moneyz.abhorsen.core.enums.PlayerPossibleCapabilities;

public interface IAbhorsenCapability {
    PlayerPossibleCapabilities getAbilities();
    void setAbilities(PlayerPossibleCapabilities ability);
}
