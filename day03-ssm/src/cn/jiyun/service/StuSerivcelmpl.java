package cn.jiyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jiyun.mapper.StuMapper;
import cn.jiyun.pojo.Stu;

@Service
public class StuSerivcelmpl implements StuSerivce {
		@Autowired
		private StuMapper sm;

		public List<Stu> findAll() {
			// TODO Auto-generated method stub
			List<Stu> list=sm.findAll();
			return list;
		}
		
}
