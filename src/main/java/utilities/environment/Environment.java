package utilities.environment;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:environmentsConfig/${env}.properties"})
public interface Environment extends Config {

    @Key("url")
    String url();
}
