package com.me.courses_selection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.courses_selection.mapper.OrdersMapper;
import com.me.courses_selection.mapper.PointListMapper;
import com.me.courses_selection.pojo.Courses;
import com.me.courses_selection.pojo.Orders;
import com.me.courses_selection.service.ICoursesService;
import com.me.courses_selection.service.IOrdersService;
import com.me.courses_selection.vo.pojo.PointOfCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * June
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    @Resource
    private OrdersMapper ordersMapper;

    @Autowired
    @Resource
    private PointListMapper pointListMapper;

    @Autowired
    private ICoursesService coursesService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<PointOfCourses> getPointListBySid(Long sid){
        return pointListMapper.getPointListBySid(sid);
    }



        /**
         * 抢课成功写数据库
         */
        @Transactional
        @Override
        public Orders WriteToOrders(Long sid,Long cid) {
            //秒杀减库存
            Courses courses = coursesService.getOne(new QueryWrapper<Courses>().eq("cid", cid));
            courses.setNowStudents(courses.getNowStudents() + 1);
            boolean result = coursesService.update(new UpdateWrapper<Courses>().setSql("now_students=" + "now_students+1")
                    .eq("cid", cid).lt("now_students", courses.getMaxStudents()));
            //写入tempOrders表
            Orders orders = new Orders();
            if(ordersMapper.getNewOid()!=null) {
                orders.setOid(ordersMapper.getNewOid() + 1);
            }else{
                orders.setOid(new Long(1));
            }
            System.out.println(ordersMapper.getNewOid());
            orders.setSid(sid);
            orders.setCid(cid);
            ordersMapper.insert(orders);
            return orders;
        }

        @Override
        public void DropFromOrders(Long sid, Long cid){
            Courses courses = coursesService.getOne(new QueryWrapper<Courses>().eq("cid",cid));
            courses.setNowStudents(courses.getNowStudents()-1);
            boolean result=coursesService.update(new UpdateWrapper<Courses>().setSql("now_students="+"now_students-1")
                    .eq("cid",cid).lt("now_students", courses.getMaxStudents()));
            //删除tempOrders表
            Orders orders=new Orders();
            orders.setSid(sid);
            orders.setCid(cid);
            ordersMapper.delete(new QueryWrapper<Orders>().eq("cid",cid).eq("sid",sid));
        }
}
