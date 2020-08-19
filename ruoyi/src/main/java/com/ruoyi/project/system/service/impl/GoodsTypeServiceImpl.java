package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.GoodsType;
import com.ruoyi.project.system.domain.GoodsType;
import com.ruoyi.project.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.GoodsTypeMapper;
import com.ruoyi.project.system.domain.GoodsType;
import com.ruoyi.project.system.service.IGoodsTypeService;

/**
 * 商品分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService 
{
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    /**
     * 查询商品分类
     * 
     * @param goodsTypeId 商品分类ID
     * @return 商品分类
     */
    @Override
    public GoodsType selectGoodsTypeById(Integer goodsTypeId)
    {
        return goodsTypeMapper.selectGoodsTypeById(goodsTypeId);
    }
    /**
     * 查询商品分类
     *
     * @param goodsTypePid 商品分类PID
     * @return 商品分类
     */
    public List<GoodsType> selectGoodsTypeByPid(Integer goodsTypePid){
        return goodsTypeMapper.selectGoodsTypeByPid(goodsTypePid);
    }
    /**
     * 查询商品分类
     *
     * @param goodsTypeId 商品分类ID
     * @return 商品分类
     */
    @Override
    public GoodsType selectGoodsTypeByName(String name,Integer goodsTypeId)
    {
        return goodsTypeMapper.selectGoodsTypeByName(name,goodsTypeId);
    }
    /**
     * 查询商品分类
     *
     * @param pid 商品分类父ID
     * @return 商品分类
     */
    @Override
    public GoodsType checkGoodsTypeNameUnique(String name,Integer pid){
        return goodsTypeMapper.checkGoodsTypeNameUnique(name,pid);
    }
    /**
     * 构建前端所需要树结构
     *
     * @param goods 部门列表
     * @return 树结构列表
     */
    @Override
    public List<GoodsType> buildGoodsTree(List<GoodsType> goods)
    {
        List<GoodsType> returnList = new ArrayList<GoodsType>();
        List<Long> tempList = new ArrayList<Long>();
        for (GoodsType good : goods)
        {
            tempList.add(good.getGoodsTypeId().longValue());
        }
        for (Iterator<GoodsType> iterator = goods.iterator(); iterator.hasNext();)
        {
            GoodsType dept = (GoodsType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getGoodsTypePid()))
            {
                recursionFn(goods, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = goods;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param goods 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildGoodsTreeSelect(List<GoodsType> goods)
    {
        List<GoodsType> deptTrees = buildGoodsTree(goods);
        System.out.println(deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList()));
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 是否存在子节点
     *
     * @param id 分类ID
     * @return 结果
     */
    @Override
    public int hasChildGoodsTypeById(Integer id){
        return goodsTypeMapper.hasChildGoodsTypeById(id);
    }

    /**
     * 查询商品分类列表
     * 
     * @param goodsType 商品分类
     * @return 商品分类
     */
    @Override
    public List<GoodsType> selectGoodsTypeList(GoodsType goodsType)
    {
        return goodsTypeMapper.selectGoodsTypeList(goodsType);
    }

    /**
     * 新增商品分类
     * 
     * @param goodsType 商品分类
     * @return 结果
     */
    @Override
    public int insertGoodsType(GoodsType goodsType)
    {
        goodsType.setCreateTime(DateUtils.getNowDate());
        return goodsTypeMapper.insertGoodsType(goodsType);
    }

    /**
     * 修改商品分类
     * 
     * @param goodsType 商品分类
     * @return 结果
     */
    @Override
    public int updateGoodsType(GoodsType goodsType)
    {
        goodsType.setUpdateTime(DateUtils.getNowDate());
        return goodsTypeMapper.updateGoodsType(goodsType);
    }

    /**
     * 批量删除商品分类
     * 
     * @param goodsTypeIds 需要删除的商品分类ID
     * @return 结果
     */
    @Override
    public int deleteGoodsTypeByIds(Integer[] goodsTypeIds)
    {
        return goodsTypeMapper.deleteGoodsTypeByIds(goodsTypeIds);
    }

    /**
     * 删除商品分类信息
     * 
     * @param goodsTypeId 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteGoodsTypeById(Integer goodsTypeId)
    {
        return goodsTypeMapper.deleteGoodsTypeById(goodsTypeId);
    }
    /**
     * 得到子节点列表
     */
    private List<GoodsType> getChildList(List<GoodsType> list, GoodsType t)
    {
        List<GoodsType> tlist = new ArrayList<GoodsType>();
        Iterator<GoodsType> it = list.iterator();
        while (it.hasNext())
        {
            GoodsType n = (GoodsType) it.next();
            System.out.println("pid:"+n.getGoodsTypePid().longValue()+"id:"+t.getGoodsTypeId().longValue());
            System.out.println(n.getGoodsTypePid().longValue() == t.getGoodsTypeId().longValue());
            if (StringUtils.isNotNull(n.getGoodsTypePid()) && n.getGoodsTypePid().longValue() == t.getGoodsTypeId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<GoodsType> list, GoodsType t)
    {
        // 得到子节点列表
        List<GoodsType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (GoodsType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<GoodsType> it = childList.iterator();
                while (it.hasNext())
                {
                    GoodsType n = (GoodsType) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<GoodsType> list, GoodsType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
