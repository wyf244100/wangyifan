package cn.jiyun.mapper;

import java.util.List;

import cn.jiyun.pojo.City;

public interface CityMapper {

	List<City> findSheng();

	List<City> findShi(Integer cid);

	String findCityByID(Integer cid);

}
