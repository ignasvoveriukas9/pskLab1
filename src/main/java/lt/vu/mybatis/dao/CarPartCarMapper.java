package lt.vu.mybatis.dao;

import java.util.List;

import lt.vu.mybatis.model.CarPartCar;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarPartCarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CARPART_CAR
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    int insert(CarPartCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CARPART_CAR
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    List<CarPartCar> selectAll();
}