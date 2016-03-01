package test.wn.core;

import org.springframework.web.context.WebApplicationContext;
import yao.springmvc.WebApplicationContextInitializer;
import yao.util.init.Initer;
import yao.util.log.Console;

import javax.servlet.ServletContext;

public class InitListener implements WebApplicationContextInitializer {

	private Initer initer;

	@Override
	public void beforeWebApplicationContextInitialized(ServletContext servletContext) {
		Console.info(this, "Start system...");
		try {
			Console.info(this, "Init config...");

			//user数据库
			TestConf.init();

		} catch (Exception e) {
			Console.error(this, "Start error：", e);
			throw new RuntimeException(e);
		}
		initer = new Initer(this);

		initer.registTool(TestDB.TestDB());

		try {
			initer.init();
		} catch (Exception e) {
			Console.error(this, "Start error：", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void afterWebApplicationContextDestroy() {
		Console.info(this, "Stop system...");

		//关闭数据库、redis连接池
		initer.shutdown();
	}

	@Override
	public void afterWebApplicationContextInitialized(WebApplicationContext webApplicationContext) {

	}

}
