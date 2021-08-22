package com.syz.eduservice.service;

import com.syz.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syz.eduservice.entity.vo.ChapterQuery;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterQuery> getChapterAndVideo(String courseId);

    Boolean removeChapterById(String chapterId);
}
