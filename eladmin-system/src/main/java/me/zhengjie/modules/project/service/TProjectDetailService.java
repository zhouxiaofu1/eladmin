package me.zhengjie.modules.project.service;

import me.zhengjie.modules.project.domain.TProjectDetail;
import me.zhengjie.modules.project.service.dto.TProjectDetailDTO;
import me.zhengjie.modules.project.service.dto.TProjectDetailQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zzh
* @date 2019-11-08
*/
public interface TProjectDetailService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TProjectDetailQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TProjectDetailDTO>
    */
    List<TProjectDetailDTO> queryAll(TProjectDetailQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param projectId ID
     * @return TProjectDetailDTO
     */
    TProjectDetailDTO findById(Integer projectId);

    TProjectDetailDTO create(TProjectDetail resources);

    void update(TProjectDetail resources);

    void delete(Integer projectId);

    void download(List<TProjectDetailDTO> all, HttpServletResponse response) throws IOException;
}