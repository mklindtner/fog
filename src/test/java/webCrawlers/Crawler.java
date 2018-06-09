package webCrawlers;
import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;
public class Crawler
{
	@Before
	public void prepare()
	{
		setBaseUrl("http://localhost:8081");
	}

	@Test
	public void testIndexpage()
	{
		beginAt("index.jsp");
	}
}
