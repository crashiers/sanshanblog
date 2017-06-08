package com.sanshan.service.editor.CacheService;

import com.github.pagehelper.PageInfo;
import com.sanshan.pojo.entity.UEditorBlogDO;
import com.sanshan.service.BaseServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DO存到缓存中
 */
@Service
public class UEditorBlogCacheService extends BaseServiceImpl<UEditorBlogDO> {

    @Cacheable(value = {"ueditor-blog"})
    @Override
    public List<UEditorBlogDO> queryAll() {
        return super.queryAll();
    }



    @Cacheable({"ueditor-blog"})
    @Override
    public List<UEditorBlogDO> queryListByWhere(UEditorBlogDO example) {
        return super.queryListByWhere(example);
    }


    @Cacheable(value = {"ueditor-blog"},key = "#a0")
    @Override
    public UEditorBlogDO queryById(Long id) {
        return super.queryById(id);
    }


    @Cacheable({"ueditor-blog"})
    @Override
    public PageInfo<UEditorBlogDO> queryPageListByWhere(UEditorBlogDO example, Integer page, Integer rows) {
        return super.queryPageListByWhere(example, page, rows);
    }


    @Override
    public Integer save(UEditorBlogDO uEditorBlog) {
        return super.save(uEditorBlog);
    }


    @CacheEvict(value = {"ueditor-blog"}, key = "#a0",beforeInvocation = true)
    @Override
    public Integer deleteById(Long id) {
        return super.deleteById(id);
    }


    @CachePut(value = {"ueditor-blog"},key = "#a0.id")
    @Override
    public UEditorBlogDO update(UEditorBlogDO uEditorBlogDO) {
        return super.update(uEditorBlogDO);
    }


    @CachePut(value = {"ueditor-blog"},key = "#a0.id")
    public UEditorBlogDO updateSelective(UEditorBlogDO uEditorBlogDO) {
        super.updateSelective(uEditorBlogDO);
        //cache注解是通过切面实现的 调用同一类中的方法不会用到缓存 直接访问数据库获取
        return queryById(uEditorBlogDO.getId());
    }

}
