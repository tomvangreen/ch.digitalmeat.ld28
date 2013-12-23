package ch.digitalmeat.ld28.client;

import ch.digitalmeat.ld28.ConcertSmugglers;
import ch.digitalmeat.ld28.Config;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(640, 480);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		Config gameConfig = new Config(960, 800);
		return new ConcertSmugglers(gameConfig);
	}
}