
package jsonseralizer;

import java.math.BigDecimal;

/**
 *
 * @author RadoslawKoch
 */
public class CarDto {

    private String make;
    private String model;
    private Integer year;
    private BigDecimal price;
    private Integer power;

    public CarDto(String make, String model, int year, BigDecimal price, int power) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.power = power;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "CarDto{" + "make=" + make + ", model=" + model + ", year=" + year + ", price=" + price + ", power=" + power + '}';
    }
    
    
    
}
