package com.dyonovan.neotech.client.ingameconfig;

import com.dyonovan.neotech.lib.Reference;
import com.dyonovan.neotech.registries.ConfigRegistry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This file was created for NeoTech
 * <p>
 * NeoTech is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 3/17/2016
 */
public class GuiConfigNeoTech extends GuiConfig {

    public GuiConfigNeoTech(GuiScreen parent) {
        super(parent, getConfigElements(), Reference.MOD_ID(), false, false, "NeoTech Config Options");
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();

        list.add(categoryElement(Reference.CONFIG_CLIENT(), "Client Side Config", "neotech.guiconfig.client"));
        list.add(categoryElement(Reference.CONFIG_WORLD(), "World", "neotech.guiconfig.world"));

        return list;
    }

    private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
                new ConfigElement(ConfigRegistry.config().getCategory(category.toLowerCase())).getChildElements());
    }
}
