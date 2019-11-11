package me.zhengjie.modules.project.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.project.domain.TProjectDetail;
import me.zhengjie.modules.project.service.TProjectDetailService;
import me.zhengjie.modules.project.service.dto.TProjectDetailQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Zzh
* @date 2019-11-08
*/
@Api(tags = "TProjectDetail管理")
@RestController
@RequestMapping("/api/tProjectDetail")
public class TProjectDetailController {

    private final TProjectDetailService tProjectDetailService;

    public TProjectDetailController(TProjectDetailService tProjectDetailService) {
        this.tProjectDetailService = tProjectDetailService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tProjectDetail:list')")
    public void download(HttpServletResponse response, TProjectDetailQueryCriteria criteria) throws IOException {
        tProjectDetailService.download(tProjectDetailService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询TProjectDetail")
    @ApiOperation("查询TProjectDetail")
    @PreAuthorize("@el.check('tProjectDetail:list')")
    public ResponseEntity getTProjectDetails(TProjectDetailQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tProjectDetailService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增TProjectDetail")
    @ApiOperation("新增TProjectDetail")
    @PreAuthorize("@el.check('tProjectDetail:add')")
    public ResponseEntity create(@Validated @RequestBody TProjectDetail resources){
        return new ResponseEntity<>(tProjectDetailService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改TProjectDetail")
    @ApiOperation("修改TProjectDetail")
    @PreAuthorize("@el.check('tProjectDetail:edit')")
    public ResponseEntity update(@Validated @RequestBody TProjectDetail resources){
        tProjectDetailService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{projectId}")
    @Log("删除TProjectDetail")
    @ApiOperation("删除TProjectDetail")
    @PreAuthorize("@el.check('tProjectDetail:del')")
    public ResponseEntity delete(@PathVariable Integer projectId){
        tProjectDetailService.delete(projectId);
        return new ResponseEntity(HttpStatus.OK);
    }
}