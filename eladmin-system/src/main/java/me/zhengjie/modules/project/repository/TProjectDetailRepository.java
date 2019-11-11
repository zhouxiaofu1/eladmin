package me.zhengjie.modules.project.repository;

import me.zhengjie.modules.project.domain.TProjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zzh
* @date 2019-11-08
*/
public interface TProjectDetailRepository extends JpaRepository<TProjectDetail, Integer>, JpaSpecificationExecutor<TProjectDetail> {
}