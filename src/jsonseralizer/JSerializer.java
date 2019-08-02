
package jsonseralizer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RadoslawKoch
 */
public class JSerializer {
    
    
    public static String getJson(Object object) throws IllegalArgumentException, IllegalAccessException{
        Field[]tmp = object.getClass().getDeclaredFields();
        String json = "{";
        for(int i = 0; i < tmp.length;i++){         
            Field fld = tmp[i];
            fld.setAccessible(true);
            json+="\""+fld.getName()+"\"=";
            if(fld.getType().isPrimitive() || fld.getType().getSimpleName().startsWith("Big")){
                json+=fld.get(object);
            }else{
                json+="\""+fld.get(object)+"\"";
            }
            if(i+1 < tmp.length){
                json+=",";
            }        
        }
        json+="}";
        return json;
    }
    
    public static String getJson(Collection collection) 
            throws IllegalArgumentException, 
            IllegalAccessException{
        return getJson(collection.toArray());
    }
    
    public static String getJson(Object[]tab) 
            throws IllegalArgumentException, 
            IllegalAccessException{
        int size = tab.length;
        String json = "{";
        for(int i = 0;i < size;i++){
            json+=getJson(tab[i]);
            if(i+1 < size){
                json+=",\n";
            }
        }   
        json+= "}";
        return json;
    }
    
    public static Object readJson(Class cl,String json) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        String[] str = json.split(",");
//        for(String x : str){
//            int startArg = x.indexOf("=");
//            int endArg = x.indexOf("\"",startArg+2);
//            if(endArg!=-1){
//                System.out.println(x.substring(startArg+2, endArg));
//            }else{
//                System.out.println(x.substring(startArg+1));
//            }
//        }
        return null;
    }
    
    public static Object[] readFieldValues(String json){
        String[] str = json.split(",");
        Object[]args = new Object[str.length];
        for(int i = 0; i < str.length;i++){
            
            String tmp = str[i];
            if(i==0){
                tmp = tmp.substring(1);
            }
            int startArg = tmp.indexOf("=");
            System.out.println();
            int endArg = tmp.indexOf("\"",startArg+2);
            if(endArg!=-1){
                tmp = tmp.substring(startArg+2, endArg);
            }else{
                tmp = tmp.substring(startArg+1);
            }         
            if(i==str.length-1){
                tmp = tmp.substring(0,tmp.length()-1);
            }
            args[i] = tmp;
        }
        return args;
    }
    
    public static Object[] readFieldValues(String json, Class cl) throws NoSuchFieldException, NoSuchMethodException{
        
        
        String[] str = json.split(",");
        Object[]args = new Object[str.length];
        Class[]classes = new Class[str.length];
        for(int i = 0; i < str.length;i++){
            //cl.getDeclaredField(json)
            String tmp = str[i];
            if(i==0){
                tmp = tmp.substring(1);
            }
            int startArg = tmp.indexOf("=");
            classes[i] = cl.getDeclaredField(tmp.substring(1,startArg-1)).getType();
            int endArg = tmp.indexOf("\"",startArg+2);
            if(endArg!=-1){
                tmp = tmp.substring(startArg+2, endArg);
            }else{
                tmp = tmp.substring(startArg+1);
            }         
            if(i==str.length-1){
                tmp = tmp.substring(0,tmp.length()-1);
            }
            try {
                System.out.println(args[i] + " --> "+ classes[i]);
                args[i] = classes[i].getConstructors()[0].newInstance(tmp);
            } catch (InstantiationException ex) {
                Logger.getLogger(JSerializer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JSerializer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(JSerializer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(JSerializer.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                System.out.println("error");
            }
        }
        return args;
    }
    
    
    
    

}
