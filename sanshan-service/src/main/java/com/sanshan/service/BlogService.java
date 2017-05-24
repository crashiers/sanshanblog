package com.sanshan.service;

import com.sanshan.service.editor.MarkDownBlogService;
import com.sanshan.service.editor.UeditorBlogService;
import com.sanshan.service.vo.BlogVO;
import com.sanshan.util.BlogIdGenerate;
import com.sanshan.util.info.BlogOperationState;
import com.sanshan.util.info.EditorTypeEnum;
import com.sanshan.util.info.SanShanBlogInfoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Service在新增Editor时需要修改
 */
@Service
public class BlogService {
    @Autowired
    MarkDownBlogService markDownBlogService;

    @Autowired
    UeditorBlogService uEditorBlogService;

    @Autowired
    BlogIdGenerate blogIdGenerate;

    public BlogVO getBlog(Long id) {
        EditorTypeEnum type = blogIdGenerate.getType(id);
        BlogVO blog = null;
        switch (type) {
            case UEDITOR_EDITOR:
                blog = new BlogVO(uEditorBlogService.queryDtoById(id));
                break;
            case MarkDown_EDITOR:
                blog = new BlogVO(markDownBlogService.queryDtoById(id));
                break;
        }
        return blog;
    }


    public BlogOperationState removeBlog(Long id) {
        EditorTypeEnum type = blogIdGenerate.getType(id);
        blogIdGenerate.remove(id);
        BlogVO blog = null;
        switch (type) {
            case UEDITOR_EDITOR:
                uEditorBlogService.deleteDOById(id);
                break;
            case MarkDown_EDITOR:
                markDownBlogService.deleteDOById(id);
                break;
        }
        return new BlogOperationState(200, SanShanBlogInfoEnum.DELETE_SUCCESS.getValue());
    }

    public List<BlogVO> queryAll() {
        List<BlogVO> blogs = new ArrayList<BlogVO>();
        int size = blogIdGenerate.getId();
        for (int i = 1; i <= size; i++) {
            blogs.add(getBlog((long) i));
        }
        return blogs;
    }


}


