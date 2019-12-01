package com.mkl.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mkl.gmall.pms.entity.Brand;
import com.mkl.gmall.pms.mapper.BrandMapper;
import com.mkl.gmall.pms.service.BrandService;
import com.mkl.gmall.vo.PageInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@com.alibaba.dubbo.config.annotation.Service
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Autowired
    BrandMapper brandMapper;
    @Override
    public PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Brand> name1=null;
        if(!StringUtils.isEmpty(keyword)){
            name1=new QueryWrapper<Brand>().like("name",keyword);
        }
        IPage<Brand> brandIPage = brandMapper.selectPage(new Page<Brand>(pageNum.longValue(), pageSize.longValue()), name1);
        PageInfoVo pageInfoVo=new PageInfoVo(brandIPage.getTotal(),brandIPage.getPages(),pageSize.longValue(),brandIPage.getRecords(),brandIPage.getCurrent());
        return pageInfoVo;
    }
}
