package com.example;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;

@Slf4j
@PluginDescriptor(
		name = "Nightmare Fade Remover",
		description="Removes the pink fade when Nightmare shuffles your prayers",
		tags= {"nightmare", "phosani"}
)
public class NightmareFadeRemoverPlugin extends Plugin
{
	@Inject
	public Client client;

	private boolean isInNightmareRegion()
	{
		return WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation()).getRegionID()==15515;
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		hideFade(isInNightmareRegion());
	}

	protected void hideFade(boolean hide)
	{
		Widget fade = client.getWidget(303, 1);
		if (fade != null)
		{
			fade.setHidden(hide);
		}
	}
}
