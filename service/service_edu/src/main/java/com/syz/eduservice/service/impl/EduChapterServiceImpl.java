package com.syz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syz.eduservice.entity.EduChapter;
import com.syz.eduservice.entity.EduVideo;
import com.syz.eduservice.entity.vo.ChapterQuery;
import com.syz.eduservice.entity.vo.VideoQuery;
import com.syz.eduservice.mapper.EduChapterMapper;
import com.syz.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syz.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    EduVideoService eduVideoService;

    @Override
    public List<ChapterQuery> getChapterAndVideo(String courseId) {
        //根据课程ID获取章节信息
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterQueryWrapper);
        //根据课程ID获取课时信息
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> videoList = eduVideoService.list(videoQueryWrapper);
        //创建封装结果List
        List<ChapterQuery> chapterQueryList = new ArrayList<>();
        //遍历查询章节list进行封装
        for (int i = 0; i < chapterList.size(); i++) {
            ChapterQuery chapterQuery = new ChapterQuery();
            BeanUtils.copyProperties(chapterList.get(i),chapterQuery);
            chapterQueryList.add(chapterQuery);
            //遍历查询小节list进行封装
            List<VideoQuery> videoQueryList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                if (videoList.get(j).getChapterId().equals(chapterQuery.getId())){
                    VideoQuery videoQuery = new VideoQuery();
                    BeanUtils.copyProperties(videoList.get(j),videoQuery);
                    videoQueryList.add(videoQuery);
                }
            }
            chapterQuery.setChildren(videoQueryList);
        }
        return chapterQueryList;
    }
}
