package com.lyh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.lyh.bean.Tree;
import com.lyh.bean.User;
import com.lyh.util.MyUtil;


@Service
public class PublicServiceImp implements PublicServiceInf{
	@Autowired
	UserServiceInf userServiceInf;
	@Autowired
	TreeServiceInf treeServiceInf;

	@Override
	public void addLeftMenu(ModelAndView mav) {
			
		mav.addObject("leftPage", "/admin/common/left_menu.jsp");
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		currentUser = userServiceInf.findById(currentUser.getId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		String menuIds = currentUser.getMenuIds();
		
		if(menuIds==null){
			menuIds = "";
		}
		
		List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
		map.put("father", -1);
		map.put("ids", ids);
		
		if(ids.size()>0){
		}else{
			mav.addObject("treeList", null);
		}
		try {
			List<Tree> treeList = getTreesByParentId(map);//   List<Integer> ids
			mav.addObject("treeList", treeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 拿菜单
	 */
	public List<Tree> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Tree> list = treeServiceInf.getTreesByFatherOrIds(map);
		//
		
		
		for(Tree tree : list){
			//如果 是复选框  可以在这里判断   
			//tree.setChecked(true);
			if("open".equals(tree.getState())){
				continue;
			}else{
				map.put("father", tree.getId()+"");//更换id不换ids继续查
				tree.setChildren(getTreesByParentId(map));
			}
		}
		return list;
	}

}
