
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author kenkwan
 */
public class Member implements Serializable{
    private String memID, memName, memPassword, memEmail, memStatus;
    
    public Member(){}
    public Member(String id, String name, String pwd, String email){
        memID = id;
        memName = name;
        memPassword = pwd;
        memEmail = email;
    }
    public Member(String id, String name, String pwd, String email, String status){
        memID = id;
        memName = name;
        memPassword = pwd;
        memEmail = email;
        memStatus = status;
    }
    
    public void setMemberID(String id){ memID = id; }
    public void setMemberName(String name){ memName = name; }
    public void setMemberPassword(String pwd){ memPassword = pwd; }
    public void setMemberEmail(String email){ memEmail = email; }
    public void setMmeberStatus(String status){ memStatus = status; }
    
    public String getMemberID(){ return memID; }
    public String getMemberName(){ return memName; }
    public String getMemberPassword(){ return memPassword; }
    public String getMemberEmail(){ return memEmail; }
    public String getMemberStatus(){ return memStatus; }
}
