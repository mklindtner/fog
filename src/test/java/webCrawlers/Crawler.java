package webCrawlers;
import data.MySqlConnector;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;
public class Crawler
{
	@Before
	public void prepare()
	{
		setBaseUrl("http://localhost:8081");
	}
/*
	@Test
	public void testIndexpage()
	{
		beginAt("index.jsp");
	}*/
}
