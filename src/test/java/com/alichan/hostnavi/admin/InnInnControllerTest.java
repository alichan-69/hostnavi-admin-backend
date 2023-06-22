package com.alichan.hostnavi.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@AutoConfigureMockMvc
@SpringBootTest(classes = {HostNaviAdminApplication.class})
public class InnInnControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private Util util;

  private final String rootPath1 = "src/test/resources/";
  private final String rootPath2 = "com/alichan/hostnavi/admin/";
  private final String createTest1FilePath = "innInn/create/test1/";
  private final String deleteTest1FilePath = "innInn/delete/test1/";
  private final String getTest1FilePath = "innInn/get/test1/";
  private final String getTest2FilePath = "innInn/get/test2/";
  private final String getTest3FilePath = "innInn/get/test3/";
  private final String updateTest1FilePath = "innInn/update/test1/";

  @Test
  @ExpectedDatabase(value = createTest1FilePath + "database/expected",
      assertionMode = DatabaseAssertionMode.NON_STRICT)
  public void createTest1() throws Exception {
    util.setUpDatabase(rootPath1 + rootPath2 + createTest1FilePath + "database/setUp");

    String requestParam =
        util.convertJsonFileToString(rootPath2 + createTest1FilePath + "requestParam.json");
    String responseData =
        util.convertJsonFileToString(rootPath2 + createTest1FilePath + "responseData.json");

    mockMvc.perform(post("/inns").contentType(MediaType.APPLICATION_JSON).content(requestParam))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(responseData, false));
  }

  @Test
  @ExpectedDatabase(value = deleteTest1FilePath + "database/expected",
      assertionMode = DatabaseAssertionMode.NON_STRICT)
  public void deleteTest1() throws Exception {
    util.setUpDatabase(rootPath1 + rootPath2 + deleteTest1FilePath + "database/setUp");

    String responseData =
        util.convertJsonFileToString(rootPath2 + deleteTest1FilePath + "responseData.json");

    mockMvc.perform(delete("/inns/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(responseData, false));
  }

  @Test
  public void getTest1() throws Exception {
    util.setUpDatabase(rootPath1 + rootPath2 + getTest1FilePath + "database/setUp");

    String responseData =
        util.convertJsonFileToString(rootPath2 + getTest1FilePath + "responseData.json");

    mockMvc.perform(get("/inns?page-number=1&page-size=1").contentType(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(responseData, false));
  }

  @Test
  public void getTest2() throws Exception {
    util.setUpDatabase(rootPath1 + rootPath2 + getTest2FilePath + "database/setUp");

    String responseData =
        util.convertJsonFileToString(rootPath2 + getTest2FilePath + "responseData.json");

    mockMvc.perform(get("/inns/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(responseData, false));
  }

  @Test
  public void getTest3() throws Exception {
    util.setUpDatabase(rootPath1 + rootPath2 + getTest3FilePath + "database/setUp");

    String responseData =
        util.convertJsonFileToString(rootPath2 + getTest3FilePath + "responseData.json");

    mockMvc.perform(get("/inns/all").contentType(MediaType.APPLICATION_JSON)).andDo(print())
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(responseData, false));
  }

  @Test
  @ExpectedDatabase(value = updateTest1FilePath + "database/expected",
      assertionMode = DatabaseAssertionMode.NON_STRICT)
  public void updateTest1() throws Exception {
    util.setUpDatabase(rootPath1 + rootPath2 + updateTest1FilePath + "database/setUp");

    String requestParam =
        util.convertJsonFileToString(rootPath2 + updateTest1FilePath + "requestParam.json");

    String responseData =
        util.convertJsonFileToString(rootPath2 + updateTest1FilePath + "responseData.json");

    mockMvc.perform(put("/inns/1").contentType(MediaType.APPLICATION_JSON).content(requestParam))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(responseData, false));
  }
}
