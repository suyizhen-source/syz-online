package com.syz.eduservice.service;

import com.syz.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syz.eduservice.entity.vo.SubjectNestedQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-27
 */
public interface EduSubjectService extends IService<EduSubject> {

    void importSubjectData(MultipartFile file,EduSubjectService eduSubjectService);

    List<SubjectNestedQuery> getAllSubject();
}
