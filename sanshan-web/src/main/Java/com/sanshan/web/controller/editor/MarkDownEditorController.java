package com.sanshan.web.controller.editor;

import com.github.pagehelper.PageInfo;
import com.sanshan.pojo.dto.MarkDownBlogDTO;
import com.sanshan.pojo.entity.MarkDownBlogDO;
import com.sanshan.service.editor.MarkDownBlogService;
import com.sanshan.service.vo.ResponseMsgVO;
import com.sanshan.util.BlogIdGenerate;
import com.sanshan.util.info.EditorTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("markdown-editor")
@RestController
public class MarkDownEditorController {

    @Autowired
    private BlogIdGenerate blogIdGenerate;

    @Autowired
    private MarkDownBlogService markDownBlogService;

    /**
     * 分页查询
     *
     * @param pagenum
     * @param pagesize
     * @return
     */
    @RequestMapping(value = "query-by-page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO queryByPage(@RequestParam("pagenum") Integer pagenum
            , @RequestParam("pagesize") Integer pagesize) {
        PageInfo<MarkDownBlogDTO> info = markDownBlogService.queryDtoPageListByWhere(null, pagenum, pagesize);
        ResponseMsgVO<PageInfo> responseMsgVO = new ResponseMsgVO<PageInfo>();
        return responseMsgVO.buildOKWithData(info);
    }


    @RequestMapping(value = "query-all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO queryAll() {
        ResponseMsgVO<List<MarkDownBlogDTO>> responseMsgVO = new ResponseMsgVO<>();
        List<MarkDownBlogDTO> list = markDownBlogService.queryDtoAll();
        return responseMsgVO.buildOKWithData(list);
    }


    @RequestMapping(value = "insert-blog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO insertMarkDownBlog(@RequestParam("id") Long id, @RequestParam("content") String content, @RequestParam("tag") String tag
            , @RequestParam("user") String user) {
        //id生成
        MarkDownBlogDO markDownBlog = new MarkDownBlogDO();
        markDownBlog.setId(id);
        markDownBlog.setContent(content);
        markDownBlog.setCreated(new Date());
        markDownBlog.setUpdated(new Date());
        markDownBlog.setTag(tag);
        markDownBlog.setTime(new Date());
        markDownBlog.setUser(user);
        blogIdGenerate.setId(markDownBlog.getId(), EditorTypeEnum.MarkDown_EDITOR);
        markDownBlog.setId(blogIdGenerate.getId());
        markDownBlogService.saveDO(markDownBlog);
        ResponseMsgVO responseMsgVO = new ResponseMsgVO().buildOK();
        return responseMsgVO;
    }


    @RequestMapping(value = "delete-by-id", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO deleteMarkDownBlog(@RequestParam("id") Long id) {
        //id去除
        blogIdGenerate.remove(id);
        markDownBlogService.deleteDOById(id);
        return new ResponseMsgVO().buildOK();
    }


    @RequestMapping(value = "update-blog-by-id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMsgVO update(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "title", required = false) String title) {
        MarkDownBlogDO markDownBlogDO = new MarkDownBlogDO();
        markDownBlogDO.setId(id);
        markDownBlogDO.setContent(content);
        markDownBlogDO.setUpdated(new Date());
        markDownBlogDO.setTag(tag);
        markDownBlogDO.setTitle(title);
        markDownBlogService.updateSelectiveDO(markDownBlogDO);
        return new ResponseMsgVO().buildOK();
    }

}

