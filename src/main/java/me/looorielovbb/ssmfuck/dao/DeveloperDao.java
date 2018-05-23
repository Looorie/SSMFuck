package me.looorielovbb.ssmfuck.dao;


import me.looorielovbb.ssmfuck.model.DeveloperModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeveloperDao {

    List<DeveloperModel> getAllDevelopers();

    DeveloperModel getDeveloper(String developerId);

    boolean addDeveloper(DeveloperModel developerModel);

    //如果想传入多个参数,则需要在接口的参数上添加 @Param 注解
    boolean updateDeveloper(@Param("id") String id, @Param("name") String name);

    boolean deleteDeveloper(String id);

}
