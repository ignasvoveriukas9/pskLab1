package lt.vu.mybatis.dao;

import java.util.List;

import lt.vu.mybatis.model.CarOwner;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CarOwnerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CAROWNER
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CAROWNER
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    int insert(CarOwner record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CAROWNER
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    CarOwner selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CAROWNER
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    List<CarOwner> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.CAROWNER
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    int updateByPrimaryKey(CarOwner record);
}