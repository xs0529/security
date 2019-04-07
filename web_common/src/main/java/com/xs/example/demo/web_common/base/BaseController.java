package com.xs.example.demo.web_common.base;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xs.example.demo.web_common.pojo.vo.Result;
import com.xs.example.demo.web_common.util.ValidUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author xieshuang
 */
public abstract class BaseController<E extends Serializable> {

    @Autowired
    public abstract IService<E> getService();

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<E> get(@PathVariable Long id){
        E entity = getService().getById(id);
        return Result.ok(entity);
    }

    @GetMapping()
    @ApiOperation(value = "获取全部数据")
    public Result<List<E>> all(){
        List<E> list = getService().list();
        return Result.ok(list);
    }

    @GetMapping("page")
    @ApiOperation(value = "获取分页数据")
    public Result<IPage<E>> page(@RequestParam(value = "0") Integer page, @RequestParam(value = "10") Integer size){
        IPage<E> iPage = getService().page(new Page<>(page, size));
        return Result.ok(iPage);
    }

    @PostMapping()
    @ApiOperation(value = "新增数据")
    public Result<E> save(@Valid @ModelAttribute E entity, BindingResult result){
        if (result.hasErrors()){
            return ValidUtil.VaildMessage(result);
        }
        boolean save = getService().save(entity);
        if (save){
            return Result.ok(entity);
        }else {
            return Result.fail();
        }
    }

    @PutMapping
    @ApiOperation(value = "更新数据")
    public Result<E> update(@Valid @ModelAttribute E entity, BindingResult result){
        if (result.hasErrors()){
            return ValidUtil.VaildMessage(result);
        }
        boolean b = getService().updateById(entity);
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "通过id删除")
    public Result del(@PathVariable Long id){
        boolean b = getService().removeById(id);
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @DeleteMapping(value = "dels/{id}")
    @ApiOperation(value = "批量通过id删除")
    public Result dels(@PathVariable Long[] ids){
        boolean b = getService().removeByIds(Arrays.asList(ids));
        if (b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}
