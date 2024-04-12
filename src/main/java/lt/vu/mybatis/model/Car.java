package lt.vu.mybatis.model;

public class Car {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAR.ID
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAR.MAKE
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    private String make;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAR.MODEL
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    private String model;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAR.CAROWNER_ID
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    private Long carOwnerId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAR.ID
     *
     * @return the value of PUBLIC.CAR.ID
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAR.ID
     *
     * @param id the value for PUBLIC.CAR.ID
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAR.MAKE
     *
     * @return the value of PUBLIC.CAR.MAKE
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public String getMake() {
        return make;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAR.MAKE
     *
     * @param make the value for PUBLIC.CAR.MAKE
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAR.MODEL
     *
     * @return the value of PUBLIC.CAR.MODEL
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public String getModel() {
        return model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAR.MODEL
     *
     * @param model the value for PUBLIC.CAR.MODEL
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAR.CAROWNER_ID
     *
     * @return the value of PUBLIC.CAR.CAROWNER_ID
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public Long getCarOwnerId() {
        return carOwnerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAR.CAROWNER_ID
     *
     * @param carOwnerId the value for PUBLIC.CAR.CAROWNER_ID
     *
     * @mbg.generated Fri Apr 12 21:42:49 EEST 2024
     */
    public void setCarOwnerId(Long carOwnerId) {
        this.carOwnerId = carOwnerId;
    }

    // added manually

    private CarOwner carOwner; // Add CarOwner property

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }
}