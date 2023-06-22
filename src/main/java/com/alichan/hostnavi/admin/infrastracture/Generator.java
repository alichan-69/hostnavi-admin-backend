package com.alichan.hostnavi.admin.infrastracture;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {
  public static void main(String[] args) throws Exception {
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    InputStream inputStream = Generator.class.getResourceAsStream("/generatorConfig.xml");
    ConfigurationParser configurationParser = new ConfigurationParser(warnings);
    Configuration configuration = configurationParser.parseConfiguration(inputStream);
    inputStream.close();

    DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator =
        new MyBatisGenerator(configuration, defaultShellCallback, warnings);
    myBatisGenerator.generate(null);
    for (String warning : warnings) {
      System.out.println(warning);
    }
  }
}
