package me.zhengjie.modules.project.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.project.domain.TProjectDetail;
import me.zhengjie.modules.project.service.dto.TProjectDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zzh
* @date 2019-11-08
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TProjectDetailMapper extends BaseMapper<TProjectDetailDTO, TProjectDetail> {

}