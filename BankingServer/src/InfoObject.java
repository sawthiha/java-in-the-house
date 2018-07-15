

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author MIchael Pauk Sa
 */
public class InfoObject implements Serializable {
    private static final long serialVersionUID=42L;
    String type,content,userID;
    String[] detail=null;
    /**
     *
     * @param type
     * @param content
     * @param userID
     */
    public InfoObject(String type,String content,String userID)  {
        this.type=type;
        this.content=content;
        this.userID=userID;
    }
    /**
     *
     * @param type
     * @param content
     * @param userID
     * @param detail
     */
    public InfoObject(String type,String content,String userID,String[] detail)  {
        this.type=type;
        this.content=content;
        this.userID=userID;
        this.detail=detail;
    }
    @Override
    public String toString()  {
        return "Type : "+type+" Content : "+content+" UserName : "+userID;
    }
}
