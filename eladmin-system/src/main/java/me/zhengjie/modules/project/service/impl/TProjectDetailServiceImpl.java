package me.zhengjie.modules.project.service.impl;

import me.zhengjie.modules.project.domain.TProjectDetail;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.project.repository.TProjectDetailRepository;
import me.zhengjie.modules.project.service.TProjectDetailService;
import me.zhengjie.modules.project.service.dto.TProjectDetailDTO;
import me.zhengjie.modules.project.service.dto.TProjectDetailQueryCriteria;
import me.zhengjie.modules.project.service.mapper.TProjectDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author Zzh
* @date 2019-11-08
*/
@Service
@CacheConfig(cacheNames = "tProjectDetail")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TProjectDetailServiceImpl implements TProjectDetailService {

    private final TProjectDetailRepository tProjectDetailRepository;

    private final TProjectDetailMapper tProjectDetailMapper;

    public TProjectDetailServiceImpl(TProjectDetailRepository tProjectDetailRepository, TProjectDetailMapper tProjectDetailMapper) {
        this.tProjectDetailRepository = tProjectDetailRepository;
        this.tProjectDetailMapper = tProjectDetailMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(TProjectDetailQueryCriteria criteria, Pageable pageable){
        Page<TProjectDetail> page = tProjectDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tProjectDetailMapper::toDto));
    }

    @Override
    @Cacheable
    public List<TProjectDetailDTO> queryAll(TProjectDetailQueryCriteria criteria){
        return tProjectDetailMapper.toDto(tProjectDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public TProjectDetailDTO findById(Integer projectId) {
        TProjectDetail tProjectDetail = tProjectDetailRepository.findById(projectId).orElseGet(TProjectDetail::new);
        ValidationUtil.isNull(tProjectDetail.getProjectId(),"TProjectDetail","projectId",projectId);
        return tProjectDetailMapper.toDto(tProjectDetail);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TProjectDetailDTO create(TProjectDetail resources) {
        return tProjectDetailMapper.toDto(tProjectDetailRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TProjectDetail resources) {
        TProjectDetail tProjectDetail = tProjectDetailRepository.findById(resources.getProjectId()).orElseGet(TProjectDetail::new);
        ValidationUtil.isNull( tProjectDetail.getProjectId(),"TProjectDetail","id",resources.getProjectId());
        tProjectDetail.copy(resources);
        tProjectDetailRepository.save(tProjectDetail);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer projectId) {
        tProjectDetailRepository.deleteById(projectId);
    }


    @Override
    public void download(List<TProjectDetailDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TProjectDetailDTO tProjectDetail : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("项目名称", tProjectDetail.getProjectName());
            map.put("省份", tProjectDetail.getProvince());
            map.put("城市", tProjectDetail.getCity());
            map.put("地址", tProjectDetail.getAddress());
            map.put("开始时间", tProjectDetail.getStartTime());
            map.put("结束时间", tProjectDetail.getEndTime());
            map.put("出差时长", tProjectDetail.getDuration());
            map.put("预算", tProjectDetail.getBudget());
            map.put("总报销金额", tProjectDetail.getAggregateAmount());
            map.put("总补贴金额", tProjectDetail.getSubsidyAmount());
            map.put("报销状态 0未报销，1已报销", tProjectDetail.getAccountState());
            map.put("报销时间", tProjectDetail.getAccountTime());
            map.put("补贴状态 0未报销，1已报销", tProjectDetail.getSubsidyState());
            map.put("补贴报销时间", tProjectDetail.getSubsidyName());
            map.put("创建人", tProjectDetail.getCreatedUser());
            map.put("创建时间", tProjectDetail.getCreatedTime());
            map.put("更新人", tProjectDetail.getUpdatedUser());
            map.put("更新时间", tProjectDetail.getUpdatedTime());
            map.put("差旅状态 0生效，1结束", tProjectDetail.getState());
            map.put("是否有效 0有效，1无效", tProjectDetail.getVaild());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}