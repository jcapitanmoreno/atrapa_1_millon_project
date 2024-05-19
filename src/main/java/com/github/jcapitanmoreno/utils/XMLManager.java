package com.github.jcapitanmoreno.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLManager {
    /**
     * Escribe un objeto en un archivo XML.
     *
     * @param c        El objeto que se va a escribir en el archivo XML.
     * @param filename El nombre del archivo XML donde se escribirá el objeto.
     * @param <T>      El tipo genérico del objeto.
     * @return true si la escritura fue exitosa, false de lo contrario.
     */
    public static <T> boolean writeXML(T c,String filename){
        boolean result=false;
        JAXBContext context;
        try{
            context = JAXBContext.newInstance(c.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            m.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
            m.marshal(c,new File(filename));
            result=true;
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Lee un objeto desde un archivo XML.
     *
     * @param c        El objeto de tipo genérico que se va a leer desde el archivo XML.
     * @param filename El nombre del archivo XML del cual se leerá el objeto.
     * @param <T>      El tipo genérico del objeto.
     * @return El objeto leído desde el archivo XML.
     */
    public static<T> T readXML(T c,String filename){
        T result = c;
        JAXBContext context;

        try{
            context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (T) um.unmarshal(new File(filename));
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return result;
    }
}
