package com.alichan.hostnavi.admin.domain.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.InnViewMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnView;
import com.alichan.hostnavi.admin.infrastracture.model.generated.InnViewExample;

@Component
public class InnViewLogic {
  @Autowired
  InnViewMapper innViewMapper;

  public void deleteViewsByInnId(long innId) {
    List<InnView> views = getViewByInnId(innId);

    views.forEach(view -> {
      deleteViewnWithoutResponseData(view.getId());
    });
  }

  public List<InnView> getViewByInnId(long innId) {
    InnViewExample innViewExample = new InnViewExample();
    innViewExample.createCriteria().andInnIdEqualTo(innId).andDeleteFlagEqualTo(false);
    return innViewMapper.selectByExample(innViewExample);
  }

  public void deleteViewnWithoutResponseData(long id) {
    InnView deletedView = createDeletedModel(id);
    innViewMapper.updateByPrimaryKeySelective(deletedView);
  }

  public InnView createDeletedModel(long id) {
    InnView innView = new InnView();

    innView.setId(id);
    innView.setDeleteFlag(true);

    return innView;
  }
}
