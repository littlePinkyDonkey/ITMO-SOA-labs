package andrei.teplykh.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ContextProvider {
    public Context getContext() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//        props.put(Context.PROVIDER_URL, "http-remoting://10.20.30.40:8080");


        return new InitialContext(props);
    }
}
