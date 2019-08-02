package jsonseralizer;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 *
 * @author RadoslawKoch
 */
public class JsonSeralizer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
            throws InstantiationException, 
            IllegalAccessException, 
            NoSuchMethodException, 
            IllegalArgumentException, 
            InvocationTargetException,
            NoSuchFieldException {
        
        CarDto[]tab = {new CarDto("BMW","320D",2009,BigDecimal.valueOf(45330),177),
                    new CarDto("Renault","Laguna",2014,BigDecimal.valueOf(47000),183)};
        
     
            String obj = JSerializer.getJson(tab[0]);
            System.out.println(obj);
            Object[] objs = JSerializer.readFieldValues(obj,CarDto.class);
            
            for(Object x : objs){
                System.out.println(x.getClass());
            }
           

    }

}
