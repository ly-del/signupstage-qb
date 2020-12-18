package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.enums.DBStatusEnum;
import com.cb.signupstage.dto.UserGroupDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserGroup;
import com.cb.signupstage.entity.UserGroupBind;
import com.cb.signupstage.mapper.UserGroupBindMapper;
import com.cb.signupstage.mapper.UserGroupEntityMapper;
import com.cb.signupstage.mapper.UserGroupMapper;
import com.cb.signupstage.service.StudentGroupService;
import com.cb.signupstage.utils.CopyUtils;
import com.cb.signupstage.vo.UserGroupTitleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: ly
 * @time: 2020/11/14 9:50
 * @description:
 */
@Slf4j
@Service
public class StudentGroupServiceImpl extends ServiceImpl<UserGroupEntityMapper, UserGroup> implements StudentGroupService {

    @Autowired
    private UserGroupEntityMapper userGroupEntityMapper;

    @Autowired
    private UserGroupBindMapper userGroupBindMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Autowired
    private StudentGroupService studentGroupService;

    @Override
    public String createGroup(String groupName , Long parentId, Long accountId) {
      String failMsg = null;
        UserGroup userGroupEntity = new UserGroup();
        userGroupEntity.setGroupName(groupName);
        userGroupEntity.setParentId(parentId);
        List<UserGroup> selectList = getExistMsg(userGroupEntity, accountId);

        if (!CollectionUtils.isEmpty(selectList)){
            //数据存在 插入失败
            // TODO 确认
            failMsg = "创建的分组名称已经存在";
        }
        //数据不存在 就插入数据
        userGroupEntity.setCreateTime(new Date());
        userGroupEntity.setAccountId(accountId);
        userGroupEntity.setStatus(DBStatusEnum.EFFECT_STATUS.getCode());
        int count = userGroupEntityMapper.insert(userGroupEntity);
        return  failMsg ;
    }

    private List<UserGroup> getExistMsg(UserGroup userGroupEntity, Long accountId) {

        QueryWrapper<UserGroup> wrapper  = new QueryWrapper<>(userGroupEntity) ;

        List<UserGroup> selectList = userGroupEntityMapper.selectList(wrapper);
        return selectList;
    }


    @Override
    public String updateGroup(UserGroup userGroupEntity, Long accountId) {
        String fsg=null;
        //修改
        int i = userGroupEntityMapper.updateById(userGroupEntity);
        if (i < 1){
            fsg ="更新失败";
            return fsg;
        }
        return fsg;
    }

    @Override
    public String deleteGroup(Long groupId, Long accountId) {
        String failMsg = null;
        UserGroup userGroup = new UserGroup();
        userGroup.setId(groupId);
        List<UserGroup> selectList = getExistMsg(userGroup, accountId);

        if (CollectionUtils.isEmpty(selectList)){
            // TODO 确认
            failMsg = "数据源不存在";
           return failMsg;
        }
        //判断该分组下有没有 考生用户
           //查找考生是否存在
        //查询要删除的 分组及其所有的子节点  孩子们
      List<Long>  list = userGroupEntityMapper.selectAllChildrensId(userGroup.getId());
        list.add(userGroup.getId());
        //
        UserGroupBind userGroupBind = new UserGroupBind();
        QueryWrapper<UserGroupBind> wrapper = new QueryWrapper<>();
        wrapper.in("group_id",list);
        List<UserGroupBind> userGroupBinds = userGroupBindMapper.selectList(wrapper);
        if (userGroupBinds.size()==0){
            //删除
            log.info("======没有用户绑定在该组");

            LambdaUpdateWrapper<UserGroup> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.in(UserGroup::getId, list).set(UserGroup::getStatus, SignDec.STATUS_DELETED);

            userGroupMapper.update(null, lambdaUpdateWrapper);
            failMsg="删除成功";
            return failMsg;
        }

        log.info("======有用户绑定在该组,不能删除");
        failMsg="有用户绑定在该组,不能删除";
        return failMsg;
    }

    @Override
    public List<UserGroupDTO> queryGroup( Long accountId) {
        //查询所有的 分组数据
        UserGroup userGroupEntity =new UserGroup();

        userGroupEntity.setStatus(SignDec.STATUS_UN_DELETED);
        QueryWrapper<UserGroup> wrapper  = new QueryWrapper<>(userGroupEntity) ;
        List<UserGroup> selectList = userGroupEntityMapper.selectList(wrapper);
        List<UserGroupTitleVo> copy = CopyUtils.copy(selectList, UserGroupTitleVo.class);
        for (UserGroupTitleVo userGroupTitleVo : copy) {
            userGroupTitleVo.setTitle(userGroupTitleVo.getGroupName());
        }
        List<UserGroupDTO> copyList = CopyUtils.copy(copy, UserGroupDTO.class);
        return listToTree(copyList);
    }
    public static List<UserGroupDTO> listToTree( List<UserGroupDTO> list) {
        List<UserGroupDTO> treeList = new ArrayList<UserGroupDTO>();
        for (UserGroupDTO tree : list) {
            if (tree.getParentId() == 0) {
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    private static UserGroupDTO findChildren(UserGroupDTO tree, List<UserGroupDTO> list) {
        for (UserGroupDTO node : list) {
            if (node.getParentId() == tree.getId()) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<UserGroupTitleVo>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }


}
