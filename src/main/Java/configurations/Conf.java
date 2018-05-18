package configurations;


import java.io.IOException;
import java.util.logging.*;

public class Conf
{
	private static Logger LOGGER;

	public static final boolean ISPRODUCTION  = false;

	//for local
	public static final String  LOGFILEPATH = "/home/mkl/.IntelliJIdea2017.3/system/tomcat/Unnamed_maven_fog/logs/maven_fog.txt";


	public static Logger getLogger() {
		if(LOGGER == null) {
			//LOGGER =  Logger.getLogger("");
			LOGGER = Logger.getLogger(Conf.class.getName());
		}
		addHandlers();
		return LOGGER;
	}

	private static void addHandlers()
	{
		LOGGER.addHandler(new ConsoleHandler());
		VerySimpleFomarter vsf = new VerySimpleFomarter();
		try {
			if (Conf.ISPRODUCTION) {
				FileHandler handler = new FileHandler(Conf.LOGFILEPATH);
				handler.setFormatter(vsf);
				LOGGER.addHandler(handler);
			} else {
				FileHandler handler = new FileHandler("/home/mkl/java/2semester/maven_fog/logs/devLogs/dev-log.%u.%g.log");
				handler.setFormatter(vsf);
				LOGGER.addHandler(handler);
			}
		} catch(IOException IOException)
		{
		}
	}
}
